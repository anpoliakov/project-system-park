package controller;

import model.dao.PersonDAO;
import model.entity.Person;
import model.factory.FactoryAbstract;
import model.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {

    final static Logger logger = Logger.getLogger(Login.class);
    private FactoryAbstract factoryMySQL;
    private PersonDAO personDAOImpl;

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("Servlet Login READY!");
        factoryMySQL = FactoryAbstract.getFactoryMySQL();
        personDAOImpl = factoryMySQL.getPersonDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int resultLogin = Constants.DEFAULT_OPERATION;
        System.out.println("#Request POST in Login Servlet");
        req.setCharacterEncoding(Constants.ENCODING_DEFAULT);
        resp.setCharacterEncoding(Constants.ENCODING_DEFAULT);

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Person person = personDAOImpl.getPersonByLoginAndPassword(login, password);

        //если пользователь есть в БД
        if(person != null){
            System.out.println("OK: There is Person in DataBase");
            int id = person.getId();

            //стартуем сессию
            HttpSession session = req.getSession();
            session.setMaxInactiveInterval(600); //СЕССИЯ ЖИВЁТ 10 МИНУТ
            System.out.println("ID SESSION: "  + session.getId());
            if(session == null){
                System.err.println("Серьёзная ошибка получения сессии");
            }

            //пишем в сессию что мы авторизовались, а так же ID и Login Person
            session.setAttribute("auth", true);
            session.setAttribute("person", person);

            //настраиваем cookies и добавляем в response
            //добавляем в куки ID сессии и сохраняем этот ID в БД
            //когда пользователь заходит на страницу - проверяем сходится ли ID - если нет, перекидываем на логин
            Cookie loginPerson = new Cookie("loginPerson", login);
            Cookie idPerson = new Cookie("idPerson", String.valueOf(id));
            Cookie rolePerson = new Cookie("rolePerson", person.getRole());

            loginPerson.setMaxAge(60*60*24);
            idPerson.setMaxAge(60*60*24);
            rolePerson.setMaxAge(60*60*24);

            resp.addCookie(loginPerson);
            resp.addCookie(idPerson);
            resp.addCookie(rolePerson);

            //добавляем ID сессии в БД для конкретного человека
            boolean isCookieAdd = personDAOImpl.setCookie(id, session.getId());
            if(isCookieAdd){
                System.out.println("Cookie of person with id №" + id + " set!");
            }
            resultLogin = Constants.OK_OPERATION;
        }else {
            resultLogin = Constants.ERROR_OPERATION;
        }

        resp.getWriter().println(resultLogin);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("#Request GET:");
//        req.setCharacterEncoding(Constants.ENCODING_DEFAULT);
//        resp.setCharacterEncoding(Constants.ENCODING_DEFAULT);
//
//        String login = req.getParameter("login");
//        String password = req.getParameter("password");
//        Person person = personDAOImpl.getPersonByLoginAndPassword(login, password);
//
//        //если пользователь есть в БД
//        if(person != null){
//            System.out.println("OK: There is Person in DataBase");
//            int id = person.getId();
//
//            //стартуем сессию
//            HttpSession session = req.getSession();
//            System.out.println("ID SESSION: "  + session.getId());
//            if(session == null){
//                System.err.println("Серьёзная ошибка получения сессии");
//            }
//
//            //пишем в сессию что мы авторизовались, а так же ID и Login Person
//            session.setAttribute("auth", true);
//            session.setAttribute("idPerson", id);
//            session.setAttribute("loginPerson", login);
//
//            //настраиваем cookies и добавляем в response
//            Cookie loginCookie = new Cookie("login", login);
//            Cookie keyCookie = new Cookie("key", session.getId());
//            loginCookie.setMaxAge(60*60*24);
//            keyCookie.setMaxAge(60*60*24);
//            resp.addCookie(loginCookie);
//            resp.addCookie(keyCookie);
//
//            boolean isCookieAdd = personDAOImpl.setCookie(id, session.getId());
//            if(isCookieAdd){
//                System.out.println("We are here 2");
//                System.out.println("Cookie of person with id №" + id + " update!");
//            }
//            resultLogin = Constants.OK_OPERATION;
//        }else {
//            resultLogin = Constants.ERROR_OPERATION;
//        }
//
//        resp.getWriter().println(resultLogin);
    }
}


// устанавливаю куки - при каждом вызове от клиента эти куки будут возвращаться в сервлет