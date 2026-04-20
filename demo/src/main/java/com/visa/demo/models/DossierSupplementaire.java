package com.visa.demo.models;

import com.nojpa.bd.entity.Entity;

public class DossierSupplementaire extends Entity<DossierSupplementaire> {

    private String libelle;
    private String idtypevisa;
    private boolean obligatoire;

    public DossierSupplementaire() {
        setNomTable("dossiersupplementaire");
        setSigle("DSU");
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getIdtypevisa() {
        return idtypevisa;
    }

    public void setIdtypevisa(String idtypevisa) {
        this.idtypevisa = idtypevisa;
    }

    public boolean isObligatoire() {
        return obligatoire;
    }

    public void setObligatoire(boolean obligatoire) {
        this.obligatoire = obligatoire;
    }

}
