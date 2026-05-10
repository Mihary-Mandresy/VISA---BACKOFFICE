package com.visa.demo.controller.api;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nojpa.bd.connexion.DbConnexe;
import com.visa.demo.dto.DemandeFicheFrontDto;
import com.visa.demo.dto.DemandeRechercheDto;
import com.visa.demo.dto.DemandeurDto;
import com.visa.demo.dto.DossierStandardDto;
import com.visa.demo.dto.DossierSupplementaireDto;
import com.visa.demo.dto.EtatDemandeDto;
import com.visa.demo.dto.HistoriqueEtatDemandeDto;
import com.visa.demo.dto.NationaliteDTO;
import com.visa.demo.dto.PassportDTO;
import com.visa.demo.dto.SituationDeFamilleDTO;
import com.visa.demo.dto.TypeVisaDto;
import com.visa.demo.dto.VisaTransformableDTO;
import com.visa.demo.models.Demande;
import com.visa.demo.models.Demandeur;
import com.visa.demo.models.EtatDemande;
import com.visa.demo.models.Passport;
import com.visa.demo.models.obj.DemandeObj;

@RestController
@RequestMapping("/demande/api")
@CrossOrigin(origins = "http://localhost:5173")
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        Connection c = null;
        try {
            // Validation de l'ID
            if (id == null || id.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(Map.of(
                                "error", "Le paramètre id doit être fourni",
                                "status", HttpStatus.BAD_REQUEST.value()));
            }

            c = new DbConnexe().getConnection();
            Demande d = new Demande().findByid(c, id);

            // Vérifier si la demande existe
            if (d == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of(
                                "error", "La demande avec l'ID '" + id + "' n'existe pas",
                                "status", HttpStatus.NOT_FOUND.value()));
            }

            // Récupérer les données
            Demandeur dmdr = new Demandeur().findByid(c, d.getIddemandeur());
            if (dmdr == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of(
                                "error", "Le demandeur associé n'existe pas",
                                "status", HttpStatus.NOT_FOUND.value()));
            }

            // Construction du DTO
            DemandeFicheFrontDto dto = new DemandeFicheFrontDto();

            // Demandeur avec ses informations
            DemandeurDto dmdrDto = new DemandeurDto().copyToDemandeurDto(dmdr);
            NationaliteDTO nationalite = new NationaliteDTO().findByid(c, dmdrDto.getIdnationalite());
            SituationDeFamilleDTO situationfamiliale = new SituationDeFamilleDTO().findByid(c,
                    dmdrDto.getIdsituationdefamille());

            dmdrDto.setNationalite(nationalite);
            dmdrDto.setSituationdefamille(situationfamiliale);

            // Historique des états
            String afterWhereHistorique = "iddemande='" + id + "'";
            List<HistoriqueEtatDemandeDto> historiqueEtats = new HistoriqueEtatDemandeDto().select(c,
                    afterWhereHistorique, null);

            // État actuel de la demande
            EtatDemande etatDemande = new EtatDemande().findByid(c, d.getIdetatdemande());
            EtatDemandeDto etatDemandeDto = new EtatDemandeDto();
            etatDemandeDto.copierDepuisEtatDemande(etatDemande);

            // Passport et Visa
            PassportDTO passport = new PassportDTO().findByid(c, d.getIdpassport());
            VisaTransformableDTO visatransformableDto = new VisaTransformableDTO().findByid(c,
                    d.getIdvisatransformable());

            // Dossiers
            List<DossierStandardDto> dossierStandard = new DossierStandardDto().select(c, afterWhereHistorique, null);
            List<DossierStandardDto> dossierStandardNonVerifies = new DossierStandardDto()
                    .getDossiersNonVerifiesByIdDemande(c, id);
            dossierStandard.addAll(dossierStandardNonVerifies);

            List<DossierSupplementaireDto> dossierSupplementaires = new DossierSupplementaireDto().select(c,
                    afterWhereHistorique, null);
            List<DossierSupplementaireDto> dossierSupplementairesNonVerifies = new DossierSupplementaireDto()
                    .getDossiersNonVerifiesByIdDemande(c, id);
            dossierSupplementaires.addAll(dossierSupplementairesNonVerifies);

            // Assemblage final du DTO
            dto.setId(d.getId());
            dto.setTypevisa(new TypeVisaDto().findByid(c, d.getIdtypevisa()));
            dto.setIdoriginal(d.getIdoriginal());
            dto.setDemandeur(dmdrDto);
            dto.setEtatdemande(etatDemandeDto);
            dto.setPassport(passport);
            dto.setVisatransformable(visatransformableDto);
            dto.setHistoriquesEtats(historiqueEtats);
            dto.setDossierStandard(dossierStandard);
            dto.setDossierSupplementaire(dossierSupplementaires);

            // Retourner la réponse avec succès
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "data", dto,
                    "message", "Demande récupérée avec succès"));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error", "Une erreur est survenue lors de la récupération de la demande",
                            "details", e.getMessage(),
                            "status", HttpStatus.INTERNAL_SERVER_ERROR.value()));
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (Exception e) {
                }
            }
        }
    }

    @GetMapping
    public ResponseEntity<?> findBySearchValue(@RequestParam(value = "id", required = false) String id) {
        Connection c = null;

        try {
            // Validation du format de l'ID
            if (id != null && !id.isEmpty()) {
                if (!id.contains("DMD") && !id.contains("PASS")) {
                    return ResponseEntity
                            .status(HttpStatus.BAD_REQUEST)
                            .body(Map.of(
                                    "error",
                                    "Format invalide. Veuillez entrer un ID de demande (DMD...) ou un ID de passport (PASS...)",
                                    "status", HttpStatus.BAD_REQUEST.value()));
                }
            }

            c = new DbConnexe().getConnection();
            List<DemandeRechercheDto> resultats = new ArrayList<>();

            // Cas 1: Pas d'ID - retourner toutes les demandes
            if (id == null || id.isEmpty()) {
                resultats.addAll(new DemandeRechercheDto().findAll(c));

                if (resultats.isEmpty()) {
                    return ResponseEntity
                            .status(HttpStatus.NOT_FOUND)
                            .body(Map.of(
                                    "error", "Aucune demande trouvée",
                                    "status", HttpStatus.NOT_FOUND.value()));
                }

                return ResponseEntity.ok(Map.of(
                        "success", true,
                        "data", resultats,
                        "count", resultats.size(),
                        "message", "Toutes les demandes récupérées avec succès"));
            }

            // Cas 2: Recherche par ID de demande
            if (id.contains("DMD")) {
                Demande d = new Demande().findByid(c, id);

                if (d == null) {
                    return ResponseEntity
                            .status(HttpStatus.NOT_FOUND)
                            .body(Map.of(
                                    "error", "La demande avec l'ID '" + id + "' n'existe pas",
                                    "status", HttpStatus.NOT_FOUND.value()));
                }

                Demandeur dmdr = new Demandeur().findByid(c, d.getIddemandeur());
                DemandeRechercheDto dto = new DemandeRechercheDto().findByid(c, d.getId());

                if (dto != null) {
                    resultats.add(dto);
                }

                // Rechercher les autres demandes du même demandeur
                if (dmdr != null && dmdr.getId() != null) {
                    String nomComplet = dmdr.getNom() + " " + dmdr.getPrenom();
                    String condition = "nomDemandeur='" + nomComplet + "' and id!='" + id + "'";
                    List<DemandeRechercheDto> autresDemandes = new DemandeRechercheDto().select(c, condition, null);
                    resultats.addAll(autresDemandes);
                }
            }

            // Cas 3: Recherche par ID de passport
            else if (id.contains("PASS")) {
                Passport p = new Passport().findByid(c, id);

                if (p == null) {
                    return ResponseEntity
                            .status(HttpStatus.NOT_FOUND)
                            .body(Map.of(
                                    "error", "Le passport avec l'ID '" + id + "' n'existe pas",
                                    "status", HttpStatus.NOT_FOUND.value()));
                }

                Demandeur dmdr = new Demandeur().findByid(c, p.getIddemandeur());

                if (dmdr == null || dmdr.getId() == null) {
                    return ResponseEntity
                            .status(HttpStatus.NOT_FOUND)
                            .body(Map.of(
                                    "error", "Aucun demandeur trouvé pour ce passport",
                                    "status", HttpStatus.NOT_FOUND.value()));
                }

                String nomCompletDemandeur = dmdr.getNom() + " " + dmdr.getPrenom();
                String afterWhere = "nomDemandeur='" + nomCompletDemandeur + "'";
                resultats.addAll(new DemandeRechercheDto().select(c, afterWhere, null));
            }

            // Vérifier si des résultats ont été trouvés
            if (resultats.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of(
                                "error", "Aucune demande trouvée pour l'identifiant: " + id,
                                "status", HttpStatus.NOT_FOUND.value()));
            }

            // Retourner les résultats
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "data", resultats,
                    "count", resultats.size(),
                    "message", resultats.size() + " demande(s) trouvée(s)"));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error", "Une erreur technique est survenue lors de la recherche",
                            "details", e.getMessage(),
                            "status", HttpStatus.INTERNAL_SERVER_ERROR.value()));
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (Exception e) {
                }
            }
        }
    }

}
