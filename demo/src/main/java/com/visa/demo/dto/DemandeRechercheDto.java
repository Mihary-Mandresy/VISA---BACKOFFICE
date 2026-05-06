package com.visa.demo.dto;

import com.nojpa.bd.entity.Entity;

public class DemandeRechercheDto extends Entity<DemandeRechercheDto> {

    private String nomDemandeur;
    private String numeropassport;
    private String typevisa;
    private String etat;

    public DemandeRechercheDto() {
        setNomTable("v_demande_recherche");
    }

    public String geNomDemandeur() {
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

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
