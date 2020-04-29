package models.dao;

import models.entity.Role;

public interface RoleDAO {
    int addRole(Role role);
    Role getRoleByName(String name);
}
