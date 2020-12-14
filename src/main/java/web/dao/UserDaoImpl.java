package web.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public User findUserByUsername(String name) {
        String hql =" FROM User WHERE username=:name";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("name",name);
        return (User) query.getSingleResult();
    }

    @Override
    public void saveUser(User user) {
     //   sessionFactory.getCurrentSession().save(user);
        if (user.getUsername() == null) {
            sessionFactory.getCurrentSession().save(user);
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
        if (user.getUsername() != null) {
            sessionFactory.getCurrentSession().save(user);
        }
    }

    @Override
    public void deleteUser(long id) {
      sessionFactory.getCurrentSession().delete(getUserById(id));//getUserById(id)
    }
}
