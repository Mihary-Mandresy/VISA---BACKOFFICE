<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Demande de transformation de visa</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/root.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/form-visa.css">
        <script src="${pageContext.request.contextPath}/assets/js/jquery-4.0.0.min.js"></script>
    </head>
    <body>
        <form class="form-wrap" action="${pageContext.request.contextPath}/demande" method="post">
            <div class="form-header">
                <h2>Demande de transformation de visa</h2>
                <p>Transformation d'un visa transformable</p>
            </div>
            <div class="form-body">
                <div>
                    <div class="section-title">Type VISA demandé</div>
                    <div class="field-group">
                        <div>
                            <label class="field-label">type de visa</label>
                            <select name="idTypeVisa" class="types-visas">
                                <c:forEach items="${typevisas}" var="type">
                                    <option value="${type.id}">${type.libelle}</option>
                                </c:forEach>
                            </select >
                        </div>
                    </div>
                </div>
                <div class="groupe-infos">
                    <div class="info-card-item">
                        <div class="section-title">État civil</div>
                        <div class="field-group col2">
                            <div>
                                <label class="field-label">Nom</label>
                                <input type="text" placeholder="Nom de famille" name="demandeur.nom">
                            </div>
                            <div>
                                <label class="field-label">Prénoms</label>
                                <input type="text" placeholder="Prénoms" name="demandeur.prenom">
                            </div>
                        </div>
                        <div class="field-group col2">

                            <div>
                                <label class="field-label">Situation familiale</label>
                                <select name="demandeur.idsituationdefamille">
                                    <option value="">— Sélectionner —</option>
                                    <c:forEach items="${situationdefamilles}" var="sf">
                                        <option value="${sf.id}">${sf.libelle}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div>
                                <label class="field-label">Nationalite</label>
                                <select name="demandeur.idnationalite">
                                    <option value="">— Sélectionner —</option>
                                    <c:forEach items="${nationalites}" var="nat">
                                        <option value="${nat.id}">${nat.libelle}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="field-group col2">

                            <div>
                                <label class="field-label">Profession</label>
                                <input type="text" placeholder="Intitulé du poste" name="demandeur.profession">
                            </div>
                            <div>
                                <label class="field-label">Adresse à Madagascar</label>
                                <input type="text" placeholder="Adresse complète" name="demandeur.adressemada">
                            </div>
                        </div>
                        <div class="field-group col2">
                            <div><label class="field-label">Email</label>
                                <input type="email" placeholder="exemple@email.com" name="demandeur.email"></div>
                                <div><label class="field-label">Téléphone</label>
                                    <input type="tel" placeholder="+261 ..." name="demandeur.tel"></div>
                                </div>
                            </div>
                            <div class="info-card-item">
                                <div class="section-title">Passeport</div>
                                <div class="field-group">
                                    <div>
                                        <label class="field-label">Numéro de passeport</label>
                                        <input type="text" name="passport.numero" placeholder="N° passeport">
                                    </div>
                                </div>
                                <div class="field-group col2">
                                    <div>
                                        <label class="field-label">Date de délivrance</label>
                                        <input type="date" name="passport.datedelivrance">
                                    </div>
                                    <div>
                                        <label class="field-label">Date d'expiration</label>
                                        <input type="date" name="passport.dateexpiration">
                                    </div>
                                </div>

                                <div class="section-title">Visa transformable</div>
                                <div class="field-group col2">
                                    <div>
                                        <label class="field-label">Référence visa</label>
                                        <input type="text" name="visatransformable.reference" placeholder="Réf. du visa">
                                    </div>
                                    <div>
                                        <label class="field-label">Lieu d'entrée Madagascar</label>
                                        <input type="text" name="visatransformable.lieuentree" placeholder="ex. aeroport de Nosy Be">
                                    </div>
                                </div>
                                <div class="field-group col2">
                                    <div>
                                        <label class="field-label">Date d'entrée à Madagascar</label>
                                        <input type="date" name="visatransformable.dateentreemada">
                                    </div>
                                    <div>
                                        <label class="field-label">Date d'expiration de visa</label>
                                        <input type="date" name="visatransformable.dateexpiration">
                                    </div>
                                </div>

                                <div class="section-title">Type de demande</div>
                                <div class="radio-group">
                                    <div class="radio-group">
                                        <c:forEach items="${typedemandes}" var="td" varStatus="status">
                                            <label>
                                                <input type="radio" name="idTypeDemande" value="${td.id}" ${status.first ? 'checked' : ''}>
                                                ${td.libelle}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                            <div class="folder">
                                <div class="section-title">Dossiers à fournir</div>
                                <div class="info-note">Cochez les documents que vous avez réunis pour votre dossier.</div>
                                <div class="folder-list">
                                    <div class="subfolder-list">
                                        <div class="subfolder-title">Dossiers communs</div>
                                        <div id="standard-folder" class="checkbox-group">
                                            <label>
                                                <input type="checkbox" id="all-standards"> tout cocher
                                            </label>
                                            <c:forEach items="${dossierstandards}" var="ds">
                                                <label>
                                                    <input type="checkbox" name="dossiersStandard" value="${ds.id}"> ${ds.libelle}
                                                </label>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div class="subfolder-list">
                                        <div class="subfolder-title">Dossiers complementaires</div>
                                        <div id="specific-folder" class="checkbox-group">
                                            <c:forEach items="${dossiersupplementaires}" var="dsup">
                                                <label class="folder-${dsup.idtypevisa}">
                                                    <input type="checkbox" name="dossiersSup" value="${dsup.id}"> ${dsup.libelle}
                                                </label>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <button class="submit-btn" type="submit">Soumettre la demande</button>
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

                    <script>
                        $(document).ready(function(){
                            $('.types-visas').on('change', function() {
                                // 1. Récupérer la valeur (l'attribut 'value' de l'option choisie)
                                const selectedValue = $(this).val();
                                
                                
                                // 3. Récupérer le texte affiché
                                const selectedText = $(this).find('option:selected').text().trim();
                                
                                console.log("Valeur:", selectedValue);
                                console.log("Libellé:", selectedText);
                                
                                // Si tu veux appeler ta fonction renderFolder :
                                renderFolder(selectedValue);
                            });
                            // fonction qui sert a rendre les dossiers specifique selon le type de visa demande
                            function renderFolder(type){
                                const container = $('#specific-folder');
                                const children = container.children("label");
                                if (container.children().length == 0) {
                                    container.html("<p>Erreur: aucun dossier supplementaire charge</p>");
                                    return;
                                }
                                
                                children.each((function(){
                                    if($(this).hasClass('folder-'+type)){
                                        $(this).show();
                                    }
                                    else{
                                        $(this).hide(0);
                                    }
                                }));
                            }
                            $('#all-standards').on('click', function() {
                                const parent = $(this).closest(".checkbox-group");
                                const checkboxes = parent.find("input[type='checkbox']").not(this);
                                checkboxes.prop("checked", $(this).prop("checked"));
                            });
                            // INITIALISATION au chargement de la page
                            const initialType = $('.types-visas').val(); // jQuery récupère la valeur de l'option active
                            
                            if (initialType) {
                                console.log("Initialisation avec le type :", initialType);
                                renderFolder(initialType);
                            }
                        })
                    </script>
                </body>
            </html>