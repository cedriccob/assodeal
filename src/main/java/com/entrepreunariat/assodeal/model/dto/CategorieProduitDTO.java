package com.entrepreunariat.assodeal.model.dto;

import java.io.Serializable;

public class CategorieProduitDTO implements Serializable {

    private static final long serialVersionUID = 1775201885919116481L;
    private long idCategorieProduit;
    private String libelleProduit;
    private String abreviationProduit;

    public long getIdCategorieProduit() {
        return idCategorieProduit;
    }

    public void setIdCategorieProduit(long idCategorieProduit) {
        this.idCategorieProduit = idCategorieProduit;
    }

    public String getLibelleProduit() {
        return libelleProduit;
    }

    public void setLibelleProduit(String libelleProduit) {
        this.libelleProduit = libelleProduit;
    }

    public String getAbreviationProduit() {
        return abreviationProduit;
    }

    public void setAbreviationProduit(String abreviationProduit) {
        this.abreviationProduit = abreviationProduit;
    }

}
