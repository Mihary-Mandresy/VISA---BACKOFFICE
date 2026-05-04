<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${demande != null ? "Modification demande de Transformation de visa": "Creation demande de transformation de
        visa"}</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/form-visa.css">
    <script src="${pageContext.request.contextPath}/assets/js/jquery-4.0.0.min.js"></script>
    <%@ include file="../../../includes/css.jsp" %>
</head>

<body>
    <%@ include file="../../../includes/header.jsp" %>
    <main>
    <div class="title-page"><h2>Scanner Le demande</h2></div>
    <form class="form-wrap" action="./${id}" method="post" enctype="multipart/form-data">

        <div class="form-header">
            <h2>Scanner les dossiers</h2>
        </div>
        <div class="form-body">
            <div class="groupe-infos">
                <div class="info-card-item">
                    <div class="section-title">Dossier standard</div>
                    
                    <c:forEach items="${dstds}" var="dossierStandard">
                    <div class="field-group">
                        <div>
                            <label class="field-label">${dossierStandard.libelle}</label>
                            <input type="file" name="${dossierStandard.id}" multiple />
                        </div>
                    </div>
                    </c:forEach>
                </div>
                <div class="info-card-item">
                    <div class="section-title">Dossier supplementaire</div>
                    <c:forEach items="${dsups}" var="dossierSupplementaire">
                    <div class="field-group">
                        <div>
                            <label class="field-label">${dossierSupplementaire.libelle}</label>
                            <input type="file" name="${dossierSupplementaire.id}" multiple />
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <button class="submit-btn" type="submit">Envoyer le dossier</button>
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
    </form>
</main>

</body>

</html>