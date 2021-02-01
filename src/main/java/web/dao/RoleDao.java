package web.dao;

import web.model.Role;

import java.util.List;

public interface RoleDao {
    Role findRoleByName (String name);

    Role getRoleById(long id);

    List<Role> getAllRoles();
}
