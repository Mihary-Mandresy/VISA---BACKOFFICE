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
    <div class="title-page"><h2>Approuver le demande</h2></div>
    <form class="form-wrap" action="${pageContext.request.contextPath}/demande/scan/approver" method="post" >

        <div class="form-header">
            <h2>Entrer le date de validite du carte resident</h2>
        </div>
        <div class="form-body">
            <div class="groupe-infos">
                <div class="info-card-item">
                    <div class="section-title">Date debut</div>
                    <div class="field-group">
                        <div>
                            <input class="dateexpirationvisa" type="date" name="datedebut">
                        </div>
                    </div>
                </div>
                <div class="info-card-item">
                    <div class="section-title">Date d'expiration</div>
                    <div class="field-group">
                        <div>
                            <input class="dateexpirationvisa" type="date" name="datefin">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <input type="hidden" name="iddemande" value="${id}">
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