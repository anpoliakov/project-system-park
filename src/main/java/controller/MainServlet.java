package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mainservlet")
public class MainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long time1 = System.currentTimeMillis();

        System.out.println("Hello!");



        long time2 = System.currentTimeMillis();
        long result = (time2-time1)/1000;
        System.out.println("Сервлет отработал за: " + result + " c.");
    }
}

















/*
*
* 1) Избавиться от шума название DAO - и так ясно что в папке dao находятся dao
* 2) Есть ли разница и как работает закрытие try-resource + finally(в котором свой порядок закрытия ResultSet + Connection, ведь Statement закроется в ресурсах)
* 3) в ДАО Park - есть ошибка в таблице? теперь может быть таких 2:     id|Парк А|1   (2 и 3 колонка повторяются 2 раза)
* 4) UPDATE - операцию добавить в impl сущностей
* 5) Как нужно пробрасывать исключение ? какие именно? (выше имеется ввиду)
* */