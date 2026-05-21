package com.visa.demo.controller;

import com.itextpdf.html2pdf.HtmlConverter;
import com.nojpa.bd.connexion.DbConnexe;
import com.visa.demo.dto.PdfExportModel;
import com.visa.demo.models.Demande;
import com.visa.demo.models.Demandeur;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;

@RestController
@RequestMapping("/demande/export")
public class PdfDemandeController {
    @GetMapping("/scan-termine/{id}")
    public ResponseEntity<byte[]> generatePdfFromHtml(@PathVariable("id") String id) {
        try {

            DbConnexe dbConnexe = new DbConnexe();
            Connection c = dbConnexe.getConnection();
            Demande demande = new Demande().findByid(c, id);
            Demandeur demandeur = new Demandeur().findByid(c, demande.getIddemandeur());
            // Buffer mémoire
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            String html = PdfExportModel.getHtml(demande, demandeur);

            // Conversion HTML -> PDF
            HtmlConverter.convertToPdf(html, baos);

            byte[] pdfBytes = baos.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "html-document.pdf");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(pdfBytes);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
