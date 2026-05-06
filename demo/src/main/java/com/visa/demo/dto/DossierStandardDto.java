package com.visa.demo.dto;

import com.nojpa.bd.entity.Entity;

public class DossierStandardDto extends Entity<DossierStandardDto> {

    private String libelle;
    private boolean exist;

    public DossierStandardDto() {
        setNomTable("v_verifications_dossiers_standards");
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

}
