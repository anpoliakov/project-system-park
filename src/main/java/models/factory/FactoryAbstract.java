package models.factory;

import models.dao.*;

public abstract class FactoryAbstract {
    // нужна для создпния конкретной фабрики под определённую БД
    // говорит все возможным фабрикам БД - какие должны быть методы у них

    public abstract Action getActionDAOImpl();
    public abstract Park getParkDAOImpl();
    public abstract Participants getParticipantsDAOImpl();
    public abstract Person getPersonDAOImpl();
    public abstract Plant getPlantDAOImpl();
    public abstract Role getRoleDAOImpl();
    public abstract Task getTaskDAOImpl();


    public static FactoryAbstract getMySQLDAOFactory(){
        return new FactoryMySQL();
    }

}
