package web.service;

import web.model.Role;

public interface RoleService {
    Role findRoleByName(String name);
}
