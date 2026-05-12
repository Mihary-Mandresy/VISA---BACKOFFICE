<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Detail de la demande</title>

        <script src="${pageContext.request.contextPath}/assets/js/jquery-4.0.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/form.js"></script>
        <%@ include file="../../includes/css.jsp" %>
    </head>
    <body>
        <%@ include file="../../includes/header.jsp" %>
        <main>
        <c:choose>
            <c:when test="${demande == null}">
                <c:url var="formAction" value="/demande" />
            </c:when>
            <c:otherwise>
                <c:url var="formAction" value="/demande/update" />
            </c:otherwise>
        </c:choose>
        <div class="title-page"><h2>Detail de la demande</h2></div>
        <form class="form-wrap" action="${formAction}" method="post">
            <c:if test="${demande != null}">
                <input type="hidden" name="iddemande" value="${demande.id}">
                <input type="hidden" name="demandeur.id" value="${demande.iddemandeur}">
                <c:out value="${demande.idvisatransformable}"/>
                <input type="hidden" name="visatransformable.id" value="${demande.idvisatransformable}">
                <input type="hidden" name="passport.id" value="${demande.idpassport}">
                <input type="hidden" name="idTypeVisaPrecedent" value="${demande.idtypevisa}">
                <c:forEach items="${dossierstandardscheckes}"  var="dstc">
                    <input type="hidden" name="dossiersStandardConcatIdChecks" value="${dstc.id}-${dstc.iddossierstandard}">
                </c:forEach>
                <c:forEach items="${dossiersupplementairescheckes}"  var="dspc">
                    <input type="hidden" name="dossiersSupplementairesConcatIdChecks" value="${dspc.id}-${dspc.iddossiersupplementaire}">
                </c:forEach>
            </c:if>
            <div class="form-header">
                <h2>${demande != null ? "Détail de la demande de transformation de visa" : "Création demande de transformation de visa"}</h2>
                <p>Transformation d'un visa transformable</p>
            </div>
            <div class="form-body">
                <div class="groupe-infos">
                    <!-- etooo-->
                    <div class="info-card-item">
                    <div class="section-title">Demandeur</div>

                    <div class="field-group">

                        <!-- Photo profil -->
                        <div>
                            <label class="field-label">Photo de profil</label>

                            <div class="field-value">
                                <c:choose>
                                    <c:when test="${not empty pdpBase64}">
                                        <img
                                            src="data:image/png;base64,${pdpBase64}"
                                            alt="Photo de profil"
                                            class="photo-profil"
                                        />
                                    </c:when>

                                    <c:otherwise>
                                        Photo de profil pas encore prise
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>

                        <!-- Signature -->
                        <div>
                            <label class="field-label">Signature</label>

                            <div class="field-value">
                                <c:choose>
                                    <c:when test="${not empty signatureBase64}">
                                        <img
                                            src="data:image/png;base64,${signatureBase64}"
                                            alt="Signature"
                                            class="signature-img"
                                            style="background: white; border: 1px solid #ccc;" 
                                        />
                                    </c:when>

                                    <c:otherwise>
                                        Signature pas encore prise
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>

                    </div>
                </div>
                    <!-- katreto-->
                    <div class="info-card-item">
                        <div class="section-title">Type VISA demandé</div>
                        <div class="field-group">
                            <div>
                                <label class="field-label">Type de visa</label>
                                <div class="field-value">
                                    <c:forEach items="${typevisas}" var="type">
                                        <c:if test="${demande.idtypevisa == type.id}">
                                            ${type.libelle}
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="groupe-infos">
                    <div class="info-card-item">
                        <div class="section-title">État civil</div>
                        <div class="field-group col2">
                            <div>
                                <label class="field-label">Nom</label>
                                <div class="field-value">${demande != null ? demande.nomdemandeur : ''}</div>
                            </div>
                            <div>
                                <label class="field-label">Prénoms</label>
                                <div class="field-value">${demande != null ? demande.prenomdemandeur : ''}</div>
                            </div>
                        </div>
                        <div class="field-group col2">
                            <div>
                                <label class="field-label">Date de naissance</label>
                                <div class="field-value">${demande != null ? demande.dtndemandeur : ''}</div>
                            </div>
                            <div>
                                <label class="field-label">Profession</label>
                                <div class="field-value">${demande != null ? demande.profession : ''}</div>
                            </div>
                        </div>
                        <div class="field-group col2">
                            <div>
                                <label class="field-label">Situation familiale</label>
                                <div class="field-value">
                                    <c:forEach items="${situationdefamilles}" var="sf">
                                        <c:if test="${demande.idsituationdefamille == sf.id}">
                                            ${sf.libelle}
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                            <div>
                                <label class="field-label">Nationalité</label>
                                <div class="field-value">
                                    <c:forEach items="${nationalites}" var="nat">
                                        <c:if test="${demande.idnationalite == nat.id}">
                                            ${nat.libelle}
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="field-group">
                            <div>
                                <label class="field-label">Adresse à Madagascar</label>
                                <div class="field-value">${demande != null ? demande.adressemada : ''}</div>
                            </div>
                        </div>
                        <div class="field-group col2">
                            <div>
                                <label class="field-label">Email</label>
                                <div class="field-value">${demande != null ? demande.email : ''}</div>
                            </div>
                            <div>
                                <label class="field-label">Téléphone</label>
                                <div class="field-value">${demande != null ? demande.tel : ''}</div>
                            </div>
                        </div>

                        <!-- Partie Visa et Carte Resident (si existants) -->
                        <c:if test="${not empty visaExistant}">
                            <div class="section-title">Visa actuel</div>
                            <div class="field-group col6">
                                <div>
                                    <label class="field-label">Référence visa</label>
                                    <div class="field-value">${visaExistant.reference}</div>
                                </div>
                            </div>
                            <div class="field-group col2">
                                <div>
                                    <label class="field-label">Date début</label>
                                    <div class="field-value">${visaExistant.datedebut}</div>
                                </div>
                                <div>
                                    <label class="field-label">Date d'expiration</label>
                                    <div class="field-value">${visaExistant.dateexpiration}</div>
                                </div>
                            </div>

                            <div class="section-title">Carte résident</div>
                            <div class="field-group col6">
                                <div>
                                    <label class="field-label">Référence de la carte</label>
                                    <div class="field-value">${carteExistant.reference}</div>
                                </div>
                            </div>
                            <div class="field-group col2">
                                <div>
                                    <label class="field-label">Date début</label>
                                    <div class="field-value">${carteExistant.datedebut}</div>
                                </div>
                                <div>
                                    <label class="field-label">Date d'expiration</label>
                                    <div class="field-value">${carteExistant.dateexpiration}</div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                    
                    <div class="info-card-item">
                        <div class="section-title title-passport">Passeport</div>
                        <div class="field-group">
                            <div>
                                <label class="field-label">Numéro de passeport</label>
                                <div class="field-value">${demande != null ? demande.numeropassport : ''}</div>
                            </div>
                        </div>
                        <div class="field-group col2">
                            <div>
                                <label class="field-label">Date de délivrance</label>
                                <div class="field-value">${demande != null ? demande.datedelivrancepassport : ''}</div>
                            </div>
                            <div>
                                <label class="field-label">Date d'expiration</label>
                                <div class="field-value">${demande != null ? demande.dateexpirationpassport : ''}</div>
                            </div>
                        </div>

                        <!-- Nouveau passeport (si existant) -->
                        <c:if test="${not empty nouveauPasseport}">
                            <div class="section-title title-passport">Nouveau passeport</div>
                            <div class="field-group">
                                <div>
                                    <label class="field-label">Numéro de passeport</label>
                                    <div class="field-value">${nouveauPasseport.numero}</div>
                                </div>
                            </div>
                            <div class="field-group col2">
                                <div>
                                    <label class="field-label">Date de délivrance</label>
                                    <div class="field-value">${nouveauPasseport.datedelivrance}</div>
                                </div>
                                <div>
                                    <label class="field-label">Date d'expiration</label>
                                    <div class="field-value">${nouveauPasseport.dateexpiration}</div>
                                </div>
                            </div>
                        </c:if>

                        <div class="section-title">Visa transformable</div>
                        <div class="field-group col2">
                            <div>
                                <label class="field-label">Référence visa</label>
                                <div class="field-value">${demande != null ? demande.referencevt : ''}</div>
                            </div>
                            <div>
                                <label class="field-label">Lieu d'entrée Madagascar</label>
                                <div class="field-value">${demande != null ? demande.lieuentree : ''}</div>
                            </div>
                        </div>
                        <div class="field-group col2">
                            <div>
                                <label class="field-label">Date d'entrée à Madagascar</label>
                                <div class="field-value">${demande != null ? demande.dateentreemada : ''}</div>
                            </div>
                            <div>
                                <label class="field-label">Date d'expiration de visa</label>
                                <div class="field-value">${demande != null ? demande.dateexpirationvt : ''}</div>
                            </div>
                        </div>

                        <div class="section-title">Type de demande</div>
                        <div class="radio-group">
                            <div class="radio-group">
                                <div class="field-value">
                                    <c:forEach items="${typedemandes}" var="td">
                                        <c:if test="${td.id == demande.idtypedemande}">
                                            ${td.libelle}
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    
                </div>
                
               
                </form>
                
                <c:if test="${not empty message}">
                    <div class="alert alert-success">
                        ${message}
                        <span class="close-btn" onclick="this.parentElement.style.display='none'">&times;</span>
                    </div>
                </c:if>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger">
                        ${error}
                        <span class="close-btn" onclick="this.parentElement.style.display='none'">&times;</span>
                    </div>
                </c:if>
                </main>
                
                <style>
                    /* Styles pour l'affichage des valeurs en mode consultation */
                    .field-value {
                       /* background-color: #1b1b1b;*/
                        padding: 8px 12px;
                        border-radius: 4px;
                        border-bottom: 1px solid #e0e0e0;
                        min-height: 38px;
                        width: 100%;
                        font-size: 14px;
                        color: #e0e0e0;
                    }
                    
                    .checkbox-group .document-item {
                        padding: 8px 12px;
                        margin: 5px 0;
                        background-color: #f9f9f9;
                        border-radius: 4px;
                        border-left: 3px solid #ccc;
                    }
                    
                    .checkbox-group .document-item.checked {
                        background-color: #e8f5e9;
                        border-left-color: #4caf50;
                        color: #2e7d32;
                    }
                    
                    .info-card-item .field-group {
                        margin-bottom: 15px;
                    }
                    
                    .submit-btn {
                        margin-top: 20px;
                    }

                    .photo-profil,
                    .signature-img {
                        width: 150px;
                        border: 1px solid #ccc;
                        border-radius: 8px;
                        object-fit: cover;
                    }
                </style>
                
                <script>
                    $(document).ready(function(){
                        // Fonction pour filtrer les dossiers selon le type de visa (affichage seul)
                        function filterFolderByType(type){
                            const container = $('#specific-folder');
                            const children = container.children(".document-item");
                            if (children.length == 0) return;
                            
                            children.each(function(){
                                if($(this).hasClass('folder-'+type)){
                                    $(this).show();
                                } else {
                                    $(this).hide();
                                }
                            });
                        }
                        
                      
                    });
                </script>
            </body>
        </html>