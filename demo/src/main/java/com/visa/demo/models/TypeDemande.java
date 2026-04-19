package com.visa.demo.models;

import com.nojpa.bd.entity.Entity;

public class TypeDemande extends Entity {

    private String libelle;

    public TypeDemande() {
        setNomTable("typedemande");
        setSigle("TYPDMD");
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

}
