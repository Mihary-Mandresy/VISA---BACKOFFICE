package com.visa.demo.models;

import java.sql.Connection;
import java.util.List;

import com.nojpa.bd.entity.Entity;

public class DossierSupplementaire extends Entity<DossierSupplementaire> {

    private String libelle;
    private String idtypevisa;
    private boolean obligatoire;

    public DossierSupplementaire() {
        setNomTable("dossiersupplementaire");
        setSigle("DSU");
    }

    public List<DossierSupplementaire> getAllByIdTypeVisa(Connection c, String idtypevisa) throws Exception {
        return select(c, "idtypevisa = '" + idtypevisa + "'", null);
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
