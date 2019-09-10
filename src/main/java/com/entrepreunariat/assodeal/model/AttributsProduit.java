package com.entrepreunariat.assodeal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AttributsProduit {
    @Id
    @GeneratedValue
    private long idAttributProduit;
    @Column(name="valeur_couleur_produit")
    private String couleurProduit;
    @Column(name="valeur_poids_produit")
    private double poidsProduit;

    public AttributsProduit() {
    }

    public long getIdAttributProduit() {
        return idAttributProduit;
    }

    public void setIdAttributProduit(long idAttributProduit) {
        this.idAttributProduit = idAttributProduit;
    }

    public String getCouleurProduit() {
        return couleurProduit;
    }

    public void setCouleurProduit(String couleurProduit) {
        this.couleurProduit = couleurProduit;
    }

    public double getPoidsProduit() {
        return poidsProduit;
    }

    public void setPoidsProduit(double poidsProduit) {
        this.poidsProduit = poidsProduit;
    }

    @Override
    public String toString() {
        return "AttributsProduit{" +
                "idAttributProduit=" + idAttributProduit +
                ", couleurProduit='" + couleurProduit + '\'' +
                ", poidsProduit=" + poidsProduit +
                '}';
    }
}
