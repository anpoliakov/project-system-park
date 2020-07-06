package model.dao;

import model.entity.Action;
import java.util.List;

public interface ActionDAO {
    int addAction(Action action);
    Action getActionById(int id);
    Action getActionByName(String name);
    boolean deleteActionById(int id);
    List<Action> getListAction();
}
