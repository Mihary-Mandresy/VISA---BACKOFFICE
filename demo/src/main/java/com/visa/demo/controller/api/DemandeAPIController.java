package com.visa.demo.controller.api;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

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
    public DemandeFicheFrontDto getById(@PathVariable("id") String id) throws Exception {
        Connection c = null;
        DemandeFicheFrontDto dto = new DemandeFicheFrontDto();
        if (id == null || id.isEmpty()) {
            throw new Exception("le parametre id doit etre fourni");
        }
        try {
            c = new DbConnexe().getConnection();
            Demande d = new Demande().findByid(c, id);
            Demandeur dmdr = new Demandeur().findByid(c, d.getIddemandeur());

            DemandeurDto dmdrDto = new DemandeurDto();
            dmdrDto = dmdrDto.copyToDemandeurDto(dmdr);
            NationaliteDTO nationalite = new NationaliteDTO().findByid(c, dmdrDto.getIdnationalite());
            SituationDeFamilleDTO situationfamiliale = new SituationDeFamilleDTO().findByid(c,
                    dmdrDto.getIdsituationdefamille());
            String afterWhereHistorique = "iddemande='" + id +"'";
            
            List<HistoriqueEtatDemandeDto> historiqueEtats = new HistoriqueEtatDemandeDto().select(c, afterWhereHistorique,
                    null);
            EtatDemande etatDemande = new EtatDemande().findByid(c, d.getIdetatdemande());
            EtatDemandeDto etatDemandeDto = new EtatDemandeDto();
            PassportDTO passport = new PassportDTO().findByid(c, d.getIdpassport());
            VisaTransformableDTO visatransformableDto = new VisaTransformableDTO().findByid(c, d.getIdvisatransformable());
            etatDemandeDto.copierDepuisEtatDemande(etatDemande);
            List<DossierStandardDto> dossierStandard = new DossierStandardDto().select(c, afterWhereHistorique, null);
            List<DossierSupplementaireDto> dossierSupplementaires = new DossierSupplementaireDto().select(c,
                    afterWhereHistorique, null);
            // VisaTransformableDTO vtDto = new VisaTransformableDTO().findByid(c, );
            dmdrDto.setNationalite(nationalite);
            dmdrDto.setSituationdefamille(situationfamiliale);
            dto.setId(d.getId());
            dto.setIdoriginal(d.getIdoriginal());
            dto.setDemandeur(dmdrDto);
            dto.setEtatdemande(etatDemandeDto);
            dto.setPassport(passport);
            dto.setVisatransformable(visatransformableDto);
            dto.setHistoriquesEtats(historiqueEtats);
            dto.setDossierStandard(dossierStandard);
            dto.setDossierSupplementaire(dossierSupplementaires);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return dto;
    }
    @GetMapping
    public List<DemandeRechercheDto> findBySearchValue(@RequestParam("id")String id) throws Exception{
        if(id!=null && !id.isEmpty() && (!id.contains("DMD") && !id.contains("PASS"))){
            throw new Exception("il faut un id passport ou un id demande");
        }
        Connection c = null;
        Demandeur dmdr= new Demandeur();
        Demande d = new Demande();
        List<DemandeRechercheDto> resultats = new ArrayList<DemandeRechercheDto>();
        if(id == null || id.isEmpty()){
            resultats.addAll(new DemandeRechercheDto().findAll(c));
        }
        try {
            c = new DbConnexe().getConnection();
            if(id != null){

                if(id.contains("DMD")){
                    d = new Demande().findByid(c, id);
                    dmdr = dmdr.findByid(c,d.getIddemandeur());
                    resultats.add(new DemandeRechercheDto().findByid(c,d.getId()));
                }
                if(id.contains("PASS")){
                    Passport  p = new Passport().findByid(c, id);
                    dmdr = new Demandeur().findByid(c, p.getIddemandeur());
                }
            }
            if(dmdr.getId() != null){
                String nomCompletDemandeur = dmdr.getNom().concat(" ").concat(dmdr.getPrenom());
                String conditionIddemande = "";
                if(d.getId()!=null){
                    conditionIddemande = "and id!='"+d.getId()+"'";
                }
                String afterWhere = "'nomDemandeur'='"+nomCompletDemandeur+"' "+conditionIddemande;
                resultats.addAll(new DemandeRechercheDto().select(c,afterWhere,null));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("une erreur est survenue lors de la recherche");
        } finally {
            if (c != null) {
                c.close();
            }
        }
        
        
        return resultats;
    }
}
