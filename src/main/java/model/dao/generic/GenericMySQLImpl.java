package model.dao.generic;

import model.util.ConfigurationManager;
import model.util.ConnectionPool;

public abstract class GenericMySQLImpl <T> implements GenericDAO <T>{
    protected ConnectionPool pool;
    protected ConfigurationManager manager;

    public GenericMySQLImpl() {
        pool = ConnectionPool.getInstance();
        manager = ConfigurationManager.getInstance();
    }
}
