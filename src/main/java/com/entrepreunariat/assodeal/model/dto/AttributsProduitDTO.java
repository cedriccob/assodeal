package com.entrepreunariat.assodeal.model.dto;

public class AttributsProduitDTO  {

    private long idAttributProduit;
    private String couleurProduit;
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
