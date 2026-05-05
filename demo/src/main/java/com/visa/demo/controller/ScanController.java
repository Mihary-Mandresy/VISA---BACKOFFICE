package com.visa.demo.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
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
import com.visa.demo.dto.ApproveDto;
import com.visa.demo.models.CheckDossierStandard;
import com.visa.demo.models.CheckDossierSupplementaire;
import com.visa.demo.models.Demande;
import com.visa.demo.models.DossierStandard;
import com.visa.demo.models.DossierSupplementaire;
import com.visa.demo.models.FilePdf;

@Controller
@RequestMapping("/demande/scan")
public class ScanController {

    @GetMapping("/{id}")
    public String handleScan(@PathVariable("id") String id, Model model) throws Exception {
        Connection c = new DbConnexe().getConnection();
        model.addAttribute("dstds", new DossierStandard().findAll(c));

        Demande dmd = new Demande().findByid(c, id);
        model.addAttribute("dsups", new DossierSupplementaire().getAllByIdTypeVisa(c, dmd.getIdtypevisa()));
        model.addAttribute("id", id);
        return "pages/demande/scan/scan";
    }

    @GetMapping("/approve/{id}")
    public String formApprove(@PathVariable("id") String id, Model model) throws Exception {
        model.addAttribute("id", id);
        return "pages/demande/scan/approve";
    }

    @PostMapping("/approver")
    public String approveDemande(ApproveDto approveDto, Model model) throws Exception {
        System.out.println("akory nenty eeeh ");
        Connection c = new DbConnexe().getConnection();
        try { 

            Demande d = new Demande().findByidWithThrows(c, approveDto.getIddemande());
            d.approve(c, approveDto.getDatedebut(), approveDto.getDatefin());

            model.addAttribute("message", "Approbation avec succes !");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        // return "redirect:/demande/scan/approve/"+approveDto.getIddemande();
        // model.addAttribute("id", id);
        return "pages/demande/scan/approve"; 
    }

    @PostMapping("/{id}")
    public String saveScan(@PathVariable("id") String id, @RequestParam Map<String, MultipartFile> files,
            RedirectAttributes redirectAttributes) throws Exception {
        Connection c = new DbConnexe().getConnection();
        c.setAutoCommit(false);
        try {
 
            files.forEach((name, file) -> {
                System.out.println("Champ : " + name);
                System.out.println("Nom fichier : " + file.getOriginalFilename());

                try {
                    byte[] data = file.getBytes();

                    FilePdf filePdf = new FilePdf();
                    filePdf.setNom(id + "_" + "_" + System.currentTimeMillis() + "_" + name);
                    filePdf.setContenue(data);

                    if (data.length > 0) {
                        filePdf.insert(c);

                        if (name.startsWith("DST")) {
                            CheckDossierStandard checkDossierStandard = new CheckDossierStandard();
                            checkDossierStandard.setExist(true);
                            checkDossierStandard.setIdfilepdf(filePdf.getId());
                            checkDossierStandard.setIddemande(id);
                            checkDossierStandard.setIddossierstandard(name);

                            checkDossierStandard.insert(c);
                        } else if (name.startsWith("DSU")) {
                            CheckDossierSupplementaire checkDossierSupplementaire = new CheckDossierSupplementaire();
                            checkDossierSupplementaire.setExist(true);
                            checkDossierSupplementaire.setIdfilepdf(filePdf.getId());
                            checkDossierSupplementaire.setIddemande(id);
                            checkDossierSupplementaire.setIddossiersupplementaire(name);

                            checkDossierSupplementaire.insert(c);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            Demande d = new Demande().findByid(c, id);
            d.scanVisa(c);
            c.commit();
            redirectAttributes.addFlashAttribute("message", "Dossier envoyer avec succes"); 
        } catch (Exception e) {
            c.rollback();
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } finally {
            if (c != null) {
                c.close();
            }
        }

        return "redirect:/demande/scan/" + id;
    }
}
