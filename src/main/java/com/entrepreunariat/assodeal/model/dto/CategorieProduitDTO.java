package com.entrepreunariat.assodeal.model.dto;

import java.io.Serializable;

public class CategorieProduitDTO implements Serializable {

    private static final long serialVersionUID = 1775201885919116481L;
    private long idCategorieProduit;
    private String libelleCategorieProduit;

    public long getIdCategorieProduit() {
        return idCategorieProduit;
    }

    public void setIdCategorieProduit(long idCategorieProduit) {
        this.idCategorieProduit = idCategorieProduit;
    }

    public String getLibelleCategorieProduit() {
        return libelleCategorieProduit;
    }

    public void setLibelleCategorieProduit(String libelleCategorieProduit) {
        this.libelleCategorieProduit = libelleCategorieProduit;
    }

}
