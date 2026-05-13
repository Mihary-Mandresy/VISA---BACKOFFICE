package com.visa.demo.dto;

import java.time.LocalDate;

import com.nojpa.bd.entity.Entity;

public class HistoriqueEtatDemandeDto extends Entity<HistoriqueEtatDemandeDto> {

    private String iddemande;
    private LocalDate daty;
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

    public LocalDate getDaty() {
        return daty;
    }

    public void setDaty(LocalDate datecreation) {
        this.daty = datecreation;
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
