package models.dao.mysql;

import models.dao.Person;
import models.dao.generic.GenericMySQLImpl;
import models.util.ConnectionPool;

public class PersonMySQLImpl extends GenericMySQLImpl implements Person {
    private ConnectionPool pool;

    public PersonMySQLImpl(ConnectionPool pool) {
        this.pool = pool;
    }

}
