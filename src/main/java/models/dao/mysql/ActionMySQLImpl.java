package models.dao.mysql;

import models.dao.ActionDAO;
import models.dao.generic.GenericMySQLImpl;

import java.util.List;

public class ActionMySQLImpl extends GenericMySQLImpl<models.entity.Action> implements ActionDAO {

    public ActionMySQLImpl() {}

    @Override
    public int add(ActionDAO action) {
        return 0;
    }

    @Override
    public ActionDAO getById(long id) {
        return null;
    }

    @Override
    public boolean update(ActionDAO action) {
        return false;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override
    public List<ActionDAO> getAll() {
        return null;
    }
}
