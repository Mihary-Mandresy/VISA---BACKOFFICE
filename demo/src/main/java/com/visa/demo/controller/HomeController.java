package com.visa.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nojpa.bd.connexion.DbConnexe;
import com.visa.demo.models.Personne;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "hello";
    }

    @PostMapping("/")
    public String save(RedirectAttributes redirectAttributes, Personne personne) throws Exception {
        try {
            DbConnexe dbConnexe = new DbConnexe();
            personne.insert(dbConnexe.getConnection());
            System.out.println(personne);

            redirectAttributes.addFlashAttribute("message", "Mety " + personne);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/";
    }
}
