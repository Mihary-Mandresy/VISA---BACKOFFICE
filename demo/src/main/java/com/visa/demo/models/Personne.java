package com.visa.demo.models;

import com.nojpa.bd.entity.Entity;

public class Personne extends Entity {

    private String nom;
    private String prenom;

    @Override
    public String toString() {
        return "Personne [nom=" + nom + ", prenom=" + prenom + "]";
    }

    public Personne() {
        setNomTable("personne");
        setSigle("PRS");
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

}
