package com.entrepreunariat.assodeal.model;

import javax.persistence.*;

@Entity
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqid-gen-frn")
    @SequenceGenerator(name = "seqid-gen-frn", sequenceName = "fournisseur_seq", allocationSize = 1, initialValue = 1)
    private long idFournisseur;
    private String nomFournisseur;
    private String numeroSiretFournisseur;
    private String statutFournisseur;
    private String userFournisseur;
    private String passwordFournisseur;
    @Transient
    private String confirmPasswordFournisseur;

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

    public String getNumeroSiretFournisseur() {
        return numeroSiretFournisseur;
    }

    public void setNumeroSiretFournisseur(String numeroSiretFournisseur) {
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

    public String getPasswordFournisseur() {
        return passwordFournisseur;
    }

    public void setPasswordFournisseur(String passwordFournisseur) {
        this.passwordFournisseur = passwordFournisseur;
    }

    public String getConfirmPasswordFournisseur() {
        return confirmPasswordFournisseur;
    }

    public void setConfirmPasswordFournisseur(String confirmPasswordFournisseur) {
        this.confirmPasswordFournisseur = confirmPasswordFournisseur;
    }
}
