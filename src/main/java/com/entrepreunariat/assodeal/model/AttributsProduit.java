package com.entrepreunariat.assodeal.model;

import javax.persistence.*;

@Entity
public class AttributsProduit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqid-gen-att")
    @SequenceGenerator(name = "seqid-gen-att", sequenceName = "attributs_produits_seq", allocationSize = 1, initialValue = 1)
    private long idAttributProduit;
    @Column(name="valeur_couleur_produit")
    private String couleurProduit;
    @Column(name="valeur_poids_produit")
    private double poidsProduit;

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

}
