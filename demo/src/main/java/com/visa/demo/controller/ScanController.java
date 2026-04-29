package com.visa.demo.controller;

import java.sql.Connection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nojpa.bd.connexion.DbConnexe;
import com.visa.demo.models.DossierStandard;
import com.visa.demo.models.DossierSupplementaire;


@Controller
@RequestMapping("/demande/scan")
public class ScanController {
    
    @GetMapping("/{id}")
    public String handleScan(@PathVariable("id") String id, Model model) throws Exception {
        Connection c = new DbConnexe().getConnection();

        model.addAttribute("dstds", new DossierStandard().findAll(c));
        model.addAttribute("dsups", new DossierSupplementaire().getAllByIdTypeVisa(c, id));

        return "pages/demande/scan/scan";
    }
}
