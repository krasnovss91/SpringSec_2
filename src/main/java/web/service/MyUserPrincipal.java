package web.service;

import org.springframework.security.core.userdetails.UserDetails;
import web.model.User;

public class MyUserPrincipal implements UserDetails {
    private User user;

    public MyUserPrincipal(User user) {
        this.user = user;
    }
}
