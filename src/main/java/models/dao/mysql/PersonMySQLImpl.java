package models.dao.mysql;

import models.dao.PersonDAO;
import models.dao.generic.GenericMySQLImpl;
import models.entity.Person;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PersonMySQLImpl extends GenericMySQLImpl implements PersonDAO {
    final static Logger logger = Logger.getLogger(PersonMySQLImpl.class);

    @Override
    public int addPerson(Person person) {
        // остановился тут
        return 0;
    }

    @Override
    public Person getPersonById(int id) {
        return null;
    }

    @Override
    public Person getPersonByLogPass(String login, String password) {
        String requestSQL = manager.getSQLRequest("GET_PERSON_BY_LOGIN_PASSW");
        Connection connection = pool.getConnection();
        ResultSet resultSet = null;
        Person person = null;

        try(PreparedStatement statement = connection.prepareStatement(requestSQL)) {
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String middleName = resultSet.getString("middle_name");
                String last_name = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                int role = resultSet.getInt("role");
                person = new Person(id,name,middleName,last_name,login,password,email,role);
            }
        } catch (SQLException e) {
            logger.error("Error in PreparedStatement, look ActionMySQLImpl class",e);
        }finally {
            pool.closeResultSet(resultSet);
            pool.returnConnection(connection);
        }
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public List<Person> getAll() {
        return null;
    }
}
