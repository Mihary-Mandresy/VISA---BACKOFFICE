package com.visa.demo.dto;

import com.nojpa.bd.entity.Entity;
import com.visa.demo.models.DossierSupplementaire;

public class DossierSupplementaireDto extends Entity<DossierSupplementaireDto> {
    
    private String libelle;
    private boolean exist;
    public DossierSupplementaireDto() {
        setNomTable("v_verifications_dossiers_supplementaires");
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
