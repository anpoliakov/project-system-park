package models.dao;

import java.util.List;

public interface ActionDAO {
    int add(ActionDAO action);
    ActionDAO getById(long id);
    boolean update(ActionDAO action);
    boolean deleteById(long id);
    List<ActionDAO> getAll();
}
