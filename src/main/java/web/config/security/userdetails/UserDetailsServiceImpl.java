package web.config.security.userdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
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
            user = userService.findUserByName(username);//юзер подтягивается без проблем
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user.equals(null)) {
            throw new UsernameNotFoundException(username);
        }
       // return user;//при входе падает здесь
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles()
        );

    }

}

