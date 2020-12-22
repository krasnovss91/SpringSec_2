package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
// пересадить с SessionFactory  на EntityManager+@PersistenceContext
@Transactional
@Repository
public class UserDaoImpl implements UserDao {

  //  @Autowired
  //  private SessionFactory sessionFactory;
  @PersistenceContext
  EntityManager entityManager;


    @Override
    public User findUserByUsername(String name) {
        /*
        String hql = " FROM User WHERE username=:name";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("name", name);
        return (User) query.getSingleResult();
         */
        return entityManager.find(User.class, name);
    }

    @Override
    public void saveUser(User user) {
        if (user.getUsername() != null) {
           // sessionFactory.getCurrentSession().save(user);
            entityManager.persist(user);
        }

    }

    @Override
    public User getUserById(long id) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        //TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
       // return query.getResultList();
        return entityManager.createQuery("select e from User e", User.class).getResultList();
    }

    @Override
    public void editUser(User user) {
         entityManager.merge(user);
      //  sessionFactory.getCurrentSession().save(user);

    }

    @Override
    public void deleteUser(String name) {
        //sessionFactory.getCurrentSession().delete(findUserByUsername(name));

    }

}