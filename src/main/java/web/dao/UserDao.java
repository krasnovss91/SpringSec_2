package web.dao;


import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;

import java.util.List;

//@Transactional
public interface UserDao {
    User findUserByUsername(String username);

    void saveUser(User user);

    User getUserById(long id);

    List<User> getAllUsers();

    void editUser(User user);

    void deleteUser(long id);

    Role getRoleByName(String name);

}