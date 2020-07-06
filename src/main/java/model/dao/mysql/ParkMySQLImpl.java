package model.dao.mysql;

import model.dao.ParkDAO;
import model.dao.generic.GenericMySQLImpl;
import model.entity.Park;
import model.util.Constants;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParkMySQLImpl extends GenericMySQLImpl implements ParkDAO {
    final static Logger logger = Logger.getLogger(ParkMySQLImpl.class);

    @Override
    public int addPark(Park park) {
        String requestSQL = manager.getSQLRequest("ADD_PARK");
        int reusltOperation = Constants.DEFAULT_OPERATION;
        Connection connection = pool.getConnection();

        try(PreparedStatement statement = connection.prepareStatement(requestSQL)) {
            statement.setString(1, park.getName());
            statement.setInt(2, park.getOwnerId());

            if(statement.executeUpdate() > 0){
                reusltOperation = Constants.OK_OPERATION;
            }

        } catch (SQLException e) {
            logger.error("Error in SQL request",e);
        }finally {
            pool.returnConnection(connection);
        }
        return reusltOperation;
    }

    @Override
    public Park getParkById(int id) {
        String requestSQL = manager.getSQLRequest("GET_PARK_BY_ID");
        Connection connection = pool.getConnection();
        ResultSet resultSet = null;
        Park park = null;

        try(PreparedStatement statement = connection.prepareStatement(requestSQL)) {
            statement.setInt(1, id);

            resultSet = statement.executeQuery();
            if(resultSet.next()){
               String name = resultSet.getString("name");
               int ownerId = resultSet.getInt("owner");
               park = new Park(id, name, ownerId);
            }

        } catch (SQLException e) {
            logger.error("Error in SQL request",e);
        }finally {
            pool.closeResultSet(resultSet);
            pool.returnConnection(connection);
        }
        return park;
    }

    @Override
    public List<Park> getParksByOwnerId(int ownerId) {
        String requestSQL = manager.getSQLRequest("GET_PARKS_BY_OWNER_ID");
        Connection connection = pool.getConnection();
        ResultSet resultSet = null;
        List <Park> parks = null;

        try(PreparedStatement statement = connection.prepareStatement(requestSQL)) {
            statement.setInt(1, ownerId);
            parks = new ArrayList<>();

            resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                parks.add(new Park(id,name,ownerId));
            }
        } catch (SQLException e) {
            logger.error("Error in SQL request",e);
        }finally {
            pool.closeResultSet(resultSet);
            pool.returnConnection(connection);
        }
        return parks;
    }

    @Override
    public boolean deleteParkById(int id) {
        String requestSQL = manager.getSQLRequest("DELETE_PARK_BY_ID");
        Connection connection = pool.getConnection();
        boolean operationSuccess = false;

        try(PreparedStatement statement = connection.prepareStatement(requestSQL)) {
            statement.setInt(1, id);
            if(statement.executeUpdate() > 0) {
                operationSuccess = true;
            }
        } catch (SQLException e) {
            logger.error("Error in SQL request",e);
        }finally {
            pool.returnConnection(connection);
        }
        return operationSuccess;
    }
}
