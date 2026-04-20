package com.visa.demo.models;

import java.time.LocalDate;

import com.nojpa.bd.entity.Entity;

public class CarteResident extends Entity<CarteResident> {

    private String reference;
    private LocalDate datedebut;
    private LocalDate dateexpiration;
    private String iddemande;
    private String idpassport;

    public CarteResident() {
        setNomTable("carteresident");
        setSigle("CRTR");
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDate getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(LocalDate datedebut) {
        this.datedebut = datedebut;
    }

    public LocalDate getDateexpiration() {
        return dateexpiration;
    }

    public void setDateexpiration(LocalDate dateexpiration) {
        this.dateexpiration = dateexpiration;
    }

    public String getIddemande() {
        return iddemande;
    }

    public void setIddemande(String iddemande) {
        this.iddemande = iddemande;
    }

    public String getIdpassport() {
        return idpassport;
    }

    public void setIdpassport(String idpassport) {
        this.idpassport = idpassport;
    }

}
