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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Authorities> authorities = new HashSet<>();

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return
    }

    @Override
    public boolean isAccountNonLocked() {
        return 
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
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

    public Set<Authorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authorities> authorities) {
        this.authorities = authorities;
    }
}