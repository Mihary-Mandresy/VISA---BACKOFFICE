package com.visa.demo.models.obj;

import java.time.LocalDate;

import com.visa.demo.models.Demande;
import com.visa.demo.models.Demandeur;
import com.visa.demo.models.EtatDemande;
import com.visa.demo.models.Passport;
import com.visa.demo.models.TypeDemande;
import com.visa.demo.models.TypeVisa;
import com.visa.demo.models.Visatransformable;

public class DemandeObj {
    private String id;
    private TypeDemande typeDemande;
    private Visatransformable visatransformable;
    private Passport passport;
    private Demandeur demandeur;
    private TypeVisa typeVisa;
    private LocalDate datecreation;
    private EtatDemande etatDemande;
    private Demande original;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TypeDemande getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(TypeDemande typeDemande) {
        this.typeDemande = typeDemande;
    }

    public Visatransformable getVisatransformable() {
        return visatransformable;
    }

    public void setVisatransformable(Visatransformable visatransformable) {
        this.visatransformable = visatransformable;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Demandeur getDemandeur() {
        return demandeur;
    }

    public void setDemandeur(Demandeur demandeur) {
        this.demandeur = demandeur;
    }

    public TypeVisa getTypeVisa() {
        return typeVisa;
    }

    public void setTypeVisa(TypeVisa typeVisa) {
        this.typeVisa = typeVisa;
    }

    public LocalDate getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(LocalDate datecreation) {
        this.datecreation = datecreation;
    }

    public EtatDemande getEtatDemande() {
        return etatDemande;
    }

    public void setEtatDemande(EtatDemande etatDemande) {
        this.etatDemande = etatDemande;
    }

    public Demande getOriginal() {
        return original;
    }

    public void setOriginal(Demande original) {
        this.original = original;
    }

}