package web.model;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(name = "USERNAME", unique = true)
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ENABLED", nullable = false)
    private boolean enabled;
    
  //  @OneToMany(cascade = CascadeType.DETACH, mappedBy = "user", fetch = FetchType.EAGER)
    //mappedBy="user_id"
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "role_id")
    )

    private Set<Role> roles;
   // private Set<Role> authorities = new HashSet<>();
   
    public User() {}

    public User(String username, String password, Role roles){
        this.username = username;
        this.password = password;
        this.roles = new HashSet<>(Arrays.asList(roles));
       // this.authorities = new HashSet<>(Arrays.asList(roles));
    }


    public long getId(){return id;}

    public void setId(long id){
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public Set<Role> getAuthorities() {
      //  return authorities;
        return roles;
    }

    public Set<Role> getRoles(){
        return roles;
    }
   public  void setRoles (Set<Role> roles){
        //this.authorities = authorities;
        this.roles = roles;
    }

}