package controller;

import com.google.gson.Gson;
import model.dao.PersonDAO;
import model.entity.Person;
import model.factory.FactoryAbstract;
import model.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(LoginServlet.class);

    private int resultLogin = Constants.DEFAULT_OPERATION;
    private FactoryAbstract factoryMySQL;
    private PersonDAO personImpl;
    private HttpSession session;
    private ServletContext context;

    private Person person;
    private Gson gson;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.context = config.getServletContext();
        factoryMySQL = FactoryAbstract.getFactoryMySQL();
        System.out.println("# Init LoginServlet");
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("html/text;charset=UTF-8");
        System.out.println("# doGet LoginServlet");
        personImpl = factoryMySQL.getPersonDAOImpl();

        session = req.getSession();
        String login = (String) session.getAttribute("login");
        String password = (String) session.getAttribute("password");

        System.out.println("login = " + login + ", password = " + password);

        // ПРОВЕРКА: есть ли пользователь с таким логином и паролем
        Person person = personImpl.getPersonByLoginAndPassword(login, password);

        System.out.println("PERSON:" + person);
        if(person != null){
            System.out.println("Person NOT NULL");
            session.setAttribute("person", person);

            req.getRequestDispatcher("/view/jsp/cabinet.jsp").forward(req,resp);
//            resp.sendRedirect(req.getContextPath()+"/view/jsp/cabinet.jsp");
            return;
        }else {
            System.out.println("PERSON = NULL");
//            resultLogin = Constants.ERROR_OPERATION;
//            PrintWriter writer = resp.getWriter();
//            writer.println(resultLogin);
//            writer.close();
        }
    }
}
