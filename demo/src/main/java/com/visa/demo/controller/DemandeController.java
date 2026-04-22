package com.visa.demo.controller;

import java.sql.Connection;
import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nojpa.bd.connexion.DbConnexe;
import com.visa.demo.dto.DemandeDto;
import com.visa.demo.models.Demande;
import com.visa.demo.models.DossierStandard;
import com.visa.demo.models.DossierSupplementaire;
import com.visa.demo.models.Nationalite;
import com.visa.demo.models.SituationDeFamille;
import com.visa.demo.models.TypeDemande;
import com.visa.demo.models.TypeVisa;

@Controller
@RequestMapping("/demande")
public class DemandeController {

    @GetMapping
    private String getAll(Model model) throws Exception {
        Connection c = new DbConnexe().getConnection();
        model.addAttribute(new Demande().findAll(c));
        c.close();
        return "pages/list";
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
    private String save(RedirectAttributes redirectAttributes, @ModelAttribute("formulaire")DemandeDto dto) throws Exception {
        DbConnexe dbConnexe = new DbConnexe();
        Connection c = dbConnexe.getConnection();
        try {
            Demande demande = new Demande();
            if(dto.getVisatransformable().getDateentreemada() instanceof LocalDate){
                
            }
            demande.save(c, dto.getDemandeur(), dto.getPassport(), dto.getVisatransformable(), dto.getDossiersStandard(), dto.getDossiersSup(), dto.getIdTypeDemande(),
                    dto.getIdTypeVisa(), dto.getDate());

            redirectAttributes.addFlashAttribute("message", "Demande Crée avec succes !");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return "redirect:/demande";
    }

}
