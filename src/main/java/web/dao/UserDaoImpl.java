package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.config.SecurityConfig;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

  //  @Autowired
 //   PasswordEncoder passwordEncoder;


    @Override
    public User findUserByUsername(String name) {

        return entityManager.find(User.class, name);
    }

    @Override
    public void saveUser(User user) {
        String password = user.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);

        user.setPassword(encodedPassword);
        if (user.getUsername() != null) {
            entityManager.persist(user);
        }
    //String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword);
    }

    @Override
    public User getUserById(long id) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {

        return entityManager.createQuery("SELECT e FROM User e", User.class).getResultList();

    }

    @Override
    public void editUser(User user) {
        //и здесь шифрование паролей?
        entityManager.merge(user);

    }

    @Override
    public void deleteUser(String name) {
     // public void deleteUser(User userToBeDeleted){

      //  entityManager.remove(userToBeDeleted);
        User userToBeDeleted = findUserByUsername(name);

        if (userToBeDeleted != null) {
            entityManager.remove(userToBeDeleted);
        }

    }

}