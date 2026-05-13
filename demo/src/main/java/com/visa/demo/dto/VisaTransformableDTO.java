package com.visa.demo.dto;

import java.time.LocalDate;

import com.nojpa.bd.entity.Entity;

public class VisaTransformableDTO extends Entity<VisaTransformableDTO>{
    private String id;
    private String reference;
    private LocalDate dateentreemada;
    private LocalDate dateexpiration;
    private String lieuentree;
    
    public VisaTransformableDTO() {
        setNomTable("v_visatransformable");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDate getDateentreemada() {
        return dateentreemada;
    }

    public void setDateentreemada(LocalDate dateentreemada) {
        this.dateentreemada = dateentreemada;
    }

    public LocalDate getDateexpiration() {
        return dateexpiration;
    }

    public void setDateexpiration(LocalDate dateexpiration) {
        this.dateexpiration = dateexpiration;
    }

    public String getLieuentree() {
        return lieuentree;
    }

    public void setLieuentree(String lieuentree) {
        this.lieuentree = lieuentree;
    }
}
