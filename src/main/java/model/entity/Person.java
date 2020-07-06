package model.entity;


import model.dao.RoleDAO;
import model.factory.FactoryAbstract;
import model.factory.FactoryMySQL;

import java.util.Objects;

public class Person {
    private FactoryAbstract factoryMySQL;
    private int id;
    private String name;
    private String middleName;
    private String lastName;
    private String login;
    private String password;
    private String email;

    // поле с ID роли из БД
    private int roleId;
    // поле с именем роли
    private String role;

    public Person(int id, String name, String middleName, String lastName, String login, String password, String email, int roleId) {
        this.id = id;
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.roleId = roleId;

        //получаем string имя роли
        factoryMySQL = new FactoryMySQL();
        RoleDAO roleDAOImpl = factoryMySQL.getRoleDAOImpl();
        role = roleDAOImpl.getRoleById(roleId).getName().toLowerCase();
    }

    public Person(String name, String middleName, String lastName, String login, String password, String email, int roleId) {
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.roleId = roleId;

        //получаем string имя роли
        factoryMySQL = new FactoryMySQL();
        RoleDAO roleDAOImpl = factoryMySQL.getRoleDAOImpl();
        role = roleDAOImpl.getRoleById(roleId).getName().toLowerCase();
    }

    //конструктор который по параметру создаёт Person без roleID - НО с именем роли (используется при регистрации)
    public Person(String name, String middleName, String lastName, String login, String password, String email, String role) {
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Person(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                Objects.equals(name, person.name) &&
                Objects.equals(middleName, person.middleName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(login, person.login) &&
                Objects.equals(password, person.password) &&
                Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, middleName, lastName, login, password, email);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roleId=" + roleId +
                ", role='" + role + '\'' +
                '}';
    }
}
