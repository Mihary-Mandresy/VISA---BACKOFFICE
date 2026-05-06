<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
            <!DOCTYPE html>
            <html lang="fr">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>${demande != null ? "Modification demande de Transformation de visa": "Creation demande de
                    transformation de visa"}</title>

                <script src="${pageContext.request.contextPath}/assets/js/jquery-4.0.0.min.js"></script>
                <script src="${pageContext.request.contextPath}/assets/js/form.js"></script>
                <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fiche-demande.css">
                <%@ include file="../../includes/css.jsp" %>
            </head>

            <body>
                <div style="display: flex;flex-direction: row; justify-content: space-between;">
                    <%@ include file="../../includes/header.jsp" %>
                        <main>
                            <div class="row justify-content-start py-5" style="margin-left: 0;">
                                <div class="col-12 col-md-10 col-lg-9">
                                    <div class="card fiche-card">
                                        <!-- ══════════════════════════════
                                    EN-TÊTE
                                    ══════════════════════════════════ -->
                                        <div class="card-header badge-primary">
                                            <h5 class="mb-0 text-white" style="font-size:16px; font-weight:500;">
                                                Fiche de demande
                                            </h5>
                                        </div>

                                        <!-- ══════════════════════════════
                                    CORPS
                                    ══════════════════════════════════ -->
                                        <div class="card-body p-5 bg-dark">

                                            <div class="d-flex flex-column align-items-center gap-4">

                                                <!-- ── COLONNE GAUCHE : QR Code (4/12) ── -->
                                                <div class="col-12 col-md-4 d-flex flex-column align-items-center">

                                                    <div class="qr-wrapper">
                                                        <img src="data:image/png;base64,${qrcode}"
                                                            alt="QR Code de la demande n°${demande.id}" />
                                                    </div>

                                                    <p class="text-center mt-2 mb-0"
                                                        style="font-size:12px; color:#6c757d;">
                                                        QR Code de la demande
                                                    </p>

                                                </div>

                                                <!-- ── COLONNE DROITE : Informations (8/12) ── -->
                                                <div class="col-12 col-md-8">

                                                    <%-- dl.row=grille Bootstrap dt.col-5=label (largeur 5/12)
                                                        dd.col-7=valeur (largeur 7/12) --%>
                                                        <dl class="row info-list mb-0 ">

                                                            <!-- ID -->
                                                            <dt class="col-5">ID demande</dt>
                                                            <dd class="col-7">
                                                                <span class="badge-id">#${demande.id}</span>
                                                            </dd>

                                                            <!-- Demandeur -->
                                                            <dt class="col-5">Demandeur</dt>
                                                            <dd class="col-7">
                                                                <div class="d-flex align-items-center gap-2">
                                                                    <%-- Avatar initiales --%>
                                                                        <span class="avatar">
                                                                            ${fn:substring(demande.prenomdemandeur,0,1)}${fn:substring(demande.nomdemandeur,0,1)}
                                                                        </span>
                                                                        <span
                                                                            class="avatar-name">${demande.nomdemandeur}
                                                                            ${demande.prenomdemandeur}</span>

                                                                </div>
                                                            </dd>

                                                            <!-- Type de visa -->
                                                            <dt class="col-5">Type de visa</dt>
                                                            <dd class="col-7">
                                                                <span class="badge-visa">${demande.typevisa}</span>
                                                            </dd>

                                                            <!-- Type de demande -->
                                                            <dt class="col-5">Type de demande</dt>
                                                            <dd class="col-7">
                                                                <span class="badge-type">${demande.typedemande}</span>
                                                            </dd>

                                                            <!-- État -->
                                                            <dt class="col-5">État</dt>
                                                            <dd class="col-7">
                                                                <span class="badge-etat">
                                                                    <span class="dot"></span>
                                                                    ${demande.etatdemande}
                                                                </span>
                                                            </dd>

                                                        </dl>

                                                </div>

                                            </div><%-- /row --%>

                                        </div><%-- /card-body --%>

                                            <!-- ══════════════════════════════
                                            PIED DE PAGE : Boutons
                                        ══════════════════════════════════ -->
                                            <div class="card-footer d-flex gap-2 justify-content-end py-3 px-4 bg-dark">

                                                <a href="${pageContext.request.contextPath}/demande/liste.jsp"
                                                    class="btn btn-outline-light">
                                                    &larr; Retour à la liste
                                                </a>

                                                <a href="${pageContext.request.contextPath}/demande/detail?id=${demande.id}"
                                                    class="btn btn-primary btn-sm">
                                                    Modifier
                                                </a>

                                            </div>

                                    </div><%-- /card --%>

                                </div>
                            </div>
                </div>
                </main>
                </div>
            </body>

            </html>