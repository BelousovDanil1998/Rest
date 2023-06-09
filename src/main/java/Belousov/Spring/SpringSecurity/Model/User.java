package Belousov.Spring.SpringSecurity.Model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;


    @Column
    private String password;


    @Column
    private String firstName;



    @ManyToMany(cascade=CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private Set<Role> roleSet;


    public User(String email, String password, String firstName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
    }


    public User(String email, String password, String firstName, Set<Role> roleSet) {
        this(firstName, email, password);
        this.roleSet = roleSet;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleSet;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return email;
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

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public Set<Role> getRoleSet() {
        return roleSet;
    }

    @Override
    public String toString() {
        String rolesString = "";
        for (Role role: this.roleSet) {
            rolesString += role.getName();
        }
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", roleSet=" + rolesString +
                '}';
    }

    public void setRoleSet(String roleSet) {
        this.roleSet = new HashSet<>();
        if (roleSet.contains("ADMIN")) {
            this.roleSet.add(new Role("ADMIN"));
        }
        if (roleSet.contains("USER")) {
            this.roleSet.add(new Role("USER"));
        }
    }


}
