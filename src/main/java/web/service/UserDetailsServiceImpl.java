package web.service;

import org.springframework.security.core.GrantedAuthority;
import web.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public org.springframework.security.core.userdetails.User loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findUserByName(s);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                (Collection<? extends GrantedAuthority>) user.getAuthorities()
        );
    }
}