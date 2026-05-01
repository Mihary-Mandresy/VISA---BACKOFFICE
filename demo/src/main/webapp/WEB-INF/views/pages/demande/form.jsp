<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${demande != null ? "Modification demande de Transformation de visa": "Creation demande de transformation de visa"}</title>

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
        <div class="title-page"><h2>Ajouter Un Demande</h2></div>
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
                <h2>${demande != null ? "Modification demande de Transformation de visa": "Creation demande de transformation de visa"}</h2>
                <p>Transformation d'un visa transformable</p>
            </div>
            <div class="form-body">
                <div>
                    <div class="section-title">Demandeur</div>
                    <div class="field-group">
                        <div>
                            <label class="field-label">choisir un demandeur ou sans donner interieur</label>
                            <select name="demandeur.id" class="demandeurs">
                                 <option value="" selected >Sans donner interieur</option>
                                <c:forEach items="${demandeurs}" var="demandeur">
                                    <option value="${demandeur.id}" >${demandeur.nom} - ${demandeur.prenom}</option>
                                </c:forEach>
                            </select >
                        </div>
                    </div>
                </div>
                <div>
                    <div class="section-title">Type VISA demandé</div>
                    <div class="field-group">
                        <div>
                            <label class="field-label">type de visa</label>
                            <select name="idTypeVisa" class="types-visas">
                                <c:forEach items="${typevisas}" var="type">
                                    <option value="${type.id}" ${demande.idtypevisa != null && demande.idtypevisa == type.id ? "selected":''}>${type.libelle}</option>
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
                                <input type="text"
                                placeholder="Nom de famille"
                                name="demandeur.nom"
                                class="nom"
                                value="${demande != null ? demande.nomdemandeur : ''}">
                            </div>
                            <div>
                                <label class="field-label">Prénoms</label>
                                <input type="text"
                                placeholder="Prénoms"
                                name="demandeur.prenom"
                                class="prenom"
                                value="${demande != null ? demande.prenomdemandeur : ''}">
                            </div>
                        </div>
                        <div class="field-group col2">

                            <div>
                                <label class="field-label">Date de naissance</label>
                                <input type="date" class="dtn" placeholder="Tapez votre date de naissance" name="demandeur.dtn" value="${demande != null ? demande.dtndemandeur : ''}">
                            </div>
                            <div>
                                <label class="field-label">profession</label>
                                <input type="text" class="profession" placeholder="Intitulé du poste" name="demandeur.profession" value="${demande != null ? demande.profession : ''}">
                            </div>
                        </div>
                        <div class="field-group col2">

                            <div>
                                <label class="field-label">Situation familiale</label>
                                <select name="demandeur.idsituationdefamille" class="situationFamiliale">
                                    <option value="">— Sélectionner —</option>
                                    <c:forEach items="${situationdefamilles}" var="sf">
                                        <option value="${sf.id}" ${demande.idsituationdefamille != null &&  demande.idsituationdefamille eq sf.id? "selected":''}>${sf.libelle}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div>
                                <label class="field-label">Nationalite</label>
                                <select name="demandeur.idnationalite" class="nationalite">
                                    <option value="">— Sélectionner —</option>
                                    <c:forEach items="${nationalites}" var="nat">
                                        <option value="${nat.id}" ${demande.idnationalite != null &&  demande.idnationalite eq nat.id ? "selected":''}>${nat.libelle}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="field-group">
                            <div>
                                <label class="field-label">Adresse à Madagascar</label>
                                <input type="text" class="adressemada" placeholder="Adresse complète" name="demandeur.adressemada" value="${demande != null ? demande.adressemada : ''}">
                            </div>
                        </div>
                        <div class="field-group col2">
                            <div><label class="field-label">Email</label>
                                <input class="email" type="email" placeholder="exemple@email.com" name="demandeur.email" value="${demande != null ? demande.email : ''}"></div>
                                <div><label class="field-label">Téléphone</label>
                                    <input class="tel"  type="tel" placeholder="+261 ..." name="demandeur.tel" value="${demande != null ? demande.tel : ''}"></div>
                                </div>

                                <div class="new-visa-carte" style="display: none;">
                                    <div class="section-title">Visa</div>
                                        <div class="field-group col6">
                                        <div>
                                            <label class="field-label">Référence visa</label>
                                            <input type="text" class="reference" name="visa.reference" placeholder="Réf. du visa" >
                                        </div>
                                    </div>
                                    <div class="field-group col2">
                                        <div>
                                            <label class="field-label">Date debut</label>
                                            <input class="dateentreemada" type="date" name="visa.datedebut" >
                                        </div>
                                        <div>
                                            <label class="field-label">Date d'expiration de visa</label>
                                            <input class="dateexpirationvisa" type="date" name="visa.dateexpiration" >
                                        </div>
                                    </div>

                                    <div class="section-title">Carte resident</div>
                                        <div class="field-group col6">
                                        <div>
                                            <label class="field-label">Référence du carte</label>
                                            <input type="text" class="reference" name="carte.reference" placeholder="Réf. du carte resident">
                                        </div>
                                    </div>
                                    <div class="field-group col2">
                                        <div>
                                            <label class="field-label">Date debut</label>
                                            <input class="dateentreemada" type="date" name="carte.datedebut">
                                        </div>
                                        <div>
                                            <label class="field-label">Date d'expiration du carte visa</label>
                                            <input class="dateexpirationvisa" type="date" name="carte.dateexpiration">
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="info-card-item">
                                <div class="section-title title-passport">Passeport</div>
                                <div class="field-group">
                                    <div>
                                        <label class="field-label">Numéro de passeport</label>
                                        <input class="passport" type="text" name="passport.numero" placeholder="N° passeport" value="${demande != null ? demande.numeropassport : ''}">
                                    </div>
                                </div>
                                <div class="field-group col2">
                                    <div>
                                        <label class="field-label">Date de délivrance</label>
                                        <input class="datedelivrance" type="date" name="passport.datedelivrance" value="${demande != null ? demande.datedelivrancepassport : ''}">
                                    </div>
                                    <div>
                                        <label class="field-label">Date d'expiration</label>
                                        <input class="dateexpiration" type="date" name="passport.dateexpiration" value="${demande != null ? demande.dateexpirationpassport : ''}">
                                    </div>
                                </div>
                                <div class="new-passport" style="display: none;">
                                    <div class="section-title title-passport">Nouveau passeport</div>
                                    <div class="field-group">
                                        <div>
                                            <label class="field-label">Numéro de passeport</label>
                                            <input class="passport" type="text" name="newpassport.numero" placeholder="N° passeport" >
                                        </div>
                                    </div>
                                    <div class="field-group col2">
                                        <div>
                                            <label class="field-label">Date de délivrance</label>
                                            <input class="datedelivrance" type="date" name="newpassport.datedelivrance" >
                                        </div>
                                        <div>
                                            <label class="field-label">Date d'expiration</label>
                                            <input class="dateexpiration" type="date" name="newpassport.dateexpiration" >
                                        </div>
                                    </div>
                                </div>

                                <div class="section-title">Visa transformable</div>
                                <div class="field-group col2">
                                    <div>
                                        <label class="field-label">Référence visa</label>
                                        <input type="text" class="reference" name="visatransformable.reference" placeholder="Réf. du visa" value="${demande != null ? demande.referencevt : ''}">
                                    </div>
                                    <div>
                                        <label class="field-label">Lieu d'entrée Madagascar</label>
                                        <input type="text" class="lieuentree" name="visatransformable.lieuentree" placeholder="ex. aeroport de Nosy Be" value="${demande != null ? demande.lieuentree : ''}">
                                    </div>
                                </div>
                                <div class="field-group col2">
                                    <div>
                                        <label class="field-label">Date d'entrée à Madagascar</label>
                                        <input class="dateentreemada" type="date" name="visatransformable.dateentreemada" value="${demande != null ? demande.dateentreemada : ''}">
                                    </div>
                                    <div>
                                        <label class="field-label">Date d'expiration de visa</label>
                                        <input class="dateexpirationvisa" type="date" name="visatransformable.dateexpiration" value="${demande != null ? demande.dateexpirationvt : ''}">
                                    </div>
                                </div>

                                <div class="section-title">Type de demande</div>
                                <div class="radio-group">
                                    <div class="radio-group">

                                        <c:forEach items="${typedemandes}" var="td" varStatus="status">
                                            <label>
                                                <c:choose>
                                                    <%-- Cas 1 : Nouvelle demande (on coche le premier par défaut) --%>
                                                    <c:when test="${demande == null}">
                                                        <input type="radio" name="idTypeDemande" value="${td.id}" ${status.first ? 'checked' : ''}>
                                                    </c:when>

                                                    <%-- Cas 2 : Modification (on coche celui qui correspond à l'ID stocké) --%>
                                                    <c:otherwise>
                                                        <input type="radio" name="idTypeDemande" value="${td.id}" ${td.id eq demande.idtypedemande ? 'checked' : ''}>
                                                    </c:otherwise>
                                                </c:choose>
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
                                            <c:set var="allChecked" value="${not empty dossierstandards && not empty dossierstandardscheckes && fn:length(dossierstandards) == fn:length(dossierstandardscheckes)}" />

                                            <label>
                                                <input type="checkbox" id="all-standards" ${allChecked ? 'checked' : ''}> Tout cocher
                                            </label>
                                            <c:forEach items="${dossierstandards}" var="ds">
                                                <%-- 2. Réinitialisation pour chaque ligne --%>
                                                <c:set var="fstIsChecked" value="false" />

                                                <c:forEach items="${dossierstandardscheckes}" var="checkSt">
                                                    <c:if test="${checkSt.iddossierstandard== ds.id && checkSt.exist}">
                                                        <c:set var="fstIsChecked" value="true" />
                                                    </c:if>
                                                </c:forEach>

                                                <label>
                                                    <input type="checkbox" name="dossiersStandard" value="${ds.id}" ${fstIsChecked ? 'checked' : ''}> ${ds.libelle}
                                                </label>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div class="subfolder-list">
                                        <div class="subfolder-title">Dossiers supplemetaires</div>
                                        <div id="specific-folder" class="checkbox-group">
                                            <c:forEach items="${dossiersupplementaires}" var="dsp">
                                                <%-- 2. Réinitialisation pour chaque ligne --%>
                                                <c:set var="fspIsChecked" value="false" />

                                                <c:forEach items="${dossiersupplementairescheckes}" var="checkSp">
                                                    <c:if test="${checkSp.iddossiersupplementaire == dsp.id && checkSp.exist}">
                                                        <c:set var="fspIsChecked" value="true" />
                                                    </c:if>
                                                </c:forEach>
                                                <label class="folder-${dsp.idtypevisa}">
                                                    <input type="checkbox" name="dossiersSup" value="${dsp.id}" ${fspIsChecked ? 'checked' : ''}> ${dsp.libelle}
                                                </label>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <button class="submit-btn" type="submit">${demande != null ? "Modifier la demande":"Soumettre la demande"}</button>
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
                            console.log("voalohany:",initialType)
                            if (initialType) {
                                console.log("Initialisation avec le type :", initialType);
                                renderFolder(initialType);
                            }


                            // ******* traitement du autocomplement du sans donne interieur **********

                            
                        })
                    </script>
                </body>
            </html>