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
import java.util.List;


@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User findUserByUsername(String name){

          // return entityManager.find(User.class, name);//переделать на HQl
        /*
                String hql =" FROM User WHERE car=:car";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("car",car);
        return (User) query.getSingleResult();
         */
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
    public void deleteUser(long id) {// заменить на работу с id
        // public void deleteUser(User userToBeDeleted){

        //  entityManager.remove(userToBeDeleted);
        User userToBeDeleted = getUserById(id);

        if (userToBeDeleted != null) {
            entityManager.remove(userToBeDeleted);
        }

    }

}