package com.visa.demo.controller;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nojpa.bd.connexion.DbConnexe;
import com.visa.demo.dto.DemandeDto;
import com.visa.demo.dto.DemandeFicheDto;
import com.visa.demo.dto.DemandeFicheFrontDto;
import com.visa.demo.dto.DemandeurDto;
import com.visa.demo.dto.DossierStandardDto;
import com.visa.demo.dto.DossierSupplementaireDto;
import com.visa.demo.dto.EtatDemandeDto;
import com.visa.demo.dto.HistoriqueEtatDemandeDto;
import com.visa.demo.dto.NationaliteDTO;
import com.visa.demo.dto.PassportDTO;
import com.visa.demo.dto.SituationDeFamilleDTO;
import com.visa.demo.dto.VisaTransformableDTO;
import com.visa.demo.models.CarteResident;
import com.visa.demo.models.CheckDossierStandard;
import com.visa.demo.models.CheckDossierSupplementaire;
import com.visa.demo.models.Demande;
import com.visa.demo.models.Demandeur;
import com.visa.demo.models.DossierStandard;
import com.visa.demo.models.DossierSupplementaire;
import com.visa.demo.models.EtatDemande;
import com.visa.demo.models.HistoriqueEtatDemande;
import com.visa.demo.models.Nationalite;
import com.visa.demo.models.Passport;
import com.visa.demo.models.SituationDeFamille;
import com.visa.demo.models.TypeDemande;
import com.visa.demo.models.TypeVisa;
import com.visa.demo.models.Visa;
import com.visa.demo.models.lib.DemandeDetailSansDossierLib;
import com.visa.demo.models.lib.DemandeLib;
import com.visa.demo.models.obj.DemandeObj;
import com.visa.demo.utils.Comparaison;

@Controller
@RequestMapping("/demande")
public class DemandeController {

