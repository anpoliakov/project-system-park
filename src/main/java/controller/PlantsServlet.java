package controller;

import model.dao.PlantDAO;
import model.entity.Person;
import model.entity.Plant;
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

@WebServlet("/plants")
public class PlantsServlet extends HttpServlet {
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
        PlantDAO plantDAOImpl = factoryMySQL.getPlantDAOImpl();

        int idPark = Integer.parseInt(req.getParameter("valueButton"));
        System.out.println("Полученный ID Park for PLANT: " + idPark);

        List<Plant> plants = plantDAOImpl.getListPlantsByParkId(idPark);
        session.setAttribute("plants", plants);

        getServletContext().getRequestDispatcher("/view/jsp/plants.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(Constants.ENCODING_DEFAULT);
        resp.setCharacterEncoding(Constants.ENCODING_DEFAULT);
        resp.setContentType("text/html");

        HttpSession session = req.getSession();
        Person person = (Person) session.getAttribute("person");
        PlantDAO plantDAOImpl = factoryMySQL.getPlantDAOImpl();

        String namePlant = req.getParameter("namePlant");
        String descriptionPlant = req.getParameter("descriptionPlant");


    }


}
