package models.dao.mysql;

import models.dao.Participants;
import models.dao.generic.GenericMySQLImpl;
import models.util.ConnectionPool;

public class ParticipantsMySQLImpl extends GenericMySQLImpl implements Participants {
    private ConnectionPool pool;

    public ParticipantsMySQLImpl(ConnectionPool pool) {
        this.pool = pool;
    }
}
