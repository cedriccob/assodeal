package com.entrepreunariat.assodeal.model.dto;

import java.io.Serializable;

public class AttributsProduitDTO  implements Serializable {

    private static final long serialVersionUID = 7438490015042938500L;
    private long idAttributsProduit;
    private String couleurProduit;
    private double poidsProduit;

    public long getIdAttributsProduit() {
        return idAttributsProduit;
    }

    public void setIdAttributsProduit(long idAttributsProduit) {
        this.idAttributsProduit = idAttributsProduit;
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
