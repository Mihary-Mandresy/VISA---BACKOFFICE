package com.visa.demo.dto;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class SuiviEtatDemandeDto {
    private DemandeDetailDto demandeDetail;
    private List<EtatDemandeDto> etatDemandes;
    private List<DossierStandardDto> dossierStandardsDtos;
    private List<DossierSupplementaireDto> dossierSupplementaireDtos;

    public DemandeDetailDto getDemandeDetail() {
        return demandeDetail;
    }

    public List<EtatDemandeDto> getEtatDemandes() {
        return etatDemandes;
    }

    public List<DossierStandardDto> getDossierStandardsDtos() {
        return dossierStandardsDtos;
    }

    public List<DossierSupplementaireDto> getDossierSupplementaireDtos() {
        return dossierSupplementaireDtos;
    }

    public void setDemandeDetail(DemandeDetailDto demandeDetail) {
        this.demandeDetail = demandeDetail;
    }

    public void setEtatDemandes(List<EtatDemandeDto> etatDemandes) {
        this.etatDemandes = etatDemandes;
    }

    public void setDossierStandardsDtos(List<DossierStandardDto> dossierStandardsDtos) {
        this.dossierStandardsDtos = dossierStandardsDtos;
    }

    public void setDossierSupplementaireDtos(List<DossierSupplementaireDto> dossierSupplementaireDtos) {
        this.dossierSupplementaireDtos = dossierSupplementaireDtos;
    }
}
