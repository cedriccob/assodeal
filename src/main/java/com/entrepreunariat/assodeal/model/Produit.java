package com.entrepreunariat.assodeal.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Produit {
    @Id
    @GeneratedValue
    private long idProduit;
    private String libelleProduit;
    private int qtStockProduit;
    private BigDecimal prixReelProduit;
    private BigDecimal prixVenteProduit;
    private String detailProduit;
    @OneToOne
    @JoinColumn(name = "id_attributs_produit")
    private AttributsProduit attributsProduit;
    @OneToOne
    @JoinColumn(name = "id_categorie_produit")
    private CategorieProduit categorieProduit;


    public long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(long idProduit) {
        this.idProduit = idProduit;
    }

    public String getLibelleProduit() {
        return libelleProduit;
    }

    public void setLibelleProduit(String libelleProduit) {
        this.libelleProduit = libelleProduit;
    }

    public int getQtStockProduit() {
        return qtStockProduit;
    }

    public void setQtStockProduit(int qtStockProduit) {
        this.qtStockProduit = qtStockProduit;
    }

    public BigDecimal getPrixReelProduit() {
        return prixReelProduit;
    }

    public void setPrixReelProduit(BigDecimal prixReelProduit) {
        this.prixReelProduit = prixReelProduit;
    }

    public BigDecimal getPrixVenteProduit() {
        return prixVenteProduit;
    }

    public void setPrixVenteProduit(BigDecimal prixVenteProduit) {
        this.prixVenteProduit = prixVenteProduit;
    }

    public String getDetailProduit() {
        return detailProduit;
    }

    public void setDetailProduit(String detailProduit) {
        this.detailProduit = detailProduit;
    }

    public AttributsProduit getAttributsProduit() {
        return attributsProduit;
    }

    public void setAttributsProduit(AttributsProduit attributsProduit) {
        this.attributsProduit = attributsProduit;
    }

    public CategorieProduit getCategorieProduit() {
        return categorieProduit;
    }

    public void setCategorieProduit(CategorieProduit categorieProduit) {
        this.categorieProduit = categorieProduit;
    }

}
