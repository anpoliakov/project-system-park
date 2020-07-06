package model.dao;

import model.entity.Person;

import java.util.List;

public interface PersonDAO {
    int addPerson(Person person);
    Person getPersonById(int id);
    boolean isLoginOrEmailPerson(String login, String email);
    //boolean updatePerson(Person person);
    boolean deleteById(int id);
    List<Person> getPersonsByRole(int roleId);
    Person getPersonByLoginAndPassword(String login, String password);
    boolean setCookie (int idPerson, String cookie);
    String getCookie (int idPerson);
    boolean delCookie (int idPerson);
}
