package com.entrepreunariat.assodeal.model.dto;

public class CategorieProduitDTO {

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
