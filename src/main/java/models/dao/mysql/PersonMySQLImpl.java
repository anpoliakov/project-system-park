package models.dao.mysql;

import models.dao.PersonDAO;
import models.dao.generic.GenericMySQLImpl;
import models.entity.Person;
import models.util.Constants;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonMySQLImpl extends GenericMySQLImpl implements PersonDAO {
    final static Logger logger = Logger.getLogger(PersonMySQLImpl.class);

    @Override
    public int addPerson(Person person) {
        String requestSQL = manager.getSQLRequest("ADD_PERSON");
        int reusltOperation = Constants.DEFAULT_OPERATION;
        String login = person.getLogin();
        String password = person.getPassword();
        String email = person.getEmail();


        if(getPersonByLogPassEmail(login, password, email) == null){
            Connection connection = pool.getConnection();

            try(PreparedStatement statement = connection.prepareStatement(requestSQL)) {
                statement.setString(1, person.getName());
                statement.setString(2, person.getMiddleName());
                statement.setString(3, person.getLastName());
                statement.setString(4, login);
                statement.setString(5, password);
                statement.setString(6, email);
                statement.setInt(7, person.getRoleId());

                if(statement.executeUpdate() > 0){
                    reusltOperation = Constants.OK_OPERATION;
                }
            } catch (SQLException e) {
                logger.error("Error in PreparedStatement, look PersonMySQLImpl class", e);
            }finally {
                pool.returnConnection(connection);
            }
        }else{
            reusltOperation = Constants.ERROR_OPERATION;
        }
        return reusltOperation;
    }

    @Override
    public Person getPersonById(int id) {
        String requestSQL = manager.getSQLRequest("GET_PERSON_BY_ID");
        Connection connection = pool.getConnection();
        ResultSet resultSet = null;
        Person person = null;

        try (PreparedStatement statement = connection.prepareStatement(requestSQL)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if(resultSet.next()){
                String name = resultSet.getString("name");
                String middleName = resultSet.getString("middle_name");
                String last_name = resultSet.getString("last_name");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                int role = resultSet.getInt("role");
                person = new Person(id,name,middleName,last_name,login,password,email,role);
            }
        }catch (SQLException e){
            logger.error("Error in PreparedStatement, look that class",e);
        }finally {
            pool.closeResultSet(resultSet);
            pool.returnConnection(connection);
        }
        return person;
    }

    @Override
    public Person getPersonByLogPassEmail(String login, String password,String email) {
        String requestSQL = manager.getSQLRequest("GET_PERSON_BY_LOGIN_PASSW_EMAIL");
        Connection connection = pool.getConnection();
        ResultSet resultSet = null;
        Person person = null;

        try(PreparedStatement statement = connection.prepareStatement(requestSQL)) {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, email);
            resultSet = statement.executeQuery();

            if(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String middleName = resultSet.getString("middle_name");
                String last_name = resultSet.getString("last_name");
                int role = resultSet.getInt("role");
                person = new Person(id,name,middleName,last_name,login,password,email,role);
            }
        } catch (SQLException e) {
            logger.error("Error in PreparedStatement, look ActionMySQLImpl class",e);
        }finally {
            pool.closeResultSet(resultSet);
            pool.returnConnection(connection);
        }
        return person;
    }

    @Override
    public boolean deleteById(int id) {
        String requestSQL = manager.getSQLRequest("DELETE_PERSON_BY_ID");
        boolean operationSuccess = false;
        Connection connection = pool.getConnection();

        try(PreparedStatement statement = connection.prepareStatement(requestSQL)){
            statement.setInt(1, id);
            if(statement.executeUpdate() > 0) {
                operationSuccess = true;
            }
        }catch (SQLException e){
            logger.error("Error in PreparedStatement, look that class",e);
        }finally {
            pool.returnConnection(connection);
        }
        return operationSuccess;
    }

    @Override
    public List<Person> getPersonsByRole(int roleId) {
        String requestSQL = manager.getSQLRequest("GET_PERSONS_BY_IDROLE");
        List <Person> listActions = null;
        Connection connection = pool.getConnection();
        ResultSet resultSet = null;
        Person person = null;

        try(PreparedStatement statement = connection.prepareStatement(requestSQL)){
            statement.setInt(1,roleId);
            listActions = new ArrayList<>();
            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String middleName = resultSet.getString("middle_name");
                String last_name = resultSet.getString("last_name");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");

                listActions.add(new Person(id,name,middleName,last_name,login,password,email,roleId));
            }

        }catch (SQLException e){
            logger.error("Error in statement, look that class",e);
        }finally {
            pool.closeResultSet(resultSet); // ВЕРНО ЛИ ? что тут закрыл
            pool.returnConnection(connection);
        }
        return listActions;
    }

}
