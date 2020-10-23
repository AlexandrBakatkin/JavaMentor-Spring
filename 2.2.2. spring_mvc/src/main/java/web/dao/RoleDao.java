package web.dao;

import web.model.Role;

public interface RoleDao {
    Role getRole(int id);

    Role getRoleByName(String name);

    void add(Role role);
}
