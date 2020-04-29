package models.dao;

import models.entity.Person;

import java.util.List;

public interface PersonDAO {
    int add(Person person);
    Person getById(int id);
    boolean update(Person person);
    boolean deleteById(int id);
    List<Person> getAll();
}
