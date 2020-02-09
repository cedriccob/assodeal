package com.entrepreunariat.assodeal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MoyenPaiement {
    @Id
    @GeneratedValue
    private long idMoyenPaiement;
    private String libelleMoyenPaiement;
    private String abreviationMoyenPaiement;

    public long getIdMoyenPaiement() {
        return idMoyenPaiement;
    }

    public void setIdMoyenPaiement(long idMoyenPaiement) {
        this.idMoyenPaiement = idMoyenPaiement;
    }

    public String getLibelleMoyenPaiement() {
        return libelleMoyenPaiement;
    }

    public void setLibelleMoyenPaiement(String libelleMoyenPaiement) {
        this.libelleMoyenPaiement = libelleMoyenPaiement;
    }

    public String getAbreviationMoyenPaiement() {
        return abreviationMoyenPaiement;
    }

    public void setAbreviationMoyenPaiement(String abreviationMoyenPaiement) {
        this.abreviationMoyenPaiement = abreviationMoyenPaiement;
    }

}
