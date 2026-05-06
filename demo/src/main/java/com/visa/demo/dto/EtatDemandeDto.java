package com.visa.demo.dto;

import java.sql.Connection;
import java.time.LocalDate;

import com.nojpa.bd.entity.Entity;

public class EtatDemandeDto extends Entity<EtatDemandeDto> {
    private LocalDate datecreation;
    private String idetat;
    private String libelle;
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public LocalDate getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(LocalDate datecreation) {
        this.datecreation = datecreation;
    }
        public String getIdetat() {
        return idetat;
    }

    public void setIdetat(String idetat) {
        this.idetat = idetat;
    }

    public EtatDemandeDto() {
        setNomTable("v_suivis_etats_demandes");
    }

}
