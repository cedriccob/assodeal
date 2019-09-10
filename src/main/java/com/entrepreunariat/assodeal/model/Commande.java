package com.entrepreunariat.assodeal.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Commande {
    @Id
    @GeneratedValue
    private long idCommande;
    private int quantiteCommande;
    private Date dateCommance;
    private int statusCommance;
    @OneToOne
    private Produit produit;

    public Commande() {
    }

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

    public Date getDateCommance() {
        return dateCommance;
    }

    public void setDateCommance(Date dateCommance) {
        this.dateCommance = dateCommance;
    }

    public int getStatusCommance() {
        return statusCommance;
    }

    public void setStatusCommance(int statusCommance) {
        this.statusCommance = statusCommance;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "idCommande=" + idCommande +
                ", quantiteCommande=" + quantiteCommande +
                ", dateCommance=" + dateCommance +
                ", statusCommance=" + statusCommance +
                '}';
    }
}
