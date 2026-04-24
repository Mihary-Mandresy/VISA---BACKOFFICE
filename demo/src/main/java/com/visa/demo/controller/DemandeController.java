package com.visa.demo.controller;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nojpa.bd.connexion.DbConnexe;
import com.visa.demo.dto.DemandeDto;
import com.visa.demo.models.CheckDossierStandard;
import com.visa.demo.models.CheckDossierSupplementaire;
import com.visa.demo.models.Demande;
import com.visa.demo.models.DossierStandard;
import com.visa.demo.models.DossierSupplementaire;
import com.visa.demo.models.Nationalite;
import com.visa.demo.models.SituationDeFamille;
import com.visa.demo.models.TypeDemande;
import com.visa.demo.models.TypeVisa;
import com.visa.demo.models.lib.DemandeDetailSansDossierLib;
import com.visa.demo.models.lib.DemandeLib;

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
            // TODO: handle exception
            model.addAttribute("error", e.getMessage());
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return "pages/demande/list";
    }

    @GetMapping("/form")
    private ModelAndView form() throws Exception {
        ModelAndView modelAndView = new ModelAndView("pages/demande/form");

        try {
            DbConnexe dbConnexe = new DbConnexe();
            Connection c = dbConnexe.getConnection();

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
        StringBuilder messageErreur = new StringBuilder();
        if (dto.getDemandeur().getNom() == null || dto.getDemandeur().getNom().isEmpty()) {
            messageErreur.append("le nom est requis").append(System.lineSeparator());
        }
        if (dto.getDemandeur().getDtn() == null) {
            messageErreur.append("la date de naissance est requise").append(System.lineSeparator());
        }
        if (dto.getDemandeur().getAdressemada() == null || dto.getDemandeur().getAdressemada().isEmpty()) {
            messageErreur.append("l'adresse a mada est requise").append(System.lineSeparator());
        }
        if (dto.getDemandeur().getIdnationalite() == null || dto.getDemandeur().getIdnationalite().isEmpty()) {
            messageErreur.append("la nationalite est requise").append(System.lineSeparator());
        }
        if (dto.getDemandeur().getTel() == null || dto.getDemandeur().getTel().isEmpty()) {
            messageErreur.append("le numero telephonique est requis").append(System.lineSeparator());
        }
        DbConnexe dbConnexe = new DbConnexe();
        Connection c = dbConnexe.getConnection();
        try {
            Demande demande = new Demande();
            if (dto.getVisatransformable().getDateentreemada() instanceof LocalDate) {

            }
            demande.save(c, dto.getDemandeur(), dto.getPassport(), dto.getVisatransformable(),
                    dto.getDossiersStandard(), dto.getDossiersSup(), dto.getIdTypeDemande(),
                    dto.getIdTypeVisa(), dto.getDate());

            redirectAttributes.addFlashAttribute("message", "Demande Crée avec succes !");
        } catch (Exception e) {
            e.printStackTrace();
            if(!messageErreur.toString().isEmpty()){
                redirectAttributes.addFlashAttribute("error",messageErreur.toString());
            }
            else{
                redirectAttributes.addFlashAttribute("error", e.getMessage());
            }
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return "redirect:/demande/form";
    }

    @GetMapping("/detail")
    public ModelAndView getDemandeDetailInForm(@RequestParam("id") String identifiant) throws Exception {
        ModelAndView mv = new ModelAndView("pages/demande/form");
        Connection c = null;
        try {
            c = new DbConnexe().getConnection();
            DemandeDetailSansDossierLib demande = new DemandeDetailSansDossierLib().findByid(c, identifiant);
            String apresWhere = "iddemande='" + identifiant + "'";
            List<CheckDossierStandard> dossierStandardsCheckes = new CheckDossierStandard().select(c, apresWhere, null);
            List<CheckDossierSupplementaire> dossierSupplementairesCheckes = new CheckDossierSupplementaire().select(c,
                    apresWhere, null);
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
        } catch (Exception e) {
            // TODO: handle exception
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
            System.out.println(dto.getPassport().getId());
            Demande demande = new Demande();
            demande.setId(dto.getIddemande());
            demande.update(c, dto.getIddemande(), dto.getDemandeur(), dto.getPassport(), dto.getVisatransformable(),
                    dto.getDossiersStandard(), dto.getDossiersSup(), dto.getDossiersStandardConcatIdChecks(),
                    dto.getDossiersSupplementairesConcatIdChecks(), dto.getIdTypeDemande(),
                    dto.getIdTypeVisa(),dto.getIdTypeVisaPrecedent(), dto.getDate());

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
}
