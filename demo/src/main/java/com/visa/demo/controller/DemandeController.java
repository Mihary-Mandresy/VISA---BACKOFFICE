package com.visa.demo.controller;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nojpa.bd.connexion.DbConnexe;
import com.visa.demo.Utils.Caster;
import com.visa.demo.models.Demande;
import com.visa.demo.models.Demandeur;
import com.visa.demo.models.DossierStandard;
import com.visa.demo.models.DossierSupplementaire;
import com.visa.demo.models.Nationalite;
import com.visa.demo.models.Passport;
import com.visa.demo.models.SituationDeFamille;
import com.visa.demo.models.TypeDemande;
import com.visa.demo.models.TypeVisa;
import com.visa.demo.models.Visatransformable;

@Controller
@RequestMapping("/demande")
public class DemandeController {

    @GetMapping
    private ModelAndView form() throws Exception {
        ModelAndView modelAndView = new ModelAndView("pages/demande/form");

        try {
            DbConnexe dbConnexe = new DbConnexe();
            Connection c = dbConnexe.getConnection();

            modelAndView.addObject("nationalites", new Caster<Nationalite>().casteListe(new Nationalite().findAll(c)));
            modelAndView.addObject("situationdefamilles", new Caster<SituationDeFamille>().casteListe(new SituationDeFamille().findAll(c)));
            modelAndView.addObject("typedemandes", new Caster<TypeDemande>().casteListe(new TypeDemande().findAll(c)));
            modelAndView.addObject("typevisas", new Caster<TypeVisa>().casteListe(new TypeVisa().findAll(c)));
            modelAndView.addObject("dossierstandards", new Caster<DossierStandard>().casteListe(new DossierStandard().findAll(c)));
            modelAndView.addObject("dossiersupplementaires", new Caster<DossierSupplementaire>().casteListe(new DossierSupplementaire().findAll(c)));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PostMapping
    private String save(RedirectAttributes redirectAttributes, Demandeur demandeur, Passport passport,
            Visatransformable visatransformable, List<String> dossiersStandard, List<String> dossiersSup,
            String idTypeDemande, String idTypeVisa,
            LocalDate date) throws Exception {
        DbConnexe dbConnexe = new DbConnexe();
        Connection c = dbConnexe.getConnection();
        try {
            Demande demande = new Demande();
            demande.save(c, demandeur, passport, visatransformable, dossiersStandard, dossiersSup, idTypeDemande,
                    idTypeVisa, date);

            redirectAttributes.addFlashAttribute("message", "Demande Crée avec succes !");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return "redirect:/";
    }

}
