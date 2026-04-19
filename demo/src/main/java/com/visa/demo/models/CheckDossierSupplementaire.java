package com.visa.demo.models;

import com.nojpa.bd.entity.Entity;

public class CheckDossierSupplementaire extends Entity {

    private boolean exist;
    private String iddemande;
    private String iddossiersupplementaire;

    public CheckDossierSupplementaire() {
        setNomTable("checkdossiersupplementaire");
        setSigle("CDSU");
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public String getIddemande() {
        return iddemande;
    }

    public void setIddemande(String iddemande) {
        this.iddemande = iddemande;
    }

    public String getIddossiersupplementaire() {
        return iddossiersupplementaire;
    }

    public void setIddossiersupplementaire(String iddossiersupplementaire) {
        this.iddossiersupplementaire = iddossiersupplementaire;
    }

}
