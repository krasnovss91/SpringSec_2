package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;

    }

    @Override
    public void saveUser(User user) {
        setUserRoles(user);
        String password = user.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);

        user.setPassword(encodedPassword);
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

        setUserRoles(user);
        String password_1 = user.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        
  //          long id = user.getId();
            String encodedPassword_1 = passwordEncoder.encode(password_1);
//проверять пароль и шифровать только если меняется при редактировании. Не сравнивать пароль с самим собой
            user.setPassword(encodedPassword_1);//password_1 и encodedPassword1- не совпадают
        userDao.editUser(user);//проверить и шифровать пароль уже после занесения в бд?
/*
        User user1 = getUserById(id);

        String password_2 = user1.getPassword();

        String encodedPassword_2 = passwordEncoder.encode(password_2);

        if(password_1 != password_2){
            user1.setPassword(encodedPassword_2);
            userDao.editUser(user);
        }
*/
        
    }

    @Transactional
    public void setUserRoles(User user) {
        user.setRoles(user
                .getRoles()
                .stream()
                .map(role -> userDao.getRoleByName(role.getName()))
                .collect(Collectors.toSet()));
    }



    @Override
    public void deleteUser(long id) {
        userDao.deleteUser(userDao.getUserById(id).getId());
    }

    @Override
    public User findUserByName(String name) {
        return userDao.findUserByUsername(name);

    }

    @Override
    public Role getRoleByName(String name) {
        return userDao.getRoleByName(name);
    }
}
