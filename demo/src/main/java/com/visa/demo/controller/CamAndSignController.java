package com.visa.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CamAndSignController {
    @GetMapping("/demande/cam")
    public String camAndSign() throws Exception {
        
        return "pages/demande/cam/cam";
    }
}
