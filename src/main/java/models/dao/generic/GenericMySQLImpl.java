package models.dao.generic;

import models.util.ConfigurationManager;
import models.util.ConnectionPool;

import java.util.List;

public abstract class GenericMySQLImpl <T> implements GenericDAO <T>{
    // поле доступно наследникам и текущему классу
    protected ConnectionPool pool;
    protected ConfigurationManager manager;

    public GenericMySQLImpl() {
        pool = ConnectionPool.getInstance();
        manager = ConfigurationManager.getInstance();
    }

}
