package com.entrepreunariat.assodeal.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqid-gen-com")
    @SequenceGenerator(name = "seqid-gen-com", sequenceName = "commande_seq", allocationSize = 1, initialValue = 1)
    private long idCommande;
    private int quantiteCommande;
    private Date dateCommande;
    private int statusCommande;
    @OneToOne
    @JoinColumn(name = "id_produit")
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
