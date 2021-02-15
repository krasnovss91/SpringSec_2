package web.service;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    User getUserById(long id);

    List<User> getAllUsers();

    void editUser(User user);

    void deleteUser(long id);

    User findUserByName(String name);

    Role getRoleByName(String name);

    public void setUserRoles(User user);


}

