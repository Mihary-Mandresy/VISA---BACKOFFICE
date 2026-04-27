package com.visa.demo.controller.api;

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
import org.springframework.web.bind.annotation.RestController;
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

   
}
