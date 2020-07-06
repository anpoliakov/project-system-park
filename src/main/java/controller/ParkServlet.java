package controller;

import model.dao.ParkDAO;
import model.entity.Park;
import model.entity.Person;
import model.factory.FactoryAbstract;
import model.factory.FactoryMySQL;
import model.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/park")
public class ParkServlet extends HttpServlet {
    private FactoryAbstract factoryMySQL;

    @Override
    public void init() throws ServletException {
        super.init();
        factoryMySQL = new FactoryMySQL();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(Constants.ENCODING_DEFAULT);
        resp.setCharacterEncoding(Constants.ENCODING_DEFAULT);
        resp.setContentType("text/html");

        HttpSession session = req.getSession();
        Person person = (Person) session.getAttribute("person");

        ParkDAO parkDAOImpl = factoryMySQL.getParkDAOImpl();
        List<Park> parks = parkDAOImpl.getParksByOwnerId(person.getId());
        session.setAttribute("parks", parks);

        getServletContext().getRequestDispatcher("/view/jsp/parks.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(Constants.ENCODING_DEFAULT);
        resp.setCharacterEncoding(Constants.ENCODING_DEFAULT);
        resp.setContentType("text/html");

        List<Park> parks = null;

        HttpSession session = req.getSession();
        Person person = (Person) session.getAttribute("person");

        //Получаем информацию о то - что необходимо сделать
        String action = req.getParameter("action");
        System.out.println("ACTION = " + action);

        //если добавить новый парк
        if(action.equals("ADD_PARK")){
            String name = req.getParameter("name").trim();
            if(name != null){
                ParkDAO parkDAOImpl = factoryMySQL.getParkDAOImpl();
                int resultOperation = parkDAOImpl.addPark(new Park(name, person.getId()));
                System.out.println("Результат добавления PARK- " + resultOperation);

                parks = parkDAOImpl.getParksByOwnerId(person.getId());
            }
        }

        //если удалить парк
        if(action.equals("DEL_PARK")){
            int idDelPark = Integer.parseInt(req.getParameter("idDelPark"));
            if(idDelPark >= 0){
                ParkDAO parkDAOImpl = factoryMySQL.getParkDAOImpl();
                boolean isDelPark = parkDAOImpl.deleteParkById(idDelPark);
                System.out.println("Результат удаления PARK- " + isDelPark);

                parks = parkDAOImpl.getParksByOwnerId(person.getId());
            }
        }


        //обновляю List <Park> в сессии:
        session.setAttribute("parks", parks);
        //и готовлю блок для отправки клиенту, с обновлённым списком парков:
        getServletContext().getRequestDispatcher("/view/jsp/parks.jsp").forward(req,resp);
    }
}
