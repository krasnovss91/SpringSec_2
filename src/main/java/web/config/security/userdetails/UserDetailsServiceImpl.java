package web.config.security.userdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.model.Role;
import web.model.User;
import web.service.UserService;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {


        User user = null;
        try {
            user = userService.findUserByName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user.equals(null)) {
            throw new UsernameNotFoundException(username);
        }

        User userDetail = new User(user.getUsername(), user.getPassword(), (Role) user.getRoles());

//        return user;//должно быть так; cannot cast org.hibernate.collection.internal.persistentset

        return userDetail;//сюда уже не доходит



/*
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles()
        );

 */

    }


}

