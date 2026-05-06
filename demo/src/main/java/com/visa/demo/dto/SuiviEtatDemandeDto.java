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

    public List<SuiviEtatDemandeDto> genererListeAvecValeursAttributs(Connection c, List<DemandeDetailDto> demandesDetails) throws Exception {
        List<SuiviEtatDemandeDto> results = new ArrayList<SuiviEtatDemandeDto>();
        if (demandesDetails == null) {
            throw new Exception("la liste des demandes ne doit pas etre null");
        }

        for (int i = 0; i < demandesDetails.size(); i++) {
            SuiviEtatDemandeDto suivi = new SuiviEtatDemandeDto();
            DemandeDetailDto demandeDetail = demandesDetails.get(i);

            String valeurRecherche = demandeDetail.getId();

            String apresWhereDossier = "iddemande='" + valeurRecherche + "'";
            List<DossierStandardDto> dossierStandardsDtos = new DossierStandardDto().select(c, apresWhereDossier, null);

            List<DossierSupplementaireDto> dossierSupplementaires = new DossierSupplementaireDto().select(c,
                    apresWhereDossier, null);

            List<EtatDemandeDto> etatsDemandes = new EtatDemandeDto().select(c, apresWhereDossier, null);
            suivi.setDemandeDetail(demandeDetail);
            suivi.setDossierStandardsDtos(dossierStandardsDtos);
            suivi.setDossierSupplementaireDtos(dossierSupplementaires);
            suivi.setEtatDemandes(etatsDemandes);
            results.add(suivi);
        }
        return results;
    }

}
