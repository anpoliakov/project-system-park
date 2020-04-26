package controllers;

import models.dao.Action;
import models.factory.FactoryAbstract;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MainServlet")
public class MainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FactoryAbstract mySQLFactory = FactoryAbstract.getMySQLDAOFactory();
        Action actionDao = mySQLFactory.getActionDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

/*
*
* 1) избавиться от шума DAO - и так ясно что в папке dao находятся dao
* 2) класс FactoryMySQL в каждую возвращаемую impl я передаю pool ? есть ли в этом ошибка ?
*
*
* */