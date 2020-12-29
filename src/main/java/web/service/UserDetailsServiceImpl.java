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
    UserService userService;

    public User findByUserName(String username) {
        User user = userService.findUserByName(username);
        if (user.equals(null)) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);

    }
    /*
        @Autowired
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getUserByUsername(username); //userDAO == null Causing NPE
        if (user == null)
            throw new UsernameNotFoundException("Oops!");

        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));

        return new org.springframework.security.core.userdetails
                .User(user.getLogin(), user.getPassword(), authorities);
    }

@Override
    public List<User> getUsers() {
        return userDAO.getUsers();//userDAO !=null
    }
     */

}