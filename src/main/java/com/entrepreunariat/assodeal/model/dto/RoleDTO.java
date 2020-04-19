package com.entrepreunariat.assodeal.model.dto;

import javax.persistence.*;
import java.io.Serializable;

public class RoleDTO implements Serializable {

    private static final long serialVersionUID = -1709473238277133762L;
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
