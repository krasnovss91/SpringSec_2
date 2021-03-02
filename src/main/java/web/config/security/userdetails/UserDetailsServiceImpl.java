package web.config.security.userdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
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
   // @Transactional
    public UserDetails loadUserByUsername(String username) {
  Query query = entityManager.createQuery("SELECT e FROM User e join fetch e.roles where e.username =: username");

  query.setParameter('username',username);
        
/*
        User user = null;
        try {
            user = userService.findUserByName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user.equals(null)) {
            throw new UsernameNotFoundException(username);
        }

       return user;
       /*
public Person findByNameWithJoinFech(String name) {
  Query query = entityManager.createQuery('select p from Person p join fetch p.lazyDogs where p.name = :name');
  query.setParameter('name', name);

  Person result = null;
  try {
   result = (Person) query.getSingleResult();
  } catch (NoResultException e) {
   // no result found
  }

  return result;
 }
 */


/*
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles()
        );

 */
    }


}

