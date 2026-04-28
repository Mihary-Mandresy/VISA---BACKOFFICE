package com.visa.demo.models;

import com.nojpa.bd.entity.Entity;

public class EtatDemande  extends Entity<EtatDemande> {

    String id;
    String libelle;
    public EtatDemande() {
        setNomTable("etatdemande");
        setSigle("ETATDMD");
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
