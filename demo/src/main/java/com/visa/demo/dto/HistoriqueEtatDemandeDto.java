package com.visa.demo.dto;

import java.time.LocalDate;

import com.nojpa.bd.entity.Entity;

public class HistoriqueEtatDemandeDto extends Entity<HistoriqueEtatDemandeDto> {

    private String iddemande;
    private LocalDate datecreation;
    private String idetat;
    private String libelle;

    public HistoriqueEtatDemandeDto() {
        setNomTable("v_suivis_etats_demandes");
    }

    public String getIddemande() {
        return iddemande;
    }

    public void setIddemande(String iddemande) {
        this.iddemande = iddemande;
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

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

}
