package com.visa.demo.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.visa.demo.models.Demandeur;
import com.visa.demo.models.Passport;
import com.visa.demo.models.Visatransformable;

public class DemandeDto {
    Demandeur demandeur;
    Passport passport;
    Visatransformable visatransformable;
    List<String> dossiersStandard;
    List<String> dossiersSup;
    String idTypeDemande;
    String idTypeVisa;
    LocalDate date;

    public DemandeDto() {
        this.demandeur = new Demandeur();
        this.passport = new Passport();
        this.visatransformable = new Visatransformable();
        this.dossiersStandard = new ArrayList<>(); // Initialisation
        this.dossiersSup = new ArrayList<>(); // Initialisation
    }

    public Demandeur getDemandeur() {
        return demandeur;
    }

    public void setDemandeur(Demandeur demandeur) {
        this.demandeur = demandeur;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Visatransformable getVisatransformable() {
        return visatransformable;
    }

    public void setVisatransformable(Visatransformable visaTransformable) {
        this.visatransformable = visaTransformable;
    }

    public List<String> getDossiersStandard() {
        return dossiersStandard;
    }

    public void setDossiersStandard(List<String> dossiersStandard) {
        this.dossiersStandard = dossiersStandard;
    }

    public List<String> getDossiersSup() {
        return dossiersSup;
    }

    public void setDossiersSup(List<String> dossiersSup) {
        this.dossiersSup = dossiersSup;
    }

    public String getIdTypeDemande() {
        return idTypeDemande;
    }

    public void setIdTypeDemande(String idTypeDemande) {
        this.idTypeDemande = idTypeDemande;
    }

    public String getIdTypeVisa() {
        return idTypeVisa;
    }

    public void setIdTypeVisa(String idTypeVisa) {
        this.idTypeVisa = idTypeVisa;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
