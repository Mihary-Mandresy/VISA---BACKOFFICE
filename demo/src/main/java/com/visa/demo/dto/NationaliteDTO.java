package com.visa.demo.dto;

import com.nojpa.bd.entity.Entity;

public class NationaliteDTO extends Entity<NationaliteDTO>{
    private String libelle;

    public NationaliteDTO() {
        setNomTable("nationalite");
    }

    public NationaliteDTO(String id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

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