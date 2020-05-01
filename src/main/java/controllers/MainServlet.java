package controllers;

import models.dao.ParkDAO;
import models.dao.PersonDAO;
import models.dao.RoleDAO;
import models.entity.Park;
import models.entity.Person;
import models.entity.Role;
import models.factory.FactoryAbstract;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FactoryAbstract factoryMySQL = FactoryAbstract.getFactoryMySQL();

        RoleDAO roleDAOImpl = factoryMySQL.getRoleDAOImpl();
        Role role1 = new Role("OWNER");
        Role role2 = new Role("FORESTER");
        System.out.println("Статус добавления роли OWNER - " + roleDAOImpl.addRole(role1));
        System.out.println("Статус добавления роли FORESTER - " + roleDAOImpl.addRole(role2));
        Role ownerRole = roleDAOImpl.getRoleByName("owner");
        Role foresterRole = roleDAOImpl.getRoleByName("foRESTER");

        PersonDAO personDAOImpl = factoryMySQL.getPersonDAOImpl();
        Person person1 = new Person ("Андрей", "Игоревич", "Поляков", "Qwerty", "Qwerty123", "Andrew@gmail.com", ownerRole.getId());
        Person person2 = new Person ("Дима", "Семёнович", "Козяк", "Qqqqq", "Qqqqq111", "Sddd@gmail.com", foresterRole.getId());
        Person person3 = new Person ("Дима", "Грегорьевич", "Молото", "Ewrr", "Ewrr222", "Asdok@gmail.com", foresterRole.getId());
        System.out.println(personDAOImpl.addPerson(person1));
        System.out.println(personDAOImpl.addPerson(person2));
        System.out.println(personDAOImpl.addPerson(person3));

        System.out.println("Список FORESTERS from DataBase ---------------");
        List<Person> personsForester = personDAOImpl.getPersonsByRole(foresterRole.getId());
        for (Person person: personsForester) {
            System.out.println(person.toString());
        }

        Person p1 = personDAOImpl.getPersonById(1);
        Person p2 = personDAOImpl.getPersonById(2);


        /*  ПРОТЕСТИТЬ - КОГДА ДОДЕЛАЮ СУЩНОСТЬ PERSON */
        ParkDAO parkDAOImpl = factoryMySQL.getParkDAOImpl();

        Park park1 = new Park("Парк А", p1.getId());
        Park park2 = new Park("Парк А", p2.getId());

        System.out.println(parkDAOImpl.addPark(park1));
        System.out.println(parkDAOImpl.addPark(park2));

    }
}

















/*
*
* 1) Избавиться от шума название DAO - и так ясно что в папке dao находятся dao
* 2) Есть ли разница и как работает закрытие try-resource + finally(в котором свой порядок закрытия ResultSet + Connection, ведь Statement закроется в ресурсах)
* 3) в ДАО Park - есть ошибка в таблице? теперь может быть таких 2:     id|Парк А|1   (2 и 3 колонка повторяются 2 раза)
* 4) UPDATE - операцию добавить в impl сущностей
* */