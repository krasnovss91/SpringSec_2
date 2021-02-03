package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.config.SecurityConfig;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User findUserByUsername(String username) {

        String hql = "FROM User Where username=:username";
        Query query = entityManager.createQuery(hql).setParameter("username", username);

        return (User) query.getSingleResult();

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

    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {

        return entityManager.createQuery("SELECT e FROM User e", User.class).getResultList();

    }

    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery("from Role where name =:name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public void editUser(User user) {

        String password = user.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);

        user.setPassword(encodedPassword);

        entityManager.merge(user);

    }

    @Override
    public void deleteUser(long id) {

        User userToBeDeleted = getUserById(id);

        if (userToBeDeleted != null) {
            entityManager.remove(userToBeDeleted);
        }

    }

}