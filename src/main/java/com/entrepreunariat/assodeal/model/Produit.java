package com.entrepreunariat.assodeal.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqid-gen-pdt")
    @SequenceGenerator(name = "seqid-gen-pdt", sequenceName = "produit_seq", allocationSize = 1, initialValue = 1)
    private long idProduit;
    private String libelleProduit;
    private int qtStockProduit;
    private BigDecimal prixReelProduit;
    private BigDecimal prixVenteProduit;
    private String detailProduit;
    @OneToOne
    @JoinColumn(name = "id_categorie_produit")
    private CategorieProduit categorieProduit;
    private String couleurProduit;
    private double poidsProduit;
    private String abreviationProduit;
    @Lob
    private byte[] imageProduit;


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

    public CategorieProduit getCategorieProduit() {
        return categorieProduit;
    }

    public void setCategorieProduit(CategorieProduit categorieProduit) {
        this.categorieProduit = categorieProduit;
    }

    public byte[] getImageProduit() {
        return imageProduit;
    }

    public void setImageProduit(byte[] imageProduit) {
        this.imageProduit = imageProduit;
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

    public String getAbreviationProduit() {
        return abreviationProduit;
    }

    public void setAbreviationProduit(String abreviationProduit) {
        this.abreviationProduit = abreviationProduit;
    }
}
