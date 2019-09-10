package com.entrepreunariat.assodeal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Facture {

    @Id
    @GeneratedValue
    private int idFacture;
    private String numeroFacture;
    private BigDecimal montantFacture;
    private Date dateFacture;

    public Facture() {
    }

    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public String getNumeroFacture() {
        return numeroFacture;
    }

    public void setNumeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    public BigDecimal getMontantFacture() {
        return montantFacture;
    }

    public void setMontantFacture(BigDecimal montantFacture) {
        this.montantFacture = montantFacture;
    }

    public Date getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(Date dateFacture) {
        this.dateFacture = dateFacture;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "idFacture=" + idFacture +
                ", numeroFacture='" + numeroFacture + '\'' +
                ", montantFacture=" + montantFacture +
                ", dateFacture=" + dateFacture +
                '}';
    }
}
