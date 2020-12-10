package web.service;

import web.dao.UserDaoImpl;
import web.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    UserDaoImpl dao = new UserDaoImpl();

    @Override
    public void saveUser(User user) {
        dao.saveUser(user);
    }

    @Override
    public User getUserById(long id) {

        return dao.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @Override
    public void editUser(User user) {
        dao.editUser(user);
    }

    @Override
    public void deleteUser(long id) {
        dao.deleteUser(id);
    }

    @Override
    public User findUserByName(String name) {
        return dao.findUserByUsername(name);
    }
}
