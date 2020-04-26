package models.dao.mysql;

import models.dao.Role;
import models.dao.generic.GenericMySQLImpl;
import models.util.ConnectionPool;

public class RoleMySQLImpl extends GenericMySQLImpl implements Role {
    private ConnectionPool pool;

    public RoleMySQLImpl(ConnectionPool pool) {
        this.pool = pool;
    }
}
