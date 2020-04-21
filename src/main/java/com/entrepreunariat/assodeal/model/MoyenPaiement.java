package com.entrepreunariat.assodeal.model;

import javax.persistence.*;

@Entity
public class MoyenPaiement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqid-gen-pmt")
    @SequenceGenerator(name = "seqid-gen-pmt", sequenceName = "moyen_paiement_seq", allocationSize = 1, initialValue = 1)
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
