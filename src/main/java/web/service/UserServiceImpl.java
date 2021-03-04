package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public void editUser(User user, String password) {
        User userFromDB = userDao.getUserById(user.getId());//берём юзера из бд по ID юзера с UI
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String encodedPassword = passwordEncoder.encode(password);

        if (!passwordEncoder.matches(password, userFromDB.getPassword())) {//принимает первый пароль только в нешифрованном виде
             // user.setPassword(password);//так не перезаписывается, если не изменять. Но если изменяю, сетится в нешифрованном виде
          //  user.setPassword(passwordEncoder.encode(password));//ещё раз шифровать нельзя, иначе перезаписывается каждый раз
            user.setPassword(encodedPassword);//так тоже перезаписывается каждый раз
        }
        setUserRoles(user);
        userDao.editUser(user);
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
