package com.visa.demo.models;

import java.time.LocalDate;

import com.nojpa.bd.entity.Entity;

public class Passport extends Entity {

    private String numero;
    private LocalDate datedelivrance;
    private LocalDate dateexpiration;
    private String iddemandeur;

    public Passport(String numero, LocalDate datedelivrance, LocalDate dateexpiration, String iddemandeur) {
        this();
        
        this.numero = numero;
        this.datedelivrance = datedelivrance;
        this.dateexpiration = dateexpiration;
        this.iddemandeur = iddemandeur;

    }

    public Passport() {
        setNomTable("passport");
        setSigle("PASS");
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getDatedelivrance() {
        return datedelivrance;
    }

    public void setDatedelivrance(LocalDate datedelivrance) {
        this.datedelivrance = datedelivrance;
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

}
