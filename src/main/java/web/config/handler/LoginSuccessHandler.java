package web.config.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.AccessException;
import java.util.Set;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private UserService userService;

    @Autowired
    public LoginSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {

        boolean admin = false;

        for (GrantedAuthority auth : authentication.getAuthorities()) {//летит здесь
            if ("ADMIN".equals(auth.getAuthority())) {

                admin = true;//сюда уже не долетает
            }
        }


        if (admin) {
            httpServletResponse.sendRedirect("/admin");
        } else {
            httpServletResponse.sendRedirect("/user");
        }

    


        /*
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ADMIN")) {
            httpServletResponse.sendRedirect("/admin");
        } else if (roles.contains("USER")) {
            httpServletResponse.sendRedirect("/user");
        }
*/
/*

        try {

            boolean admin = false;

         //   for (GrantedAuthority authority : authentication.getPrincipal()) {
                Role role = userService.getRoleByName(authentication.getPrincipal().toString());

                if ("ADMIN".equals(role)) {
                    admin = true;
                }
       //     }

            for (GrantedAuthority auth : authentication.getAuthorities()) {
                if ("ADMIN".equals(auth.getAuthority())) {
                    //       String role = userService.getRoleByName(auth.getPrincipal)
                    //    if("ADMIN".equals(role)){
                    admin = true;
                }
            }


            if (admin) {
                httpServletResponse.sendRedirect("/admin");
            } else {
                httpServletResponse.sendRedirect("/user");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        */
    }
}