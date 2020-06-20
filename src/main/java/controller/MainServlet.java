package controller;

import com.google.gson.Gson;
import model.entity.Person;
import model.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


/* Замена стандартному фильтру (на это есть причины) */
@WebServlet(value = "/mainservlet")
public class MainServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(MainServlet.class);
    private String encoding;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("### Init MainServlet");

        encoding = config.getInitParameter("encoding");
        if(encoding == null){
            encoding = Constants.ENCODING_DEFAULT;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("### doGet MainServlet");
        setEncoding(req,resp);

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        session.setAttribute("login", login);
        session.setAttribute("password", password);
        getServletContext().getRequestDispatcher(Constants.PATH_LOGIN_SERVLET).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("### doPost MainServlet");
        setEncoding(req,resp);

        Gson gson = new Gson();
        HttpSession session = req.getSession();

        String stringJson = getDataFromRequest(req);
        System.out.println("Данные: " + stringJson);

        session.setAttribute("person", gson.fromJson(stringJson, Person.class));
        getServletContext().getRequestDispatcher(Constants.PATH_REGISTER_SERVLET).forward(req, resp);
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
    private void setEncoding(HttpServletRequest req, HttpServletResponse resp){
        try {
            req.setCharacterEncoding(encoding);
        } catch (UnsupportedEncodingException e) {
            logger.error("Кодировка символов не поддерживается.",e);
        }
        resp.setContentType("text/html");
    }
}
