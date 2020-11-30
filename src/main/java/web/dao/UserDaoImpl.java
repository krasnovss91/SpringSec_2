package web.dao;

import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

public class UserDaoImpl implements UserDao {
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public User findUserByUsername(String username) {
        return  entityManager.find(User.class, username);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserById(long id) {

        return entityManager.find(User.class, id);

    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select e from User e", User.class).getResultList();
    }


    @Override
    public void editUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(long id) {
        User userToBeDeleted = entityManager.getReference(User.class, id);

        if (userToBeDeleted != null) {
            entityManager.remove(userToBeDeleted);
        }

    }
}
