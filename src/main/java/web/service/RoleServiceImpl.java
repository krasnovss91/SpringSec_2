package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.RoleDao;
import web.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao){
        this.roleDao = roleDao;
    }

    @Override
    public Role findRoleByName(String name) {
        return roleDao.findRoleByName(name);
    }

    @Override
    public Role getRoleById(long id) {
        return roleDao.getRoleById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }
}

