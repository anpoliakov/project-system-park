package model.dao;

import model.entity.Role;

public interface RoleDAO {
    int addRole(Role role);
    Role getRoleByName(String name);
    int getRoleIdByName(String name);
    Role getRoleById(int id);
    boolean deleteRoleByID(int id);
}
