package model.dao.mysql;

import model.dao.PersonDAO;
import model.dao.generic.GenericMySQLImpl;
import model.entity.Person;
import model.util.Constants;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonMySQLImpl extends GenericMySQLImpl implements PersonDAO {
    final static Logger logger = Logger.getLogger(PersonMySQLImpl.class);

    @Override
    public int addPerson(Person person) {
        String requestSQL = manager.getSQLRequest("ADD_PERSON");
        Connection connection = pool.getConnection();
        int reusltOperation = Constants.DEFAULT_OPERATION;
        ResultSet resultSet = null;

        try(PreparedStatement statement = connection.prepareStatement(requestSQL)) {
            String passwordMD5 = getMD5Hash(person.getPassword());
            statement.setString(1, person.getName());
            statement.setString(2, person.getMiddleName());
            statement.setString(3, person.getLastName());
            statement.setString(4, person.getLogin());
            statement.setString(5, passwordMD5);
            statement.setString(6, person.getEmail());
            statement.setInt(7, person.getRoleId());

            if(statement.executeUpdate() > 0){
                reusltOperation = Constants.OK_OPERATION;
            }
        } catch (SQLException e) {
            logger.error("Error in PreparedStatement, look PersonMySQLImpl class", e);
            reusltOperation = Constants.ERROR_OPERATION;
        }finally {
            pool.returnConnection(connection);
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error("Error in addPerson method - I can't closed resultSet", e);
                }
            }
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
    public boolean isLoginOrEmailPerson(String login, String email) {
        String requestSQL = manager.getSQLRequest("IS_LOGIN_OR_EMAIL_PERSON");
        Connection connection = pool.getConnection();
        ResultSet resultSet = null;
        boolean isLogin = false;

        try(PreparedStatement statement = connection.prepareStatement(requestSQL)) {
            statement.setString(1, login);
            statement.setString(2, email);
            resultSet = statement.executeQuery();

            if(resultSet.next()){
                isLogin = true;
            }
        } catch (SQLException e) {
            logger.error("Error in PreparedStatement, look ActionMySQLImpl class",e);
        }finally {
            pool.closeResultSet(resultSet);
            pool.returnConnection(connection);
        }
        return isLogin;
    }

//    @Override
//    public boolean isLoginPerson(String login) {
//        String requestSQL = manager.getSQLRequest("IS_LOGIN_PERSON");
//        Connection connection = pool.getConnection();
//        ResultSet resultSet = null;
//        boolean isLogin = false;
//
//        try(PreparedStatement statement = connection.prepareStatement(requestSQL)) {
//            statement.setString(1, login);
//            resultSet = statement.executeQuery();
//            if(resultSet.next()){
//                isLogin = true;
//            }
//        } catch (SQLException e) {
//            logger.error("Error in PreparedStatement, look PersonMySQLImpl class",e);
//        }finally {
//            pool.closeResultSet(resultSet);
//            pool.returnConnection(connection);
//        }
//
//        return isLogin;
//    }
//
//    @Override
//    public boolean isEmailPerson(String email) {
//        String requestSQL = manager.getSQLRequest("IS_EMAIL_PERSON");
//        Connection connection = pool.getConnection();
//        ResultSet resultSet = null;
//        boolean isEmail = false;
//
//        try(PreparedStatement statement = connection.prepareStatement(requestSQL)) {
//            statement.setString(1, email);
//            resultSet = statement.executeQuery();
//            if(resultSet.next()){
//                isEmail = true;
//            }
//        } catch (SQLException e) {
//            logger.error("Error in PreparedStatement, look PersonMySQLImpl class",e);
//        }finally {
//            pool.closeResultSet(resultSet);
//            pool.returnConnection(connection);
//        }
//
//        return isEmail;
//    }

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

    @Override
    public Person getPersonByLoginAndPassword(String login, String password) {
        String requestSQL = manager.getSQLRequest("GET_PERSON_BY_LOGIN_PASSWORD");
        String passwordMD5 = getMD5Hash(password);
        Person person = null;
        ResultSet resultSet = null;
        Connection connection = pool.getConnection();

        try(PreparedStatement statement = connection.prepareStatement(requestSQL)) {
            statement.setString(1, login);
            statement.setString(2, passwordMD5);
            resultSet = statement.executeQuery();

            if(resultSet.next()){
               int id = resultSet.getInt("id");
               String name = resultSet.getString("name");
               String middleName = resultSet.getString("middle_name");
               String lastName = resultSet.getString("last_name");
               String email = resultSet.getString("email");
               int role = resultSet.getInt("role");
               person = new Person(id,name,middleName,lastName,login,passwordMD5,email,role);
            }
        } catch (SQLException e) {
            logger.error("Error in statement, look class",e);
        }finally {
            pool.closeResultSet(resultSet);
            pool.returnConnection(connection);
        }

        return person;
    }

    // Метод для хэширования пароля (используем зависимость Commons-codec)
    private String getMD5Hash(String text){
        return DigestUtils.md5Hex(text);
    }

}
