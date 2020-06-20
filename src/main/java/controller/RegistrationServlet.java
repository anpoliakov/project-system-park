package controller;

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
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(RegistrationServlet.class);
    private int resultAddPerson = Constants.DEFAULT_OPERATION;

    private FactoryAbstract factoryMySQL;
    private PersonDAO personImpl;
    private RoleDAO roleDAOImpl;

    private HttpSession session;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        factoryMySQL = FactoryAbstract.getFactoryMySQL();
        System.out.println("# Init RegistrationServlet");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession();
        Person person = (Person) session.getAttribute("person");
        System.out.println("Person from session: " + person);

        // Создаём DAO для работы с Person и Role
        personImpl = factoryMySQL.getPersonDAOImpl();
        roleDAOImpl = factoryMySQL.getRoleDAOImpl();

        // Получаю ID Role из ДБ, соответствующей роли Person
        int roleIdByName = roleDAOImpl.getRoleIdByName(person.getRole());

        // Если Role в БД была найдена
        if(roleIdByName != Constants.ERROR_OPERATION){
            // Добавляю id роли к обьекту Person
            person.setRoleId(roleIdByName);

            // Если в БД нету пользователя с таким Login и Email
            if(!(personImpl.isLoginOrEmailPerson(person.getLogin(), person.getEmail()))){
                resultAddPerson = personImpl.addPerson(person);
                System.out.println("Пользователь успешно добавлен в БД!");
            }else {
                resultAddPerson = Constants.ERROR_OPERATION;
//                session.removeAttribute("person");
                System.out.println("Пользователь с таким Login или Email существует!");
            }

        }else{
            System.err.println("ERROR: Роль пользователя не была найдена в БД, заполни возможные роли в таблице БД");
        }

        // отправка -1 (если пользователь не был добавлен или (такой логин или емайл существует))
        // отправка 1 (если пользователь добавлен в БД)
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        writer.println(resultAddPerson);
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
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