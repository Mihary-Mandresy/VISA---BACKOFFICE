package com.visa.demo.controller;

import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nojpa.bd.connexion.DbConnexe;
import com.visa.demo.models.CheckDossierStandard;
import com.visa.demo.models.CheckDossierSupplementaire;
import com.visa.demo.models.Demande;
import com.visa.demo.models.Demandeur;
import com.visa.demo.models.FilePdf;
import com.visa.demo.utils.konst.C_EtatDemande;

@Controller
@RequestMapping("/demande/cam")
public class CamAndSignController {
    @GetMapping("/{id}")
    public String camAndSign(@PathVariable("id") String id, Model model) throws Exception {
        model.addAttribute("id", id);
        return "pages/demande/cam/cam";
    }

    @PostMapping("/{id}")
    public String savePhotoAndSign(@PathVariable("id") String id,@RequestParam Map<String, MultipartFile> files, RedirectAttributes redirectAttributes) throws Exception {
        Connection c = new DbConnexe().getConnection();
        c.setAutoCommit(false);
        try {
            Demande demande = new Demande().findByid(c, id);
            Demandeur demandeur = new Demandeur().findByid(c, demande.getIddemandeur());
            files.forEach((name, file) -> {
                System.out.println("Champ : " + name);
                System.out.println("Nom fichier : " + file.getOriginalFilename());

                try {
                    byte[] data = file.getBytes();
                        if (name.equals("photo")) {
                            demandeur.setPdp(data);
                        } else if (name.equals("signature")) {
                            demandeur.setSignatures(data);
                        }
            } catch (Exception e) {
                    e.printStackTrace();

                }
            });

             demandeur.save(c);
                    demande.changeEtatDemande(C_EtatDemande.REQUEST_PHOTO_SING_FINISH);
                    demande.save(c);
            c.commit();
            redirectAttributes.addFlashAttribute("message", "Photo et signature sauvegarder avec succes"); 
        } catch (Exception e) {
            c.rollback();
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } finally {
            if (c != null) {
                c.close();
            }
        }

        return "redirect:/demande/cam/" + id;
    
    }
  
} 
