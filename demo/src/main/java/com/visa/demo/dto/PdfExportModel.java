package com.visa.demo.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Locale;

import com.visa.demo.models.Demande;
import com.visa.demo.models.Demandeur;
import com.visa.demo.models.TypeVisa;

public class PdfExportModel {
    
    // Template simple
    // bon affichage
    public static String scanTermined = """
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
    <title>Bybrand | Carte de visite professionnelle</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Inter', system-ui, -apple-system, 'Segoe UI', Roboto, Helvetica, Arial, sans-serif;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 2rem;
            border: 1px solid rgba(128, 128, 128, 0.492);
        }

        /* Carte principale – style épuré, moderne, inspiré du template */
       
        /* Section principale : identité + coordonnées */
        .card-content {
            padding: 2rem 2rem 1.8rem 2rem;
        }

        /* Nom et titre */
        .name {
            font-size: 1.9rem;
            font-weight: 700;
            letter-spacing: -0.02em;
            color: #111827;
            line-height: 1.2;
            margin-bottom: 0.35rem;
        }

        .title {
            font-size: 1rem;
            font-weight: 500;
            color: #4b5563;
            border-left: 3px solid #2c7da0;
            padding-left: 0.75rem;
            margin-bottom: 1.75rem;
            margin-top: 0.25rem;
        }

        /* Ligne de séparation élégante */
        .divider-light {
            height: 1px;
            background: #e5e7eb;
            margin: 1.2rem 0 1.2rem 0;
        }

        
        /* Zone avertissement confidentiel – exactement le texte original */
        .confidential-notice {
            background-color: #faf9fe;
            border-top: 1px solid #edf2f7;
            padding: 1.2rem 2rem 1.2rem 2rem;
            font-size: 0.7rem;
            line-height: 1.4;
            color: #6b7280;
            font-family: 'Inter', ui-sans-serif, system-ui;
            letter-spacing: 0.01em;
        }

        .confidential-text {
            max-width: 100%;
        }


        .intitule-section{
            display: flex;
        }
        .pdp-section{
            width: 100px;
            height: 100px;
            border-radius: 50%;
        }
        .info-section{
            width: 65%;
            padding: 10px;
            
        }
        .qrcode-section{
            width: 100px;
            height: 100px;
        }
        .title-pdf{
            text-align: center;
            margin-bottom: 40px;
            background-color: rgba(12, 230, 8, 0.547);
            color: white;
            padding: 20px 0px;
        }
        .footer{
            font-size: 0.7rem;
            margin: 5px 40px;
            width: 100%;
        }
        .signature{
            padding-right: 90px; 
               
        }
        .signature-image{
            width: 100px;
            height: 100px;
            margin-left: 10px;
        }
    </style>
</head>
<body>
    <div class="business-card">
        <div class="card-content">
            <div class="intitule-section">

                <div class="pdp-section">
                    <img class="pdp-section" src="{imagePdp}" alt="Photo du titulaire">
                </div>
                <!-- Identité conforme au texte original : Amanda Carter, Marketing Analyst at Bybrand -->
                <div class="info-section">
                    <div class="name">{nom-prenom}</div>
                    <div class="title">Demande du visa {type-visa}</div>
                </div>

                    <img class="qrcode-section" src="{imageQr}" alt="QR code">
            </div>

          
            <div class="divider-light"></div>
           
        </div>

        <h1 class="title-pdf">Confirmation de dossier complete</h1>
        <!-- Zone confidentialité : reprend exactement le texte fourni -->
        <div class="confidential-notice">
            <div class="confidential-text">
                Felicitation , vous avez completer votre dossier , veiller scannez le QR code pour suivre l'evolution de votre demande 
                <br>
                Si vous avez des question , n'esitez pas a nous contacter 
            </div>
        </div>

        <!-- petite signature discrète Bybrand, comme mentionnée dans le footer du fichier source -->
        <div class="footer">
            <div class="fait-le" style="display: inline-block;">
                Fait le, {date-export}
            </div>
            <div class="signature" style="float: right;">
                <p>Signature du demandeur</p>
                <img class="signature-image" src="{imageSignature}" alt="QR code">
            </div>
        </div>
    </div>
</body>
</html>
    """;
    
    public static String getHtml(Demande demande, Demandeur demandeur, TypeVisa typeVisa) {
        
        String qrBase64 = Base64.getEncoder().encodeToString(demande.getQrcode());
        String pdpBase64 = Base64.getEncoder().encodeToString(demandeur.getPdp());
        String signatureBase64 = Base64.getEncoder().encodeToString(demandeur.getSignatures());  
        
        return scanTermined
            .replace("{imageQr}", "data:image/png;base64," + qrBase64)
            .replace("{imagePdp}", "data:image/png;base64," + pdpBase64)
            .replace("{imageSignature}", "data:image/png;base64," + signatureBase64)
            .replace("{nom-prenom}", demandeur.getNom().toUpperCase()+" "+demandeur.getPrenom())
            .replace("{type-visa}", typeVisa.getLibelle())
            .replace("{date-export}", getDateAujourdhui())
            ;
    }

     public static String getDateAujourdhui() {
        LocalDate aujourdhui = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.FRENCH);
        return aujourdhui.format(formatter);
    }
}