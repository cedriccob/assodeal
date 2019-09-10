package com.entrepreunariat.assodeal.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class User {
    @Id
    @GeneratedValue
    private long idUser;
    private String nom;
    private String prenom;
    private String mail;
    private String contact;
    private String pseudo;
    private String password;
    @Transient
    private String confirmPassword;
    private String nationalite;
    private String paysResidence;
    private String ville;
    private String adresse;
    private int status;
    // date d'enregistrement
    private Date dateEnregistrement;
    // date dernier login
    private Date dateDernierLogin;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_role")
    private Role roleUser;

    public User() {
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getPaysResidence() {
        return paysResidence;
    }

    public void setPaysResidence(String paysResidence) {
        this.paysResidence = paysResidence;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDateEnregistrement() {
        return dateEnregistrement;
    }

    public void setDateEnregistrement(Date dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

    public Date getDateDernierLogin() {
        return dateDernierLogin;
    }

    public void setDateDernierLogin(Date dateDernierLogin) {
        this.dateDernierLogin = dateDernierLogin;
    }

    public Role getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(Role roleUser) {
        this.roleUser = roleUser;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
