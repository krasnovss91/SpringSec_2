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
        try {
            sessionFactory.getCurrentSession().update(user);
        } catch (Exception e){
            e.printStackTrace();
        }
/*
        String name = user.getUsername();
        User user1 = findUserByUsername(name);

        if (user.getUsername().equals(user1.getUsername())){
   //     if (user.getUsername() != null) {//проблема в этой строке. Сравнить передаваемого в метод юзера с юзером из базы
            sessionFactory.getCurrentSession().save(user);
        }

        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
           // session.update(user);
            session.save(user);
            tx.commit();
            session.flush();
        } catch (RuntimeException e) {
            tx.rollback();
            e.printStackTrace();
        }
         */

    }

    @Override
    public void deleteUser(String name) {
        sessionFactory.getCurrentSession().delete(findUserByUsername(name));
    }
}
