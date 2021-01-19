package web.model;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @Column(name = "USERNAME", unique = true)
    private String username;

    @Column(name = "PASSWORD")
    private String password;


    @Column(name = "ROLE")
    private String role;

    @Column(name = "ENABLED", nullable = false)
    private boolean enabled;
    
   // @OneToMany(cascade = CascadeType.DETACH, mappedBy = "user", fetch = FetchType.EAGER)
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)


     @JoinTable(
             name = "user_roles",
             joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"),
             inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id")
     )

    private Set<Role> roles;
   // private Set<Role> authorities = new HashSet<>();

    public User() {}
    /*
        public User(String username, String password, String name, String lastName, String email, Role... roles) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.roles = new HashSet<>(Arrays.asList(roles));
    }
     */

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public Set<Role> getAuthorities() {
       // return authorities;
        return roles;
    }

    public void setAuthorities(Set<Role> authorities) {
       // this.authorities = authorities;
        this.roles = roles;
    }

}