package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import web.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

 //   public org.springframework.security.core.userdetails.User loadUserByUsername(String username) {
    @Override
    public UserDetails loadUserByUsername(String username){
        User user = null;
        try {
            user = userService.findUserByName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user.equals(null)) {
            throw new UsernameNotFoundException(username);
        }

     //   List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getAuthorities()));

     //  return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),user.getAuthorities());
        return new User(user.getUsername(),user.getPassword(),user.getRoles());

    }

}