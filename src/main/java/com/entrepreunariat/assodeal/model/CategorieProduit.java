package com.entrepreunariat.assodeal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CategorieProduit {
    @Id
    @GeneratedValue
    private long idCategorieProduit;
    private String libelleProduit;
    private String abreviationProduit;

    public CategorieProduit() {
    }

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

    @Override
    public String toString() {
        return "CategorieProduit{" +
                "idCategorieProduit=" + idCategorieProduit +
                ", libelleProduit='" + libelleProduit + '\'' +
                ", abreviationProduit='" + abreviationProduit + '\'' +
                '}';
    }
}
