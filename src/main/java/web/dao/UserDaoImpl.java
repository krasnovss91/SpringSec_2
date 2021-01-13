package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
    public User findUserByUsername(String name) {

        return entityManager.find(User.class, name);
    }

    @Override
    public void saveUser(User user) {
        if (user.getUsername() != null) {
            entityManager.persist(user);
        }

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