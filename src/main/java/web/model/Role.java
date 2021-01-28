package web.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = true)
    private String name;

    //@JoinColumn(name = "name")
    //@ManyToMany(mappedBy = "role_id")если это вставить в скобки строкой ниже, при запуске летит ошибка
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<User> users;
   // private User user;

    public long getId(){return id;}

    public void setId(long id){
        this.id = id;
    }
    
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAuthority() {
        return name;
    }

    public void setAuthority(String name) {
        this.name = name;
    }
/*
    public User getUser(){
        return  user;
    }

    public void setUser(User user){
        this.user = user;
    }
*/


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}