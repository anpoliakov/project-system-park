package models.dao.mysql;

import models.dao.ParkDAO;
import models.dao.generic.GenericMySQLImpl;
import models.entity.Park;
import models.util.Constants;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public Park getParkByOwnerId(int ownerId) {
        String requestSQL = manager.getSQLRequest("GET_PARK_BY_OWNER_ID");
        Connection connection = pool.getConnection();
        ResultSet resultSet = null;
        Park park = null;

        try(PreparedStatement statement = connection.prepareStatement(requestSQL)) {
            statement.setInt(1, ownerId);

            resultSet = statement.executeQuery();
            if(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
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
