package es_ontrack.backend.src.database.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User {

    // id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    // email
    @Column(nullable = false, unique = true)
    private String email;
    // password
    @Column(nullable = false)
    private String password;
    // username
    @Column(length = 30)
    private String username;
    // role
    @Column(nullable = false)
    private String role;
    // role_type
    @Column(nullable = false)
    private Integer role_type;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getRoleType() {
        return this.role_type;
    }

    public void setRoleType(Integer role_type) {
        this.role_type = role_type;
    }

}
