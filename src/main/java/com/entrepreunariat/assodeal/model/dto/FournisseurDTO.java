package com.entrepreunariat.assodeal.model.dto;

import java.io.Serializable;

public class FournisseurDTO implements Serializable {
    private static final long serialVersionUID = 7810147681120578845L;
    private long idFournisseur;
    private String nomFournisseur;
    private String numeroSiretFournisseur;
    private String statutFournisseur;
    private String userFournisseur;
    private String passwordFournisseur;
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
