package models.dao.mysql;

import models.dao.PersonDAO;
import models.dao.generic.GenericMySQLImpl;
import models.entity.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PersonMySQLImpl extends GenericMySQLImpl implements PersonDAO {


    @Override
    public int add(Person person) {
        String requestSQL = manager.getSQLRequest("ADD");
        Connection connection = pool.getConnection();
        int result = 0;

        try(PreparedStatement statement = connection.prepareStatement(requestSQL)) {

            statement.setString(1, "parksystem.people");
            statement.setString(2, "name");
            statement.setString(3, "middle_name");
            statement.setString(4, "last_name");
            statement.setString(5, "login");
            statement.setString(6, "password");
            statement.setString(7, "email");
            statement.setString(8, "role");

            statement.setString(9, person.getName());
            statement.setString(10, person.getMiddleName());
            statement.setString(11, person.getLastName());
            statement.setString(12, person.getLogin());
            statement.setString(13, person.getPassword());
            statement.setString(14, person.getEmail());
//            statement.setInt(15, person.getRoleId());

            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            pool.returnConnection(connection);
        }

        return result;
    }

    @Override
    public Person getById(int id) {
        return null;
    }

    @Override
    public boolean update(Person person) {
        return false;
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
