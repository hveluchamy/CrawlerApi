package com.demo.crawlerapi.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "useraccount")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String username;
    private String password;
    private boolean enabled;

   /* @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "email", referencedColumnName = "email")
    private List roles;*/

    public User(Long id, String email, String username, String password, boolean enabled, List roles) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
       // this.roles = roles;
    }

    public User(String email, String username, String password, boolean enabled, List roles) {
        this(null, email, username, password, enabled, roles);
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
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

    /*//public List getRoles() {
        return roles;
    }*/
  /*  public void setRoles(List roles) {
        this.roles = roles;
    }*/
}
