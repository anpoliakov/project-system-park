package controllers;

import models.dao.RoleDAO;
import models.entity.Role;
import models.factory.FactoryAbstract;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FactoryAbstract factoryMySQL = FactoryAbstract.getFactoryMySQL();
        RoleDAO roleDAOImpl = factoryMySQL.getRoleDAOImpl();

        Role role1 = new Role("OWNER");
        Role role2 = new Role("FORESTER");
        Role role3 = new Role("ADMIN");
        Role role4 = new Role("admin");
        Role role5 = new Role("owner");

        System.out.println("Проверяй, статус =  " + roleDAOImpl.addRole(role1));
        System.out.println("Проверяй, статус =  " + roleDAOImpl.addRole(role2));
        System.out.println("Проверяй, статус =  " + roleDAOImpl.addRole(role3));
        System.out.println("Проверяй, статус =  " + roleDAOImpl.addRole(role4));
        System.out.println("Проверяй, статус =  " + roleDAOImpl.addRole(role5));

    }
}

















/*
*
* 1) Избавиться от шума название DAO - и так ясно что в папке dao находятся dao
* 2) Если
* 3)
*
* */