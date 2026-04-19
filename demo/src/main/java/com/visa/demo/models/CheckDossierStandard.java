package com.visa.demo.models;

import com.nojpa.bd.entity.Entity;

public class CheckDossierStandard extends Entity {

    private boolean exist;
    private String iddemande;
    private String iddossierstandard;

    public CheckDossierStandard() {
        setNomTable("checkdossierstandard");
        setSigle("CDST");
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

    public String getIddossierstandard() {
        return iddossierstandard;
    }

    public void setIddossierstandard(String iddossierstandard) {
        this.iddossierstandard = iddossierstandard;
    }

}
