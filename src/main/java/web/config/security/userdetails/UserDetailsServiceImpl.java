package web.config.security.userdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User result = userDao.findUserByUsername(username);

        return result;

    }
}