package com.visa.demo.models;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import com.nojpa.bd.entity.Entity;

public class Demande extends Entity {

    private String idtypedemande;
    private String idvisatransformable;
    private String idpassport;
    private String iddemandeur;
    private String idtypevisa;
    private LocalDate datecreation;
    private String idetatdemande;

    private String idoriginal;

    public Demande() {
        setNomTable("demande");
        setSigle("DMD");
    }

    public String getIdtypedemande() {
        return idtypedemande;
    }

    public void setIdtypedemande(String idtypedemande) {
        this.idtypedemande = idtypedemande;
    }

    public String getIdpassport() {
        return idpassport;
    }

    public void setIdpassport(String idpassport) {
        this.idpassport = idpassport;
    }

    public LocalDate getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(LocalDate datecreation) {
        if (datecreation == null) {
            this.datecreation = LocalDate.now();
        } else {
            this.datecreation = datecreation;
        }
    }

    public String getIdvisatransformable() {
        return idvisatransformable;
    }

    public void setIdvisatransformable(String idvisatransformable) {
        this.idvisatransformable = idvisatransformable;
    }

    public String getIdtypevisa() {
        return idtypevisa;
    }

    public void setIdtypevisa(String idtypevisa) {
        this.idtypevisa = idtypevisa;
    }

    public String getIddemandeur() {
        return iddemandeur;
    }

    public void setIddemandeur(String iddemandeur) {
        this.iddemandeur = iddemandeur;
    }

    public String getIdoriginal() {
        return idoriginal;
    }

    public void setIdoriginal(String idoriginal) {
        this.idoriginal = idoriginal;
    }

    public String getIdetatdemande() {
        return idetatdemande;
    }

    public void setIdetatdemande(String idetatdemande) {
        this.idetatdemande = idetatdemande;
    }

}
