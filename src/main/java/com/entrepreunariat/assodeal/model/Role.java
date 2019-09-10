package com.entrepreunariat.assodeal.model;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue
    private long idRole;
    private String role;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_user")
    private User user;

    public Role() {
    }

    public long getIdRole() {
        return idRole;
    }

    public void setIdRole(long idRole) {
        this.idRole = idRole;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
