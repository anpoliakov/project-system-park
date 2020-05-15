package models.dao.generic;

import models.util.ConfigurationManager;
import models.util.ConnectionPool;

public abstract class GenericMySQLImpl <T> implements GenericDAO <T>{
    protected ConnectionPool pool;
    protected ConfigurationManager manager;

    public GenericMySQLImpl() {
        pool = ConnectionPool.getInstance();
        manager = ConfigurationManager.getInstance();
    }
}
