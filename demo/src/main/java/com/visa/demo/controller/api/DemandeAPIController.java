package com.visa.demo.controller.api;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nojpa.bd.connexion.DbConnexe;
import com.visa.demo.dto.DemandeDetailDto;
import com.visa.demo.dto.DossierStandardDto;
import com.visa.demo.dto.DossierSupplementaireDto;
import com.visa.demo.dto.EtatDemandeDto;
import com.visa.demo.dto.SuiviEtatDemandeDto;
import com.visa.demo.models.Demande;
import com.visa.demo.models.Demandeur;
import com.visa.demo.models.Passport;
import com.visa.demo.models.obj.DemandeObj;

@RestController
@RequestMapping("/demande/api")
public class DemandeAPIController {

    @GetMapping("/demandeur")
    public DemandeObj getByIdDemandeur(@RequestParam(required = false) String id) throws Exception {
        Connection c = null;
        DemandeObj d = null;
        try {
            c = new DbConnexe().getConnection();
            d = Demande.getByIdDemandeur(id, c);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
        }

        return d;

    }

    @GetMapping
    public List<SuiviEtatDemandeDto> findSearchByValue(@RequestParam("id") String valeuRecherche) throws Exception {
        if (valeuRecherche == null || valeuRecherche.isEmpty()
                || (!valeuRecherche.contains("DMD") && !valeuRecherche.contains("PASS"))) {
            throw new Exception("la valeur pour recherche doit etre un id de passport ou un id demande");
        }
        List<SuiviEtatDemandeDto> resultats = new ArrayList<SuiviEtatDemandeDto>();
        DbConnexe db = new DbConnexe();
        Demandeur demandeur = null;
        DemandeDetailDto demandeDetail = new DemandeDetailDto();
        try {
            Connection c = db.getConnection();
            if (valeuRecherche.contains("DMD")) {
                SuiviEtatDemandeDto suiviEtatDemande = new SuiviEtatDemandeDto();
                String apresWhereDemandeDetail = "id='" + valeuRecherche + "'";
                demandeDetail = new DemandeDetailDto().select(c, apresWhereDemandeDetail, null).get(0);
                List<DossierStandardDto> dossierStandards = new DossierStandardDto().select(c, apresWhereDemandeDetail, null);
                List<DossierSupplementaireDto> dossierSupplementaires = new DossierSupplementaireDto().select(c,
                        apresWhereDemandeDetail, null);
                List<EtatDemandeDto> etatsDemandes = new EtatDemandeDto().select(c, apresWhereDemandeDetail, null);
                suiviEtatDemande.setDemandeDetail(demandeDetail);
                suiviEtatDemande.setDossierStandardsDtos(dossierStandards);
                suiviEtatDemande.setDossierSupplementaireDtos(dossierSupplementaires);
                suiviEtatDemande.setEtatDemandes(etatsDemandes);
                resultats.add(suiviEtatDemande);
                demandeur = new Demandeur().findByid(c, demandeDetail.getIddemandeur());
            }
            if (valeuRecherche.contains("PASS")) {
                Passport passport = new Passport().findByid(c, valeuRecherche);
                demandeur = new Demandeur().findByid(c, passport.getIddemandeur());
            }

            if (demandeur != null && demandeur.getId() != null) {
                String apresWhereDemandeur = "iddemandeur='"+demandeur.getId()+"' and id !='"+demandeDetail.getId()+"' order by datecreation";
                List<DemandeDetailDto> demandesDetaisDto = new DemandeDetailDto().select(c,apresWhereDemandeur,null);
                List<SuiviEtatDemandeDto> suiviEtatDemandeDtos = new SuiviEtatDemandeDto()
                        .genererListeAvecValeursAttributs(c, demandesDetaisDto);
                resultats.addAll(suiviEtatDemandeDtos);
            } else {
                throw new Exception("l'id demandeur doit etre non null ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("une erreur interne est survenue");
        }
        return resultats;
    }
}
