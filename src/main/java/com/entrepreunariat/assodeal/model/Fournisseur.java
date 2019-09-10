package com.entrepreunariat.assodeal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Fournisseur {
    @Id
    @GeneratedValue
    private long idFournisseur;
    private String nomFournisseur;
    private int numeroSiretFournisseur;
    private String statutFournisseur;
    private String userFournisseur;
    private String mdpFournisseur;

    public Fournisseur() {
    }

    public long getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(long idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public String getNomFournisseur() {
        return nomFournisseur;
    }

    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }

    public int getNumeroSiretFournisseur() {
        return numeroSiretFournisseur;
    }

    public void setNumeroSiretFournisseur(int numeroSiretFournisseur) {
        this.numeroSiretFournisseur = numeroSiretFournisseur;
    }

    public String getStatutFournisseur() {
        return statutFournisseur;
    }

    public void setStatutFournisseur(String statutFournisseur) {
        this.statutFournisseur = statutFournisseur;
    }

    public String getUserFournisseur() {
        return userFournisseur;
    }

    public void setUserFournisseur(String userFournisseur) {
        this.userFournisseur = userFournisseur;
    }

    public String getMdpFournisseur() {
        return mdpFournisseur;
    }

    public void setMdpFournisseur(String mdpFournisseur) {
        this.mdpFournisseur = mdpFournisseur;
    }

    @Override
    public String toString() {
        return "Fournisseur{" +
                "idFournisseur=" + idFournisseur +
                ", nomFournisseur='" + nomFournisseur + '\'' +
                ", numeroSiretFournisseur=" + numeroSiretFournisseur +
                ", statutFournisseur='" + statutFournisseur + '\'' +
                ", userFournisseur='" + userFournisseur + '\'' +
                ", mdpFournisseur='" + mdpFournisseur + '\'' +
                '}';
    }
}
