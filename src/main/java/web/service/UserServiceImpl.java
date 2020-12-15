package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.dao.UserDaoImpl;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

   private UserDao userDao;

   @Autowired
   public UserServiceImpl(UserDao userDao) {
       this.userDao = userDao;
   }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User getUserById(long id) {

        return userDao.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void editUser(User user) {
        userDao.editUser(user);
    }

    @Override
    public void deleteUser(String name) {
        userDao.deleteUser(userDao.findUserByUsername(name).getUsername());
    }//сюда передать имя пользователя

    @Override
    public User findUserByName(String name) {
        return userDao.findUserByUsername(name);
    }
}
