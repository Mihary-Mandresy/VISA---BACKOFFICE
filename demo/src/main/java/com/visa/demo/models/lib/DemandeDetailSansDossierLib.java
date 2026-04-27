package com.visa.demo.models.lib;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.nojpa.bd.entity.Entity;

public class DemandeDetailSansDossierLib extends Entity<DemandeDetailSansDossierLib> {
    private String iddemandeur;
    private String nomdemandeur;
    private String prenomdemandeur;
    private LocalDate dtndemandeur;
    private String idtypedemande;
    private String idtypevisa;
    private String idsituationdefamille;
    private String idnationalite;
    private String profession;
    private String adressemada;
    private String email;
    private String tel;
    private String idpassport;
    private String numeropassport;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate datedelivrancepassport;
    private LocalDate dateexpirationpassport;
    private String idvisatransformable;
    private String referencevt;
    private String lieuentree;
    private LocalDate dateentreemada;
    private LocalDate dateexpirationvt;

    public String getReferencevt() {
        return referencevt;
    }

    public void setReferencevt(String referencevt) {
        this.referencevt = referencevt;
    }

    public String getLieuentree() {
        return lieuentree;
    }

    public void setLieuentree(String lieuentree) {
        this.lieuentree = lieuentree;
    }

    public LocalDate getDateentreemada() {
        return dateentreemada;
    }

    public void setDateentreemada(LocalDate dateentreemada) {
        this.dateentreemada = dateentreemada;
    }

    public LocalDate getDateexpirationvt() {
        return dateexpirationvt;
    }

    public void setDateexpirationvt(LocalDate dateexpirationvt) {
        this.dateexpirationvt = dateexpirationvt;
    }

    public DemandeDetailSansDossierLib() {
        setNomTable("v_demande_details_sans_dossier");
    }

    public String getNomdemandeur() {
        return nomdemandeur;
    }

    public void setNomdemandeur(String nomdemandeur) {
        this.nomdemandeur = nomdemandeur;
    }

    public String getPrenomdemandeur() {
        return prenomdemandeur;
    }

    public void setPrenomdemandeur(String prenomdemandeur) {
        this.prenomdemandeur = prenomdemandeur;
    }

    public LocalDate getDtndemandeur() {
        return dtndemandeur;
    }

    public void setDtndemandeur(LocalDate dtndemandeur) {
        this.dtndemandeur = dtndemandeur;
    }

    public String getIdsituationdefamille() {
        return idsituationdefamille;
    }

    public void setIdsituationdefamille(String idsituationdefamille) {
        this.idsituationdefamille = idsituationdefamille;
    }

    public String getIdnationalite() {
        return idnationalite;
    }

    public void setIdnationalite(String idnationalite) {
        this.idnationalite = idnationalite;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getAdressemada() {
        return adressemada;
    }

    public void setAdressemada(String adressemada) {
        this.adressemada = adressemada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNumeropassport() {
        return numeropassport;
    }

    public void setNumeropassport(String numeropassport) {
        this.numeropassport = numeropassport;
    }

    public LocalDate getDatedelivrancepassport() {
        return datedelivrancepassport;
    }

    public void setDatedelivrancepassport(LocalDate datedelivrancepassport) {
        this.datedelivrancepassport = datedelivrancepassport;
    }

    public LocalDate getDateexpirationpassport() {
        return dateexpirationpassport;
    }

    public void setDateexpirationpassport(LocalDate dateexpirationpassport) {
        this.dateexpirationpassport = dateexpirationpassport;
    }

    public String getIdtypedemande() {
        return idtypedemande;
    }

    public void setIdtypedemande(String idtypedemande) {
        this.idtypedemande = idtypedemande;
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

    public String getIdpassport() {
        return idpassport;
    }

    public void setIdpassport(String idpassport) {
        this.idpassport = idpassport;
    }

    public String getIdvisatransformable() {
        return idvisatransformable;
    }

    public void setIdvisatransformable(String idvisatransformable) {
        this.idvisatransformable = idvisatransformable;
    }
}
