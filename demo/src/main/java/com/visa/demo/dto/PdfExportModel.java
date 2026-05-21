package com.visa.demo.dto;

import java.util.Base64;

import com.visa.demo.models.Demande;
import com.visa.demo.models.Demandeur;

public class PdfExportModel {
    
    // Template simple
    // bon affichage
    public static String scanTermined = """
     
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <title>Confirmation de Visa</title>
        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            
            body {
                font-family: 'Inter', 'Segoe UI', 'Roboto', Arial, sans-serif;
                background: #eef2f5;
                padding: 40px 20px;
                margin: 0;
            }
            
            /* Carte principale - style Bybrand */
            .visa-card {
                max-width: 720px;
                width: 100%;
                margin: 0 auto;
                background: #ffffff;
                border-radius: 32px;
                overflow: hidden;
                box-shadow: 0 25px 45px -12px rgba(0, 0, 0, 0.2), 0 4px 12px rgba(0, 0, 0, 0.05);
            }
            
            /* Section contenu principal */
            .card-content {
                padding: 2rem 2rem 1.8rem 2rem;
            }
            
            /* En-tête avec statut */
            .status-header {
                text-align: center;
                margin-bottom: 1.5rem;
                padding-bottom: 1rem;
                border-bottom: 2px solid #e8edf2;
            }
            
            .status-badge {
                display: inline-block;
                background: #2c7da0;
                color: white;
                font-size: 0.7rem;
                font-weight: 600;
                padding: 0.3rem 1rem;
                border-radius: 30px;
                letter-spacing: 1px;
                text-transform: uppercase;
                margin-bottom: 0.75rem;
            }
            
            .status-header h1 {
                font-size: 1.8rem;
                font-weight: 700;
                color: #111827;
                margin-bottom: 0.25rem;
            }
            
            .status-header p {
                color: #6b7280;
                font-size: 0.9rem;
            }
            
            /* Nom et titre - style Bybrand */
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
            
            /* Ligne de séparation */
            .divider-light {
                height: 1px;
                background: #e5e7eb;
                margin: 1.2rem 0 1.2rem 0;
            }
            
            /* Grille des informations visa */
            .info-grid {
                display: table;
                width: 100%;
                margin: 1.5rem 0;
                border-collapse: separate;
                border-spacing: 0 12px;
            }
            
            .info-row {
                display: table-row;
            }
            
            .info-label {
                display: table-cell;
                width: 100px;
                font-size: 0.75rem;
                font-weight: 600;
                color: #6b7280;
                text-transform: uppercase;
                letter-spacing: 0.5px;
                padding: 6px 0;
            }
            
            .info-value {
                display: table-cell;
                font-size: 0.9rem;
                color: #1f2937;
                font-weight: 500;
                padding: 6px 0;
            }
            
            /* Section des documents (QR, photo, signature) */
            .documents-section {
                background: #fafcff;
                border-radius: 20px;
                padding: 1.5rem;
                margin: 1.5rem 0;
                border: 1px solid #eef2f8;
            }
            
            .section-title {
                font-size: 0.85rem;
                font-weight: 600;
                color: #2c7da0;
                text-transform: uppercase;
                letter-spacing: 1px;
                margin-bottom: 1.2rem;
                padding-bottom: 0.5rem;
                border-bottom: 2px solid #e8edf2;
            }
            
            .documents-wrapper {
                display: table;
                width: 100%;
                text-align: center;
            }
            
            .doc-item {
                display: inline-block;
                width: 31%;
                margin: 0 1%;
                vertical-align: top;
                text-align: center;
            }
            
            .doc-label {
                font-size: 0.7rem;
                font-weight: 600;
                color: #6b7280;
                text-transform: uppercase;
                letter-spacing: 0.8px;
                margin-bottom: 0.8rem;
            }
            
            .doc-item img {
                max-width: 100%;
                height: auto;
                border-radius: 12px;
            }
            
            /* Style QR Code */
            .qr-item img {
                max-width: 140px;
                border: 2px solid #e8edf2;
                padding: 8px;
                background: white;
                border-radius: 16px;
            }
            
            /* Style Photo */
            .photo-item img {
                width: 110px;
                height: 110px;
                object-fit: cover;
                border-radius: 50%;
                border: 3px solid #e8edf2;
            }
            
            /* Style Signature */
            .signature-item img {
                max-width: 150px;
                border-bottom: 2px solid #2c7da0;
            }
            
            /* Message de félicitations */
            .congrats-message {
                background: #f0fdf4;
                border-left: 4px solid #22c55e;
                padding: 1rem 1.2rem;
                margin: 1.5rem 0;
                border-radius: 12px;
            }
            
            .congrats-message p {
                font-size: 0.85rem;
                color: #166534;
                margin: 0;
            }
            
            /* Zone confidentialité - exactement comme le template */
            .confidential-notice {
                background-color: #faf9fe;
                border-top: 1px solid #edf2f7;
                padding: 1.2rem 2rem;
                font-size: 0.7rem;
                line-height: 1.5;
                color: #6b7280;
            }
            
            .confidential-text {
                max-width: 100%;
            }
            
            /* Footer Bybrand */
            .brand-footer {
                background: #ffffff;
                text-align: center;
                padding: 0.75rem 1rem;
                font-size: 0.65rem;
                color: #9ca3af;
                border-top: 1px solid #f0f2f5;
                letter-spacing: 0.3px;
            }
            
            /* Signature officielle en bas (cachet) */
            .official-signature {
                display: flex;
                justify-content: space-between;
                align-items: flex-end;
                margin-top: 1.5rem;
                padding-top: 1rem;
                border-top: 1px solid #e5e7eb;
            }
            
            .signature-stamp {
                text-align: left;
            }
            
            .signature-stamp img {
                max-width: 150px;
                height: auto;
                margin-bottom: 0.3rem;
            }
            
            .signature-caption {
                font-size: 0.65rem;
                color: #9ca3af;
            }
            
            .date-stamp {
                text-align: right;
                font-size: 0.7rem;
                color: #6b7280;
            }
            
            .date-stamp strong {
                color: #1f2937;
            }
            
            /* Responsive */
            @media (max-width: 600px) {
                .card-content {
                    padding: 1.5rem;
                }
                
                .name {
                    font-size: 1.5rem;
                }
                
                .doc-item {
                    display: block;
                    width: 90%;
                    margin: 1rem auto;
                }
                
                .confidential-notice {
                    padding: 1rem 1.5rem;
                }
                
                .official-signature {
                    flex-direction: column;
                    align-items: flex-start;
                    gap: 1rem;
                }
                
                .date-stamp {
                    text-align: left;
                }
            }
            
            @media print {
                body {
                    background: white;
                    padding: 0;
                }
                .visa-card {
                    box-shadow: none;
                    border: 1px solid #ddd;
                }
            }
        </style>
    </head>
    <body>
        <div class="visa-card">
            <div class="card-content">
                
                <!-- Statut du visa -->
                <div class="status-header">
                    <div class="doc-item photo-item">
                            <img src="{imagePdp}" alt="Photo du titulaire">
                        </div>
                    <h1>Dossier completer</h1>
                    <p>Document officiel de confirmation</p>
                </div>
                
                <!-- Identité - style Bybrand -->
                <div class="name">{nom-prenom}</div>
                <div class="title">Demande de visa {type-demande}</div>
                
                <div class="divider-light"></div>
                <!-- Message de félicitations -->
                <div class="congrats-message">
                    <p>✨ Félicitations ! Votre demande de visa a été approuvée avec succès. Ce document fait office de justificatif officiel.</p>
                </div>
                
                <!-- Informations visa 
                <table class="info-grid">
                    <tr class="info-row">
                        <td class="info-label">Type</td>
                        <td class="info-value">{type-demande}</td>
                    </tr>
                    <tr class="info-row">
                        <td class="info-label">Validité</td>
                        <td class="info-value">Du 01/01/2025 au 31/12/2025</td>
                    </tr>
                    <tr class="info-row">
                        <td class="info-label">Entrées</td>
                        <td class="info-value">Multiples</td>
                    </tr>
                </table> -->
                
                <!-- Section documents avec QR, Photo et Signature -->
                <div class="documents-section">
                    <div class="section-title">📋 Documents d'identification</div>
                    <div class="documents-wrapper">
                        <div class="doc-item qr-item">
                            <div class="doc-label">🔐 QR CODE</div>
                            <img src="{imageQr}" alt="QR Code de validation">
                        </div>
                        
                        <div class="doc-item signature-item">
                            <div class="doc-label">✍️ SIGNATURE</div>
                            <img src="{imageSignature}" alt="Signature du titulaire">
                        </div>
                    </div>
                </div>
                
                
                
                <!-- Signature officielle (cachet / autorité) -->
                <div class="official-signature">
                    <div class="signature-stamp">
                        <!-- Signature de l'autorité (vous pouvez remplacer par une image si besoin) -->
                        <div style="font-family: 'Brush Script MT', cursive; font-size: 1.3rem; color: #1f2937;">_________________</div>
                        <div class="signature-caption">Signature de l'autorité consulaire</div>
                    </div>
                    <div class="date-stamp">
                        Délivré le : <strong>20 mai 2026</strong><br>
                        Cachet officiel
                    </div>
                </div>
                
            </div>
            
            <!-- Zone confidentialité - exactement comme le template original -->
            <div class="confidential-notice">
                <div class="confidential-text">
                    The content of this email is strictly confidential and only intended for the specified recipient. It is forbidden to share the email or its contents with any third party without the sender's written consent. If this email reached you by mistake, please let us know so that we can ensure that this doesn't happen in the future and delete the message.
                </div>
            </div>
            
            <!-- Footer Bybrand -->
            <div class="brand-footer">
                Bybrand · Service des Visas · Document officiel
            </div>
        </div>
    </body>
    </html>
    """;
    
    public static String getHtml(Demande demande, Demandeur demandeur) {
        
        String qrBase64 = Base64.getEncoder().encodeToString(demande.getQrcode());
        String pdpBase64 = Base64.getEncoder().encodeToString(demandeur.getPdp());
        String signatureBase64 = Base64.getEncoder().encodeToString(demandeur.getSignatures());  
        
        return scanTermined
            .replace("{imageQr}", "data:image/png;base64," + qrBase64)
            .replace("{imagePdp}", "data:image/png;base64," + pdpBase64)
            .replace("{imageSignature}", "data:image/png;base64," + signatureBase64)
            .replace("{nom-prenom}", demandeur.getNom().toUpperCase()+" "+demandeur.getPrenom())
            ;
    }
}