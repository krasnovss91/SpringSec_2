package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public User findUserByUsername(String name) {
        String hql = " FROM User WHERE username=:name";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("name", name);
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

        sessionFactory.getCurrentSession().save(user);
 //        }
    }

    @Override
    public void deleteUser(String name) {
        sessionFactory.getCurrentSession().delete(findUserByUsername(name));
    }

}