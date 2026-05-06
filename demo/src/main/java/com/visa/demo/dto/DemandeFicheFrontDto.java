package com.visa.demo.dto;

import java.util.List;

public class DemandeFicheFrontDto {
    private String id;
    private String idoriginal;
    private DemandeurDto demandeur;
    private EtatDemandeDto etatdemande;
    private VisaTransformableDTO visatransformable;
    private PassportDTO passport;
    private List<DossierStandardDto> dossierStandard;
    private List<DossierSupplementaireDto> dossierSupplementaire;
    private List<HistoriqueEtatDemandeDto> historiquesEtats;
    public String getIdoriginal() {
        return idoriginal;
    }

    public void setIdoriginal(String idoriginal) {
        this.idoriginal = idoriginal;
    }

    public DemandeurDto getDemandeur() {
        return demandeur;
    }

    public void setDemandeur(DemandeurDto demandeur) {
        this.demandeur = demandeur;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VisaTransformableDTO getVisatransformable() {
        return visatransformable;
    }

    public void setVisatransformable(VisaTransformableDTO visatransformable) {
        this.visatransformable = visatransformable;
    }

    public PassportDTO getPassport() {
        return passport;
    }

    public void setPassport(PassportDTO passport) {
        this.passport = passport;
    }

    public List<HistoriqueEtatDemandeDto> getHistoriquesEtats() {
        return this.historiquesEtats;
    }

    public void setHistoriquesEtats(List<HistoriqueEtatDemandeDto> historiqueEtats) {
        this.historiquesEtats = historiqueEtats;
    }

    public List<DossierStandardDto> getDossierStandard() {
        return dossierStandard;
    }

    public void setDossierStandard(List<DossierStandardDto> dossierStandard) {
        this.dossierStandard = dossierStandard;
    }

    public List<DossierSupplementaireDto> getDossierSupplementaire() {
        return dossierSupplementaire;
    }

    public void setDossierSupplementaire(List<DossierSupplementaireDto> dossierSupplementaire) {
        this.dossierSupplementaire = dossierSupplementaire;
    }

    public EtatDemandeDto getEtatdemande() {
        return etatdemande;
    }

    public void setEtatdemande(EtatDemandeDto etatdemande) {
        this.etatdemande = etatdemande;
    }
}
