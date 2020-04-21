package com.entrepreunariat.assodeal.model;

import javax.persistence.*;

@Entity
public class CategorieProduit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqid-gen-cat")
    @SequenceGenerator(name = "seqid-gen-cat", sequenceName = "categorie_produit_seq", allocationSize = 1, initialValue = 1)
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
