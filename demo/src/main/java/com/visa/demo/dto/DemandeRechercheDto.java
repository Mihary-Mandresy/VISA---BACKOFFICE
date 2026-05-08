package com.visa.demo.dto;

import com.nojpa.bd.entity.Entity;

public class DemandeRechercheDto extends Entity<DemandeRechercheDto> {

    private String nomDemandeur;
    private String numeropassport;
    private String typevisa;
    private String etatdemande;

    public DemandeRechercheDto() {
        setNomTable("v_demande_recherche");
    }

    public String getNomDemandeur() {
        return nomDemandeur;
    }

    public void setNomDemandeur(String nomDemandeur) {
        this.nomDemandeur = nomDemandeur;
    }

    public String getNumeropassport() {
        return numeropassport;
    }

    public void setNumeropassport(String numeropassport) {
        this.numeropassport = numeropassport;
    }

    public String getTypevisa() {
        return typevisa;
    }

    public void setTypevisa(String typevisa) {
        this.typevisa = typevisa;
    }

    public String getEtatdemande() {
        return etatdemande;
    }

    public void setEtatdemande(String etat) {
        this.etatdemande = etat;
    }
}
