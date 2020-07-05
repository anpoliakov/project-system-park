package controller;

import model.dao.PersonDAO;
import model.factory.FactoryAbstract;
import model.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    private int resultOperation = Constants.DEFAULT_OPERATION;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DEL: Запрос на очистку cookies and session");
        req.setCharacterEncoding(Constants.ENCODING_DEFAULT);
        resp.setCharacterEncoding(Constants.ENCODING_DEFAULT);

        HttpSession session = req.getSession(false);
        if(session != null){
            session.invalidate(); //после этого метода, ID который стоит в Cookie становится не действительным
        }

        Cookie[] cookies = req.getCookies();
        for(Cookie cookie : cookies){
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }

        System.out.println("DEL: Cookies and sessi on are DEAD!");
        resultOperation = Constants.OK_OPERATION;
        resp.getWriter().print(resultOperation);
    }
}
