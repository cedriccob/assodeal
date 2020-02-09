package com.entrepreunariat.assodeal.model.dto;

import com.entrepreunariat.assodeal.model.Produit;

import java.util.Date;

public class CommandeDTO {
    private long idCommande;
    private int quantiteCommande;
    private Date dateCommande;
    private int statusCommande;
    private Produit produit;

    public long getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(long idCommande) {
        this.idCommande = idCommande;
    }

    public int getQuantiteCommande() {
        return quantiteCommande;
    }

    public void setQuantiteCommande(int quantiteCommande) {
        this.quantiteCommande = quantiteCommande;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public int getStatusCommande() {
        return statusCommande;
    }

    public void setStatusCommande(int statusCommande) {
        this.statusCommande = statusCommande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
