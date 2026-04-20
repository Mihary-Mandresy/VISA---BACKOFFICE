package com.visa.demo.models;

import java.time.LocalDate;

import com.nojpa.bd.entity.Entity;

public class Visatransformable extends Entity<Visatransformable> {

    private String reference;
    private LocalDate dateentreemada;
    private LocalDate dateexpiration;
    private String iddemandeur;
    private String idpassport;
    private String lieuentree;

    public Visatransformable(String reference, LocalDate dateentreemada, LocalDate dateexpiration, String iddemandeur,
            String idpassport, String lieuentree) {
        this();

        this.reference = reference;
        this.dateentreemada = dateentreemada;
        this.dateexpiration = dateexpiration;
        this.iddemandeur = iddemandeur;
        this.idpassport = idpassport;
        this.lieuentree = lieuentree;
    }

    public Visatransformable() {
        setNomTable("visatransformable");
        setSigle("VISA");
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

    public String getIddemandeur() {
        return iddemandeur;
    }

    public void setIddemandeur(String iddemandeur) {
        this.iddemandeur = iddemandeur;
    }

    public String getIdpassport() {
        return idpassport;
    }

    public void setIdpassport(String idpassport) {
        this.idpassport = idpassport;
    }

    public String getLieuentree() {
        return lieuentree;
    }

    public void setLieuentree(String lieuentree) {
        this.lieuentree = lieuentree;
    }

}
