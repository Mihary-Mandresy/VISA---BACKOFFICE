package com.visa.demo.dto;

import java.util.List;

public class DemandeFicheFrontDto {
    private String id;
    private String idoriginal;
    private DemandeurDto demandeurDto;
    private EtatDemandeDto etatDemande;
    VisaTransformableDTO visatransformableDTO;
    PassportDTO passportDTO;

    List<HistoriqueEtatDemandeDto> historiquesEtats;
    List<DossierStandardDto> dossiersStandards;
    List<DossierSupplementaireDto> dossierSupplementaire;

    public String getIdoriginal() {
        return idoriginal;
    }

    public void setIdoriginal(String idoriginal) {
        this.idoriginal = idoriginal;
    }

    public DemandeurDto getDemandeurDto() {
        return demandeurDto;
    }

    public void setDemandeurDto(DemandeurDto demandeurDto) {
        this.demandeurDto = demandeurDto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VisaTransformableDTO getVisatransformableDTo() {
        return visatransformableDTO;
    }

    public void setVisatransformableDTO(VisaTransformableDTO visatransformableDTO) {
        this.visatransformableDTO = visatransformableDTO;
    }

    public PassportDTO getPassportDTO() {
        return passportDTO;
    }

    public void setPassportDTO(PassportDTO passportDTO) {
        this.passportDTO = passportDTO;
    }

    public List<HistoriqueEtatDemandeDto> getHistoriquesEtats() {
        return this.historiquesEtats;
    }

    public void setHistoriquesEtats(List<HistoriqueEtatDemandeDto> historiqueEtats) {
        this.historiquesEtats = historiqueEtats;
    }

    public List<DossierStandardDto> getDossiersStandards() {
        return dossiersStandards;
    }

    public void setDossiersStandards(List<DossierStandardDto> dossiersStandards) {
        this.dossiersStandards = dossiersStandards;
    }

    public List<DossierSupplementaireDto> getDossierSupplementaire() {
        return dossierSupplementaire;
    }

    public void setDossierSupplementaire(List<DossierSupplementaireDto> dossierSupplementaire) {
        this.dossierSupplementaire = dossierSupplementaire;
    }

    public EtatDemandeDto getEtatDemande() {
        return etatDemande;
    }

    public void setEtatDemande(EtatDemandeDto etatDemande) {
        this.etatDemande = etatDemande;
    }
}
