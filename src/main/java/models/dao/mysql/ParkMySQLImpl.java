package models.dao.mysql;

import models.dao.Park;
import models.dao.generic.GenericMySQLImpl;
import models.util.ConnectionPool;

public class ParkMySQLImpl extends GenericMySQLImpl implements Park {
    private ConnectionPool pool;

    public ParkMySQLImpl(ConnectionPool pool) {
        this.pool = pool;
    }
}
