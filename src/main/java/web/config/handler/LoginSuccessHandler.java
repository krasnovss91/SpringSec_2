package web.config.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
       // httpServletResponse.sendRedirect("/admin");//перенаправляется на эту страницу в любом случае. Нужно сделать распознавание ролей здесь

        boolean admin = false;

        for(GrantedAuthority auth: authentication.getAuthorities()){
            if("ADMIN".equals(auth.getAuthority())){
                admin = true;
            }
        }

        if(admin){
            httpServletResponse.sendRedirect("/admin");
        }else{
            httpServletResponse.sendRedirect("/user");
        }

        /*

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)  throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);

        boolean admin = false;

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ("ROLE_ADMIN".equals(auth.getAuthority())){
              admin = true;
            }
        }

        if(admin){
          response.sendRedirect("/admin");
        }else{
          response.sendRedirect("/user");
        }
         */
    }
}