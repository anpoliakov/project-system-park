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
        Connection connection = pool.getConnection();
        int reusltOperation = Constants.DEFAULT_OPERATION;

        if(getRoleByName(role.getName()) == null){ //проверяю есть ли с таким именем role
            System.out.println("результат метода getRoleByName == null" ); // !!!!!!!!!!!!!!!!!
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
        ResultSet resultSet = null;

        try(PreparedStatement statement = connection.prepareStatement(requestSQL)) {
            statement.setString(1, getPreparedName(name));
            resultSet = statement.executeQuery();

            if (resultSet.next()){
                System.out.println("имя " + getPreparedName(name) + " найдено в БД");
                int idFromDB = resultSet.getInt("id");
                String nameFromDB = resultSet.getString("name");
                role = new Role (idFromDB, nameFromDB);
            }

        } catch (SQLException e) {
            logger.error("Error in PreparedStatement or ResultSet, look that class",e);
        }finally {
            pool.closeResultSet(resultSet);
            pool.returnConnection(connection);
        }
        return role;
    }

    //нужно ли тут выбрасывать исключение - если передал null обьект ????
    private String getPreparedName(String name){
        return name.toUpperCase().trim();
    }

}
