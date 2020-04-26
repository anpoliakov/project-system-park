package models.factory;

import models.dao.*;
import models.dao.mysql.*;
import models.util.ConnectionPool;

public class FactoryMySQL extends FactoryAbstract {
    private ConnectionPool pool;

    public FactoryMySQL() {
        pool = ConnectionPool.getInstance();
    }

    @Override
    public Action getActionDAOImpl() {
        return new ActionMySQLImpl(pool);
    }

    @Override
    public Park getParkDAOImpl() {
        return new ParkMySQLImpl(pool);
    }

    @Override
    public Participants getParticipantsDAOImpl() {
        return new ParticipantsMySQLImpl(pool);
    }

    @Override
    public Person getPersonDAOImpl() {
        return new PersonMySQLImpl(pool);
    }

    @Override
    public Plant getPlantDAOImpl() {
        return new PlantMySQLImpl(pool);
    }

    @Override
    public Role getRoleDAOImpl() {
        return new RoleMySQLImpl(pool);
    }

    @Override
    public Task getTaskDAOImpl() {
        return new TaskMySQLImpl(pool);
    }
}
