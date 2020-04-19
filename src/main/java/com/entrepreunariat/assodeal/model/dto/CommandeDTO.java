package com.entrepreunariat.assodeal.model.dto;

import java.io.Serializable;
import java.util.Date;

public class CommandeDTO implements Serializable {
    private static final long serialVersionUID = -6950484921614522853L;
    private long idCommande;
    private int quantiteCommande;
    private Date dateCommande;
    private int statusCommande;
    private ProduitDTO produit;


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

    public ProduitDTO getProduit() {
        return produit;
    }

    public void setProduit(ProduitDTO produit) {
        this.produit = produit;
    }
}
