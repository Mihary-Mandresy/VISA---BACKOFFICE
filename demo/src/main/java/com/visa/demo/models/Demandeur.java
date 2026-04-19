package com.visa.demo.models;

import com.nojpa.bd.entity.Entity;

public class Demandeur extends Entity {

    private String nom;
    private String prenom;
    private String profession;
    private String adressemada;
    private String tel;
    private String email;
    private String idsituationdefamille;
    private String idnationalite;

    public Demandeur(String nom, String prenom, String profession, String adressemada, String tel, String email,
            String idsituationdefamille, String idnationalite) {
        this();

        this.nom = nom;
        this.prenom = prenom;
        this.profession = profession;
        this.adressemada = adressemada;
        this.tel = tel;
        this.email = email;
        this.idsituationdefamille = idsituationdefamille;
        this.idnationalite = idnationalite;
    }

    public Demandeur() {
        setNomTable("demandeur");
        setSigle("DMDR");
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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdsituationdefamille() {
        return idsituationdefamille;
    }

    public void setIdsituationdefamille(String idsituationdefamille) {
        this.idsituationdefamille = idsituationdefamille;
    }

    public String getIdnationalite() {
        return idnationalite;
    }

    public void setIdnationalite(String idnationalite) {
        this.idnationalite = idnationalite;
    }

    public String getAdressemada() {
        return adressemada;
    }

    public void setAdressemada(String adressemada) {
        this.adressemada = adressemada;
    }

}
