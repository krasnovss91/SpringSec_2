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
    @Column(name = "role_id")
    private long id;

    @Column(name = "name", unique = true)
    private String name;

    public Role() {}

    public Role(String name) {
        this.name = name;
    }
    
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


}