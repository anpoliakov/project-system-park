package model.factory;

import model.dao.*;
import model.dao.mysql.*;

public class FactoryMySQL extends FactoryAbstract {

    public FactoryMySQL(){
    }

    @Override
    public ActionDAO getActionDAOImpl() {
        return new ActionMySQLImpl();
    }

    @Override
    public ParkDAO getParkDAOImpl() {
        return new ParkMySQLImpl();
    }

    @Override
    public ParticipantsDAO getParticipantsDAOImpl() {
        return new ParticipantsMySQLImpl();
    }

    @Override
    public PersonDAO getPersonDAOImpl() {
        return new PersonMySQLImpl();
    }

    @Override
    public PlantDAO getPlantDAOImpl() {
        return new PlantMySQLImpl();
    }

    @Override
    public RoleDAO getRoleDAOImpl() {
        return new RoleMySQLImpl();
    }

    @Override
    public TaskDAO getTaskDAOImpl() {
        return new TaskMySQLImpl();
    }
}
