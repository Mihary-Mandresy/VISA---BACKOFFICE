package com.visa.demo.dto;

import com.nojpa.bd.entity.Entity;

public class TypeVisaDto extends Entity<TypeVisaDto>{
    public TypeVisaDto() {
        setNomTable("typevisa");
    }
    private String id;
    private String libelle;
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
