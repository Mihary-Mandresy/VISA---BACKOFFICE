package com.visa.demo.models;

import com.nojpa.bd.entity.Entity;

public class SituationDeFamille extends Entity {

    private String libelle;

    public SituationDeFamille() {
        setSigle("SITF");
        setNomTable("situationdefamille");
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

}
