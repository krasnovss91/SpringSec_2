package web.config.security.userdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import web.model.User;
import web.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    UserService userService;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Query query = entityManager.createQuery("SELECT e FROM User e join fetch e.roles where e.username =: username");

        query.setParameter("username", username);

        User result = null;

        try {
            result = (User) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }
}