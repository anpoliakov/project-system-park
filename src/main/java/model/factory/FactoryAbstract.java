package model.factory;

import model.dao.*;

public abstract class FactoryAbstract {
    // нужна для создпния конкретной фабрики под определённую БД
    // говорит все возможным фабрикам БД - какие должны быть методы у них

    public abstract ActionDAO getActionDAOImpl();
    public abstract ParkDAO getParkDAOImpl();
    public abstract ParticipantsDAO getParticipantsDAOImpl();
    public abstract PersonDAO getPersonDAOImpl();
    public abstract PlantDAO getPlantDAOImpl();
    public abstract RoleDAO getRoleDAOImpl();
    public abstract TaskDAO getTaskDAOImpl();


    public static FactoryAbstract getFactoryMySQL(){
        return new FactoryMySQL();
    }

}
