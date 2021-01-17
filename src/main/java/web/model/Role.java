package web.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @Column(name = "AUTHORITY")
    private String authority;

    @ManyToOne//сделать связь так, чтобы пользователь имел несколько ролей
    @JoinColumn(name = "USERNAME")
   // @ManyToMany(mappedBy = "authorities")
  //  private List<User> users;
    private User user;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User getUser(){
        return  user;
    }

    public void setUser(User user){
        this.user = user;
    }

    /*
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
*/
}