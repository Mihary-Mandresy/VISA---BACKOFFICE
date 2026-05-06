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

                <div class="container-fluid h-100">
                    <div class="row g-0">
                        <div class="col-12 col-md-4 col-lg-4">
                            <%@ include file="../../includes/header.jsp" %>
                        </div>
                        <main class="col-12 col-md-8 col-lg-8 bg-s py-5 px-5">
                            <div class="row mb-5">
                                <div class="col-4 offset-4 text-center">

                                    <img class="img-fluid border rounded shadow-sm p-2 bg-white"
                                        src="data:image/png;base64,${qrcode}"
                                        alt="QR Code de la demande n°${demande.id}" />

                                    <p class="mt-3 text-white">
                                        <i class="mdi mdi-qrcode-scan mdi-24px"></i>
                                        QR Code de la demande
                                    </p>

                                </div>
                            </div>

                            <div class="row mb-5">

                                <!-- DEMANDEUR -->
                                <div class="col-8">
                                    <div class="card shadow-sm h-100 fond-card">

                                        <div class="card-header bg-primary text-white">
                                            <h3 class="mb-0">
                                                <i class="mdi mdi-account-circle mdi-24px"></i>
                                                Information du demandeur
                                            </h3>
                                        </div>

                                        <div class="card-body">

                                            <p>
                                                <i class="mdi mdi-account"></i>
                                                <strong>Nom :</strong>
                                                ${fichedemande.demandeur.nom}
                                            </p>

                                            <p>
                                                <i class="mdi mdi-account-outline"></i>
                                                <strong>Prénom :</strong>
                                                ${fichedemande.demandeur.prenom}
                                            </p>

                                            <p>
                                                <i class="mdi mdi-calendar"></i>
                                                <strong>Date de naissance :</strong>
                                                ${fichedemande.demandeur.dtn}
                                            </p>

                                            <p>
                                                <i class="mdi mdi-briefcase"></i>
                                                <strong>Profession :</strong>
                                                ${fichedemande.demandeur.profession}
                                            </p>

                                            <p>
                                                <i class="mdi mdi-map-marker"></i>
                                                <strong>Adresse :</strong>
                                                ${fichedemande.demandeur.adressemada}
                                            </p>

                                            <p>
                                                <i class="mdi mdi-phone"></i>
                                                <strong>Téléphone :</strong>
                                                ${fichedemande.demandeur.tel}
                                            </p>

                                            <p>
                                                <i class="mdi mdi-email"></i>
                                                <strong>Email :</strong>
                                                ${fichedemande.demandeur.email}
                                            </p>

                                        </div>
                                    </div>
                                </div>

                                <!-- PASSEPORT + VISA -->
                                <div class="col-4 d-flex flex-column">

                                    <!-- PASSEPORT -->
                                    <div class="card mb-5 shadow-sm fond-card">

                                        <div class="card-header bg-success text-white">
                                            <h3 class="mb-0">
                                                <i class="mdi mdi-passport"></i>
                                                Passeport
                                            </h3>
                                        </div>

                                        <div class="card-body">

                                            <p>
                                                <i class="mdi mdi-card-account-details"></i>
                                                <strong>Numéro :</strong>
                                                ${fichedemande.passport.numero}
                                            </p>

                                            <p>
                                                <i class="mdi mdi-calendar-check"></i>
                                                <strong>Délivrance :</strong>
                                                ${fichedemande.passport.datedelivrance}
                                            </p>

                                            <p>
                                                <i class="mdi mdi-calendar-remove"></i>
                                                <strong>Expiration :</strong>
                                                ${fichedemande.passport.dateexpiration}
                                            </p>

                                        </div>
                                    </div>

                                    <!-- VISA -->
                                    <div class="card shadow-sm border-0 rounded-top rounded-bottom fond-card">

                                        <div class="card-header bg-warning rounded-top text-white">
                                            <h3 class="mb-0">
                                                <i class="mdi mdi-airplane"></i>
                                                Visa Transformable
                                            </h3>
                                        </div>

                                        <div class="card-body">

                                            <p>
                                                <i class="mdi mdi-pound"></i>
                                                <strong>Référence :</strong>
                                                ${fichedemande.visatransformable.reference}
                                            </p>

                                            <p>
                                                <i class="mdi mdi-calendar-import"></i>
                                                <strong>Entrée :</strong>
                                                ${fichedemande.visatransformable.dateentreemada}
                                            </p>

                                            <p>
                                                <i class="mdi mdi-calendar-clock"></i>
                                                <strong>Expiration :</strong>
                                                ${fichedemande.visatransformable.dateexpiration}
                                            </p>

                                            <p>
                                                <i class="mdi mdi-map-marker-radius"></i>
                                                <strong>Lieu d'entrée :</strong>
                                                ${fichedemande.visatransformable.lieuentree}
                                            </p>

                                        </div>
                                    </div>

                                </div>

                            </div>
                            <!-- DOSSIERS -->
                            <div class="row">

                                <!-- STANDARD -->
                                <div class="col">
                                    <div class="card shadow-sm fond-card">
                                        <div class="card-header bg-info text-white">
                                            <h3 class="mb-0">
                                                <i class="mdi mdi-folder-check"></i>
                                                Dossiers standard
                                            </h3>
                                        </div>

                                        <div class="card-body">

                                            <c:forEach var="d" items="${fichedemande.dossierStandard}">
                                                <p>
                                                    <i class="mdi mdi-file-document-outline"></i>
                                                    ${d.libelle}

                                                    <c:choose>
                                                        <c:when test="${d.exist}">
                                                            <i class="mdi mdi-check-circle text-success ms-2"></i>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <i class="mdi mdi-close-circle text-danger ms-2"></i>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </p>
                                            </c:forEach>

                                        </div>
                                    </div>
                                </div>

                                <!-- SUPPLÉMENTAIRES -->
                                <div class="col">
                                    <div class="card shadow-sm fond-card">
                                        <div class="card-header bg-secondary text-white">
                                            <h3 class="mb-0">
                                                <i class="mdi mdi-folder-plus"></i>
                                                Dossiers supplementaires
                                            </h3>
                                        </div>

                                        <div class="card-body">

                                            <c:forEach var="d" items="${fichedemande.dossierSupplementaire}">
                                                <p>
                                                    <i class="mdi mdi-file-plus-outline"></i>
                                                    ${d.libelle}

                                                    <c:choose>
                                                        <c:when test="${d.exist}">
                                                            <i class="mdi mdi-check-circle text-success ms-2"></i>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <i class="mdi mdi-close-circle text-danger ms-2"></i>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </p>
                                            </c:forEach>

                                        </div>
                                    </div>
                                </div>

                            </div>
                        </main>
                    </div>
                </div>
            </body>

            </html>