    @GetMapping
    private String getAll(Model model) throws Exception {
        Connection c = null;
        try {
            c = new DbConnexe().getConnection();
            model.addAttribute("listeDemandes", new DemandeLib().findAll(c));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return "pages/demande/list";
    }

    @GetMapping("/scan")
    private String scanDemande(Model model) throws Exception {
        return "pages/demande/scan/scan";
    }

    @GetMapping("/form")
    private ModelAndView form() throws Exception {
        ModelAndView modelAndView = new ModelAndView("pages/demande/form");

        try {
            DbConnexe dbConnexe = new DbConnexe();
            Connection c = dbConnexe.getConnection();
            modelAndView.addObject("demandeurs", new Demandeur().findAll(c));
            modelAndView.addObject("nationalites", new Nationalite().findAll(c));
            modelAndView.addObject("situationdefamilles", new SituationDeFamille().findAll(c));
            modelAndView.addObject("typedemandes", new TypeDemande().findAll(c));
            modelAndView.addObject("typevisas", new TypeVisa().findAll(c));
            modelAndView.addObject("dossierstandards", new DossierStandard().findAll(c));
            modelAndView.addObject("dossiersupplementaires", new DossierSupplementaire().findAll(c));
            modelAndView.addObject("formulaire", new DemandeDto());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PostMapping
    private String save(RedirectAttributes redirectAttributes, @ModelAttribute("formulaire") DemandeDto dto)
            throws Exception {
        StringBuilder messageErreur = new StringBuilder(dto.controleDtoDemande());
        if (!messageErreur.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", messageErreur.toString());
            return "redirect:/demande/form";
        }
        DbConnexe dbConnexe = new DbConnexe();
        Connection c = dbConnexe.getConnection();
        HistoriqueEtatDemande hedmd = new HistoriqueEtatDemande();
        try {
            if (c != null) {
                c.setAutoCommit(false);
            }
            if (dto.getIdTypeDemande().equals("TYPDMD000001")) {
                Demande demande = new Demande();
                
                demande = demande.save(c, null, dto.getDemandeur(), dto.getPassport(), dto.getVisatransformable(),
                        dto.getDossiersStandard(), dto.getDossiersSup(), dto.getIdTypeDemande(),
                        dto.getIdTypeVisa(), "ETATDMD000001", dto.getDate());
                hedmd.setIddemande(demande.getId());
                //possible miova
                hedmd.setDaty(LocalDate.now());
                hedmd.setIdetatdemande(demande.getIdetatdemande());
                hedmd.save(c);
            } else {
                Demande demandeSource = new Demande();
                DemandeObj demandeObj = new DemandeObj();
                if (dto.getDemandeur().getId() == null || dto.getDemandeur().getId().isEmpty()) {
                    CarteResident carteResident = new CarteResident();
                    Visa visa = new Visa();
                    demandeSource = demandeSource.save(c, null, dto.getDemandeur(), dto.getPassport(),
                            dto.getVisatransformable(),
                            dto.getDossiersStandard(), dto.getDossiersSup(),
                            "TYPDMD000001", dto.getIdTypeVisa(), "ETATDMD000003", dto.getDate());

                    // // creation carte resident
                    carteResident.setIddemande(demandeSource.getId());
                    carteResident.setIdpassport(dto.getPassport().getId());
                    carteResident.setDatedebut(dto.getCarte().getDatedebut());
                    carteResident.setDateexpiration(dto.getCarte().getDateexpiration());
                    carteResident.setReference(dto.getCarte().getReference());
                    carteResident.insert(c);

                    // // creation visa
                    visa.setIddemande(demandeSource.getId());
                    visa.setIdpassport(dto.getPassport().getId());
                    visa.setDatedebut(dto.getVisa().getDatedebut());
                    visa.setDateexpiration(dto.getVisa().getDateexpiration());
                    visa.setReference(dto.getVisa().getReference());
                    visa.setIdtypevisa(dto.getIdTypeVisa());
                    visa.insert(c);

                } else {
                    demandeObj = Demande.getByIdDemandeur(dto.getDemandeur().getId(), c);
                    if (dto.getIdTypeDemande().equals("TYPDMD000002")) {
                        if (Comparaison.comparerDeuxInstances(demandeObj.getPassport(), dto.getPassport()) == -1) {
                            messageErreur.append("on n'a pas besoin de nouveau passport pour ce type demande")
                                    .append(System.lineSeparator());
                        }
                    }
                    if (Comparaison.comparerDeuxInstances(demandeObj.getVisatransformable(),
                            dto.getVisatransformable()) == -1) {
                        messageErreur.append("aucun nouveau visa transformable n'est requis")
                                .append(System.lineSeparator());
                    }
                    if (Comparaison.comparerDeuxInstances(demandeObj.getDemandeur(), dto.getDemandeur()) == -1) {
                        messageErreur.append(
                                "aucune nouvelle information de demandeur n'est necessaire pour ce type de demande")
                                .append(System.lineSeparator());
                    }
                    demandeSource = demandeSource.getInstanceByDemandeObj(demandeObj);
                    if (messageErreur.toString() != null && !messageErreur.toString().isEmpty()) {
                        throw new Exception("");
                    }
                }
                Demande demande = new Demande();
                Passport p = dto.getNewpassport().getDatedelivrance() != null
                        && dto.getNewpassport().getDateexpiration() != null ? dto.getNewpassport() : dto.getPassport();
                if (dto.getIdTypeDemande().equals("TYPDMD000003")) {
                    if (Comparaison.comparerDeuxInstances(p, dto.getPassport()) == -1) {
                        p.setIddemandeur(dto.getDemandeur().getId());
                        p.insert(c);
                    } else {
                        messageErreur.append("un nouveau passport est requis")
                                .append(System.lineSeparator());
                        throw new Exception(messageErreur.toString());
                    }
                }
                demande = demande.save(c, demandeSource.getId(), dto.getDemandeur(), p,
                        dto.getVisatransformable(),
                        dto.getDossiersStandard(), dto.getDossiersSup(), dto.getIdTypeDemande(), dto.getIdTypeVisa(),
                        "ETATDMD000001", dto.getDate());
                hedmd.setIddemande(demande.getId());
                // possible miova
                hedmd.setDaty(LocalDate.now());
                hedmd.setIdetatdemande(demande.getIdetatdemande());
                
                hedmd.save(c);
            }
            if (c != null) {
                c.commit();
            }

            redirectAttributes.addFlashAttribute("message", "Demande Crée avec succes !");
        } catch (Exception e) {
            e.printStackTrace();
            if (c != null) {
                c.rollback();
            }
            if (!messageErreur.isEmpty()) {
                redirectAttributes.addFlashAttribute("error", messageErreur.toString());
            } else {
                redirectAttributes.addFlashAttribute("error", e.getMessage());
            }
        } finally {
            if (c != null) {
                c.setAutoCommit(true);
                c.close();
            }
        }
        return "redirect:/demande/form";
    }

    @GetMapping("/detail")
    public ModelAndView getDemandeDetailInForm(@RequestParam("id") String identifiant) throws Exception {
        ModelAndView mv = new ModelAndView("pages/demande/detail");
        Connection c = null;
        try {
            c = new DbConnexe().getConnection();
            DemandeDetailSansDossierLib demande = new DemandeDetailSansDossierLib().findByid(c, identifiant);
            String apresWhere = "iddemande='" + identifiant + "'";
            List<CheckDossierStandard> dossierStandardsCheckes = new CheckDossierStandard().select(c, apresWhere, null);
            List<CheckDossierSupplementaire> dossierSupplementairesCheckes = new CheckDossierSupplementaire().select(c,
                    apresWhere, null);
                
            Demandeur demandeur = new Demandeur().findByid(c, demande.getIddemandeur());
            mv.addObject("demandeur", demandeur);
            mv.addObject("demande", demande);
            mv.addObject("dossierstandardscheckes", dossierStandardsCheckes);
            mv.addObject("dossiersupplementairescheckes", dossierSupplementairesCheckes);
            mv.addObject("nationalites", new Nationalite().findAll(c));
            mv.addObject("situationdefamilles", new SituationDeFamille().findAll(c));
            mv.addObject("typedemandes", new TypeDemande().findAll(c));
            mv.addObject("typevisas", new TypeVisa().findAll(c));
            mv.addObject("dossierstandards", new DossierStandard().findAll(c));
            mv.addObject("dossiersupplementaires", new DossierSupplementaire().findAll(c));
            mv.addObject("formulaire", new DemandeDto());

            if (demandeur != null) {

            String pdpBase64 = null;
            String signatureBase64 = null;

            if (demandeur.getPdp() != null && demandeur.getPdp().length > 0) {
                pdpBase64 = Base64.getEncoder().encodeToString(demandeur.getPdp());
            }

            if (demandeur.getSignatures() != null && demandeur.getSignatures().length > 0) {
                signatureBase64 = Base64.getEncoder().encodeToString(demandeur.getSignatures());
            }

            mv.addObject("pdpBase64", pdpBase64);
            mv.addObject("signatureBase64", signatureBase64);
        }
        } catch (Exception e) {
            e.printStackTrace();
            mv.addObject("error", e.getMessage());
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return mv;
    }

    @PostMapping("/update")
    private String update(RedirectAttributes redirectAttributes, @ModelAttribute("formulaire") DemandeDto dto)
            throws Exception {
        DbConnexe dbConnexe = new DbConnexe();
        Connection c = dbConnexe.getConnection();
        try {
            Demande demande = new Demande();
            demande.setId(dto.getIddemande());
            demande.update(c, dto.getIddemande(), dto.getDemandeur(), dto.getPassport(), dto.getVisatransformable(),
                    dto.getDossiersStandard(), dto.getDossiersSup(), dto.getDossiersStandardConcatIdChecks(),
                    dto.getDossiersSupplementairesConcatIdChecks(), dto.getIdTypeDemande(),
                    dto.getIdTypeVisa(), dto.getIdTypeVisaPrecedent(), dto.getDate());

            redirectAttributes.addFlashAttribute("message", "Demande modifiée avec succes !");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return "redirect:/demande/form";
    }
    @GetMapping("/fiche/{id}")
    public ModelAndView getFicheById(@PathVariable("id") String id) throws Exception {
        ModelAndView mav = new ModelAndView("pages/demande/fiche");
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
            List<DossierStandardDto> dossierStandardVerifies = new DossierStandardDto().select(c, afterWhereHistorique, null);
            List<DossierStandardDto> dossierStandardNonVerifies = new DossierStandardDto().getDossiersNonVerifiesByIdDemande(c, id);
            dossierStandardVerifies.addAll(dossierStandardNonVerifies);
            dossierStandardVerifies.addAll(dossierStandardNonVerifies);
            List<DossierSupplementaireDto> dossierSupplementairesVerifies = new DossierSupplementaireDto().select(c,
                    afterWhereHistorique, null);
            List<DossierSupplementaireDto> dossierSupplementairesNonVerifies = new DossierSupplementaireDto().getDossiersNonVerifiesByIdDemande(c, id);
            dossierSupplementairesVerifies.addAll(dossierSupplementairesNonVerifies);
        
            // VisaTransformableDTO vtDto = new VisaTransformableDTO().findByid(c, );
            byte[] qrCode = d.getQrcode();
            String base64 = Base64.getEncoder().encodeToString(qrCode);
            dmdrDto.setNationalite(nationalite);
            dmdrDto.setSituationdefamille(situationfamiliale);
            dto.setId(d.getId());
            dto.setIdoriginal(d.getIdoriginal());
            dto.setDemandeur(dmdrDto);
            dto.setEtatdemande(etatDemandeDto);
            dto.setPassport(passport);
            dto.setVisatransformable(visatransformableDto);
            dto.setHistoriquesEtats(historiqueEtats);
            dto.setDossierStandard(dossierStandardVerifies);
            dto.setDossierSupplementaire(dossierSupplementairesVerifies);
            dto.setDossierStandard(dossierStandardVerifies);
            // dto.setDossierSupplementaire(dossierSupplementairesVerifies);
            mav.addObject("qrcode", base64);
            mav.addObject("fichedemande", dto);

            String pdpBase64 = null;
            String signatureBase64 = null;

            if (dmdr.getPdp() != null && dmdr.getPdp().length > 0) {
                pdpBase64 = Base64.getEncoder().encodeToString(dmdr.getPdp());
            }

            if (dmdr.getSignatures() != null && dmdr.getSignatures().length > 0) {
                signatureBase64 = Base64.getEncoder().encodeToString(dmdr.getSignatures());
            }

            mav.addObject("pdpBase64", pdpBase64);
            mav.addObject("signatureBase64", signatureBase64);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return mav;
    }
}
