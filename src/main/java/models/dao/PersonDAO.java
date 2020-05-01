package models.dao;

import models.entity.Person;

import java.util.List;

public interface PersonDAO {
    int addPerson(Person person);
    Person getPersonById(int id);
    Person getPersonByLogPass(String login, String password);
    //boolean updatePerson(Person person);
    boolean deleteById(int id);
    List<Person> getPersonsByRole(int roleId);

}
