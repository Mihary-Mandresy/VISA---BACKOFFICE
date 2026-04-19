package com.visa.demo.models;

import com.nojpa.bd.entity.Entity;

public class DossierStandard extends Entity {

    private String libelle;
    private boolean obligatoire;

    public DossierStandard() {
        setNomTable("dossierstandard");
        setSigle("DST");
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public boolean isObligatoire() {
        return obligatoire;
    }

    public void setObligatoire(boolean obligatoire) {
        this.obligatoire = obligatoire;
    }
}
