package models.dao.mysql;

import models.dao.ActionDAO;
import models.dao.generic.GenericMySQLImpl;
import models.entity.Action;
import models.util.Constants;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActionMySQLImpl extends GenericMySQLImpl <Action> implements ActionDAO {
    final static Logger logger = Logger.getLogger(ActionMySQLImpl.class);


    @Override
    public int addAction(Action action) {
        String requestSQL = manager.getSQLRequest("ADD_ACTION");
        int reusltOperation = Constants.DEFAULT_OPERATION;

        if(getActionByName(action.getName()) == null){
            Connection connection = pool.getConnection();

            try(PreparedStatement statement = connection.prepareStatement(requestSQL)) {
                statement.setString(1, getPreparedName(action.getName()));
                if(statement.executeUpdate() > 0){
                    reusltOperation = Constants.OK_OPERATION;
                }
            } catch (SQLException e) {
                logger.error("Error in PreparedStatement, look ActionMySQLImpl class",e);
            }finally {
                pool.returnConnection(connection);
            }
        }else{
            reusltOperation = Constants.ERROR_OPERATION;
        }
        return reusltOperation;
    }

    @Override
    public Action getActionById(int id) {
        String requestSQL = manager.getSQLRequest("GET_ACTION_BY_ID");
        Connection connection = pool.getConnection();
        Action action = null;

        try (PreparedStatement statement = connection.prepareStatement(requestSQL)) {
            statement.setInt(1, id);
            action = getActionByStatement(statement);
        }catch (SQLException e){
            logger.error("Error in PreparedStatement, look that class",e);
        }finally {
            pool.returnConnection(connection);
        }
        return action;
    }

    @Override
    public Action getActionByName(String name) {
        String requestSQL = manager.getSQLRequest("GET_ACTION_BY_NAME");
        Connection connection = pool.getConnection();
        Action action = null;

        try(PreparedStatement statement = connection.prepareStatement(requestSQL)) {
            statement.setString(1, getPreparedName(name));
            action = getActionByStatement(statement);
        } catch (SQLException e) {
            logger.error("Error in PreparedStatement or ResultSet, look that class",e);
        }finally {
            pool.returnConnection(connection);
        }
        return action;
    }

    @Override
    public boolean deleteActionById(int id) {
        String requestSQL = manager.getSQLRequest("DELETE_ACTION_BY_ID");
        boolean complSuccess = false;
        Connection connection = pool.getConnection();

        try(PreparedStatement statement = connection.prepareStatement(requestSQL)){
            statement.setInt(1, id);
            if(statement.executeUpdate() > 0) {
                complSuccess = true;
            }
        }catch (SQLException e){
            logger.error("Error in PreparedStatement, look that class",e);
        }finally {
            pool.returnConnection(connection);
        }
        return complSuccess;
    }

    @Override
    public List<Action> getListAction() {
        String requestSQL = manager.getSQLRequest("GET_ALL_ACTIONS");
        Connection connection = pool.getConnection();
        List <Action> listActions = null;
        ResultSet resultSet = null;

        try(Statement statement = connection.createStatement()){
            resultSet = statement.executeQuery(requestSQL);
            listActions = new ArrayList<>();

            while(resultSet.next()) {
                int idFromDB = resultSet.getInt("id");
                String nameFromDB = resultSet.getString("name");
                listActions.add(new Action(idFromDB,nameFromDB));
            }

        }catch (SQLException e){
            logger.error("Error in statement, look that class",e);
        }finally {
            pool.closeResultSet(resultSet); // ВЕРНО ЛИ ? что тут закрыл
            pool.returnConnection(connection);
        }
        return listActions;
    }

    private String getPreparedName(String name){
        return name.toLowerCase().trim();
    }

    private Action getActionByStatement(PreparedStatement statement){
        Action action = null;
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                int idFromDB = resultSet.getInt("id");
                String nameFromDB = resultSet.getString("name");
                action = new Action (idFromDB, nameFromDB);
            }
        } catch (SQLException e) {
            logger.error("getActionByStatement method error in ResultSet",e);
        }finally {
            pool.closeResultSet(resultSet);
        }
        return action;
    }
}
