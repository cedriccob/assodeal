package com.entrepreunariat.assodeal.model;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class Role {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private long idRole;
    private String nameRole;
    private String codeRole;

    public long getIdRole() {
        return idRole;
    }

    public void setIdRole(long idRole) {
        this.idRole = idRole;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public String getCodeRole() {
        return codeRole;
    }

    public void setCodeRole(String codeRole) {
        this.codeRole = codeRole;
    }
}
