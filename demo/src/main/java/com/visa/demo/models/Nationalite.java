package com.visa.demo.models;

import com.nojpa.bd.entity.Entity;

public class Nationalite extends Entity {

    private String libelle;

    public Nationalite() {
        setNomTable("nationalite");
        setSigle("NAT");
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

}
