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
                           <c:if test="${dossiers.size() == 0}">
                                <div class="alert alert-warning">
                                    Aucun dossier
                                    <span class="close-btn" onclick="this.parentElement.style.display='none'">&times;</span>
                                </div>
                            </c:if> 

                                <c:forEach items="${dossiers}" var="dossier">
                                    <div style="margin: 20px;">
                                        <p>${dossier.libelle} - <span class="badge badge-primary">${dossier.type}</span> </p>
                                        <iframe src="${pageContext.request.contextPath}/demande/afficher-pdf/${dossier.filePdf.id}" 
                                        width="100%" 
                                        height="500px">
                                    </iframe>
                                </div>
                                <hr/>
                            </c:forEach>
                        </main>
                    </div>
                </div>
            </body>

            </html>