package com.visa.demo.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.visa.demo.models.Demandeur;
import com.visa.demo.models.Passport;
import com.visa.demo.models.Visatransformable;

public class DemandeDto {
    private String iddemande;
    Demandeur demandeur;
    Passport passport;
    Visatransformable visatransformable;
    List<String> dossiersStandard;
    List<String> dossiersSup;
    List<String> dossiersStandardConcatIdChecks ;
    List<String> dossiersSupplementairesConcatIdChecks;
    String idTypeDemande;
    String idTypeVisa;
    String idTypeVisaPrecedent;


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

    public void setDossiersSup(List<String   > dossiersSup) {
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

    public String getIddemande() {
        return iddemande;
    }

    public void setIddemande(String iddemande) {
        this.iddemande = iddemande;
    }
        public List<String> getDossiersStandardConcatIdChecks() {
        return dossiersStandardConcatIdChecks;
    }

    public void setDossiersStandardConcatIdChecks(List<String> dossiersStandardConcatIdChecks) {
        this.dossiersStandardConcatIdChecks = dossiersStandardConcatIdChecks;
    }

    public List<String> getDossiersSupplementairesConcatIdChecks() {
        return dossiersSupplementairesConcatIdChecks;
    }

    public void setDossiersSupplementairesConcatIdChecks(List<String> dossiersSupplementairesConcatIdChecks) {
        this.dossiersSupplementairesConcatIdChecks = dossiersSupplementairesConcatIdChecks;
    }
        public String getIdTypeVisaPrecedent() {
        return idTypeVisaPrecedent;
    }

    public void setIdTypeVisaPrecedent(String idTypeVisaPrecedent) {
        this.idTypeVisaPrecedent = idTypeVisaPrecedent;
    }
    public String  controleDtoDemande(){
        StringBuilder messageErreur = new StringBuilder();
        try {
            if (this.getDemandeur().getNom() == null || this.getDemandeur().getNom().isEmpty()) {
                messageErreur.append("le nom est requis").append(System.lineSeparator());
            }
            if (this.getDemandeur().getDtn() == null) {
                messageErreur.append("la date de naissance est requise").append(System.lineSeparator());
            }
            if (this.getDemandeur().getAdressemada() == null || this.getDemandeur().getAdressemada().isEmpty()) {
                messageErreur.append("l'adresse a mada est requise").append(System.lineSeparator());
            }
            if (this.getDemandeur().getIdnationalite() == null || this.getDemandeur().getIdnationalite().isEmpty()) {
                messageErreur.append("la nationalite est requise").append(System.lineSeparator());
            }
            if (this.getDemandeur().getTel() == null || this.getDemandeur().getTel().isEmpty()) {
                messageErreur.append("le numero telephonique est requis").append(System.lineSeparator());
            }
            if(messageErreur.toString()!= null){}
        } catch (Exception e) {
            // TODO: handle exception
        }
        return messageErreur.toString();
    }

}
