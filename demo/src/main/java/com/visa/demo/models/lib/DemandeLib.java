package com.visa.demo.models.lib;

import java.time.LocalDate;

import com.nojpa.bd.entity.Entity;

public class DemandeLib extends Entity<DemandeLib> {
    private LocalDate datecreation;
    private String idpassport;
    private String idvisatransformable;
    private String libelleetatdemande;
    private String libelletypedemande;
    private String nomdemandeur;
    private String prenomdemandeur;
    private String libelletypevisa;

    public DemandeLib() {
        setNomTable("v_liste_demande");
    }

    public LocalDate getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(LocalDate datecreation) {
        this.datecreation = datecreation;
    }
 
    public String getIdpassport() {
        return idpassport;
    }

    public void setIdpassport(String idpassport) {
        this.idpassport = idpassport;
    }

    public String getIdvisatransformable() {
        return idvisatransformable;
    }

    public void setIdvisatransformable(String idvisatransformable) {
        this.idvisatransformable = idvisatransformable;
    }

    public String getLibelleetatdemande() {
        return libelleetatdemande;
    }

    public void setLibelleetatdemande(String libelleetatdemande) {
        this.libelleetatdemande = libelleetatdemande;
    }

    public String getLibelletypedemande() {
        return libelletypedemande;
    }

    public void setLibelletypedemande(String libelletypedemande) {
        this.libelletypedemande = libelletypedemande;
    }

    public String getNomdemandeur() {
        return nomdemandeur;
    }

    public void setNomdemandeur(String nomdemandeur) {
        this.nomdemandeur = nomdemandeur;
    }

    public String getPrenomdemandeur() {
        return prenomdemandeur;
    }

    public void setPrenomdemandeur(String prenomdemandeur) {
        this.prenomdemandeur = prenomdemandeur;
    }

    public String getLibelletypevisa() {
        return libelletypevisa;
    }

    public void setLibelletypevisa(String libelletypevisa) {
        this.libelletypevisa = libelletypevisa;
    }


}

