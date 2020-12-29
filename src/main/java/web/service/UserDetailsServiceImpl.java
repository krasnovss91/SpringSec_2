package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import web.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService  userService;

    @Override
    public UserDetails findByUserName(String username){
        User user = userService.findUserByName(username);
        if(user.equals(null)){
            throw new UsernameNotFoundException(username);
        }
        return new UserDetailsServiceImpl(username);
    }

}