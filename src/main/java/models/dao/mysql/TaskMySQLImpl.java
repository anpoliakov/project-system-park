package models.dao.mysql;

import models.dao.Task;
import models.dao.generic.GenericMySQLImpl;
import models.util.ConnectionPool;

public class TaskMySQLImpl extends GenericMySQLImpl implements Task {
    private ConnectionPool pool;

    public TaskMySQLImpl(ConnectionPool pool) {
        this.pool = pool;
    }

}
