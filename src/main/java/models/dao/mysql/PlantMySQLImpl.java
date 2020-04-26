package models.dao.mysql;

import models.dao.Plant;
import models.dao.generic.GenericMySQLImpl;
import models.util.ConnectionPool;

public class PlantMySQLImpl extends GenericMySQLImpl implements Plant {
    private ConnectionPool pool;

    public PlantMySQLImpl(ConnectionPool pool) {
        this.pool = pool;
    }
}
