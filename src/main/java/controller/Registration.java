package controller;

import com.google.gson.Gson;
import model.dao.PersonDAO;
import model.dao.RoleDAO;
import model.entity.Person;
import model.factory.FactoryAbstract;
import model.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/registration")
public class Registration extends HttpServlet {
    final static Logger logger = Logger.getLogger(Registration.class);
    private int resultAddPerson = Constants.DEFAULT_OPERATION;
    private FactoryAbstract factoryMySQL;
    private PersonDAO personImpl;
    private RoleDAO roleDAOImpl;
    private Gson gson;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        factoryMySQL = FactoryAbstract.getFactoryMySQL();
        personImpl = factoryMySQL.getPersonDAOImpl();
        roleDAOImpl = factoryMySQL.getRoleDAOImpl();
        gson = new Gson();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(Constants.ENCODING_DEFAULT);
        resp.setCharacterEncoding(Constants.ENCODING_DEFAULT);
        resp.setContentType("application/json");

        // Получаю данные из request
        String data = getDataFromRequest(req);
        if(data != null){
            Person person = gson.fromJson(data, Person.class);
            // получаю ID роли
            int roleID = roleDAOImpl.getRoleIdByName(person.getRole());

            if(roleID != Constants.ERROR_OPERATION){
                person.setRoleId(roleID);

                // Если в БД нету пользователя с таким Login и Email
                if(!(personImpl.isLoginOrEmailPerson(person.getLogin(), person.getEmail()))){
                    resultAddPerson = personImpl.addPerson(person);
                    System.out.println("Пользователь успешно добавлен в БД!");
                }else {
                    resultAddPerson = Constants.ERROR_OPERATION;
                    System.out.println("Пользователь с таким Login или Email существует!");
                }
            }else{
                System.err.println("ERROR: Роль пользователя не была найдена в БД, заполни возможные роли в таблице БД");
            }
        }

        // отправка -1 (если пользователь не был добавлен или (такой логин или емайл существует))
        // отправка 1 (если пользователь добавлен в БД)
        PrintWriter writer = resp.getWriter();
        writer.println(resultAddPerson);
        writer.close();
    }

    private String getDataFromRequest(HttpServletRequest request) {
        String data = null;

        try (BufferedReader buffReader = new BufferedReader (new InputStreamReader(request.getInputStream(), "UTF-8"))){
            String line;
            if ((line = buffReader.readLine()) != null){
                data = line;
            }
        } catch (IOException e) {
            logger.error("buffReader can't get data from stream");
        }
        return data;
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
* 7) Нужно в скрипт который предназначен для создания БД - добавить Role: OWNER, FORESTER, ADNMIN
*
*/