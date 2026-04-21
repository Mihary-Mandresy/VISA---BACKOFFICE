package com.visa.demo.models;

import com.nojpa.bd.entity.Entity;

public class TypeVisa extends Entity<TypeVisa> {

    private String libelle;

    public TypeVisa() {
        setNomTable("typevisa");
        setSigle("TYPV");
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }   
}
