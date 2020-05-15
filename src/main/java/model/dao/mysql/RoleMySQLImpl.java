package models.dao.mysql;

import models.dao.RoleDAO;
import models.dao.generic.GenericMySQLImpl;
import models.entity.Role;
import models.util.Constants;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMySQLImpl extends GenericMySQLImpl implements RoleDAO {
    final static Logger logger = Logger.getLogger(RoleMySQLImpl.class);

    @Override
    public int addRole(Role role) {
        String requestSQL = manager.getSQLRequest("ADD_ROLE");
        int reusltOperation = Constants.DEFAULT_OPERATION;

        if(getRoleByName(role.getName()) == null){ //проверяю есть ли с таким именем role
            Connection connection = pool.getConnection();

            try(PreparedStatement statement = connection.prepareStatement(requestSQL)) {
                statement.setString(1, getPreparedName(role.getName()));

                if(statement.executeUpdate() > 0){
                    reusltOperation = Constants.OK_OPERATION;
                }

            } catch (SQLException e) {
                logger.error("Error in SQL request",e);
            }finally {
                pool.returnConnection(connection);
            }
        }else{
            reusltOperation = Constants.ERROR_OPERATION;
        }
        return reusltOperation;
    }

    @Override
    public Role getRoleByName(String name) {
        String requestSQL = manager.getSQLRequest("GET_ROLE_BY_NAME");
        Connection connection = pool.getConnection();
        Role role = null;

        try(PreparedStatement statement = connection.prepareStatement(requestSQL)) {
            statement.setString(1, getPreparedName(name));
            role = getRoleByStatement(statement);
        } catch (SQLException e) {
            logger.error("Error in PreparedStatement or ResultSet, look that class",e);
        }finally {
            pool.returnConnection(connection);
        }
        return role;
    }

    @Override
    public Role getRoleById(int id) {
        String requestSQL = manager.getSQLRequest("GET_ROLE_BY_ID");
        Connection connection = pool.getConnection();
        Role role = null;

        try (PreparedStatement statement = connection.prepareStatement(requestSQL)) {
            statement.setInt(1, id);
            role = getRoleByStatement(statement);
        }catch (SQLException e){
            logger.error("Error in PreparedStatement, look that class",e);
        }finally {
            pool.returnConnection(connection);
        }
        return role;
    }

    @Override
    public boolean deleteRoleByID(int id) {
        String requestSQL = manager.getSQLRequest("DELETE_ROLE_BY_ID");
        boolean complSuccess = false;
        Connection connection = pool.getConnection();

        try(PreparedStatement statement = connection.prepareStatement(requestSQL)){
            statement.setInt(1,id);

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

    //нужно ли тут выбрасывать исключение - если передал null обьект ????
    private String getPreparedName(String name){
        return name.toUpperCase().trim();
    }


    // кидаем готовый PreparedStatement - он выполняет поиск и возвращает готовую Role
    private Role getRoleByStatement(PreparedStatement statement){
        Role role = null;
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                int idFromDB = resultSet.getInt("id");
                String nameFromDB = resultSet.getString("name");
                role = new Role (idFromDB, nameFromDB);
            }
        } catch (SQLException e) {
            logger.error("getRoleByStatement method error in ResultSet",e);
        }finally {
            pool.closeResultSet(resultSet);
        }
        return role;
    }

}
