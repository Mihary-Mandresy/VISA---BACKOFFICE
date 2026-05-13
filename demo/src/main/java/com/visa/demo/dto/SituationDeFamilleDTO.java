package com.visa.demo.dto;

import com.nojpa.bd.entity.Entity;

public class SituationDeFamilleDTO extends Entity<SituationDeFamilleDTO> {
    
    private String libelle;

    // Constructeurs
    public SituationDeFamilleDTO() {
        setNomTable("situationdefamille");
    }

    public SituationDeFamilleDTO(String id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}