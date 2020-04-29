package controllers;

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

    }
}

















/*
*
* 1) Избавиться от шума название DAO - и так ясно что в папке dao находятся dao
* 2) Есть ли разница и как работает закрытие try-resource + finally(в котором свой порядок закрытия ResultSet + Connection, ведь Statement закроется в ресурсах)
* 3)
*
* */