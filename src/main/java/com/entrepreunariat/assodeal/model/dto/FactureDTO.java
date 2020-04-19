package com.entrepreunariat.assodeal.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FactureDTO implements Serializable {

    private static final long serialVersionUID = 1114525913743701260L;
    private long idFacture;
    private String numeroFacture;
    private BigDecimal montantFacture;
    private Date dateFacture;

    public long getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(long idFacture) {
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
}
