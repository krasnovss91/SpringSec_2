package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findUserByUsername(String username) {
        return null;
    }

    @Override
    public void saveUser(User user) {
        sessionFactory.getCurrentSession().save(user);
        if (user.getCar() != null) {
            sessionFactory.getCurrentSession().save(user.getCar());
        }

    }

    @Override
    public User getUserById(long id) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public void editUser(User user) {

    }

    @Override
    public void deleteUser(long id) {

    }
}
