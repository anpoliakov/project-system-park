package controller;

import com.google.gson.Gson;
import model.entity.Person;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet("/mainservlet")
public class MainServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(MainServlet.class);
    private Gson gson;

    @Override
    public void init() throws ServletException {
        super.init();
        gson = new Gson();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Inside servlet >> ");

        String stringJson = getJsonFromClient(request);
        System.out.println("JSON from client: " + stringJson);

        Person testPerson = gson.fromJson(stringJson, Person.class);
        System.out.println(testPerson.getName());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    private String getJsonFromClient(HttpServletRequest request){
        BufferedReader buff = null;
        String stringJson = "";

        try {
            buff = new BufferedReader(new InputStreamReader(request.getInputStream()));
        } catch (IOException e) {
            logger.error("Error getting information from a stream");
        }

        if (buff != null) {
            try {
                stringJson = buff.readLine();
            } catch (IOException e) {
                logger.error("The line is not read");
            }finally {
                try {
                    buff.close();
                } catch (IOException e) {
                    logger.error("Closing error BufferedReader");
                }
            }
        }
        return stringJson;
    }
}

















/*
*
* 1) Избавиться от шума название DAO - и так ясно что в папке dao находятся dao
* 2) Есть ли разница и как работает закрытие try-resource + finally(в котором свой порядок закрытия ResultSet + Connection, ведь Statement закроется в ресурсах)
* 3) в ДАО Park - есть ошибка в таблице? теперь может быть таких 2:     id|Парк А|1   (2 и 3 колонка повторяются 2 раза)
* 4) UPDATE - операцию добавить в impl сущностей
* 5) Как нужно пробрасывать исключение ? какие именно? (выше имеется ввиду)
* 6) Почему мой web архив или папка не видна в tomcat ?
*
*
* */