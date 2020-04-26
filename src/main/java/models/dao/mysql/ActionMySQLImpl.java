package models.dao.mysql;

import models.dao.Action;
import models.dao.generic.GenericMySQLImpl;
import models.util.ConnectionPool;

public class ActionMySQLImpl extends GenericMySQLImpl<models.entity.Action> implements Action {
    private ConnectionPool pool;

    public ActionMySQLImpl(ConnectionPool pool) {
        this.pool = pool;
    }

}
