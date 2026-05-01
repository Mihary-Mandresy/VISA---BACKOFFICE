<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Liste des Demandes avec des details</title>
        <%@ include file="../../includes/css.jsp" %>
    </head>
    <body>
        <%@ include file="../../includes/header.jsp" %>
        <main>
        <div class="title-page"><h2> Liste des demandes</h2></div>
        <table class="table table-hover table-striped ">
            <thead class="thead-info">
                <th>#</th>
                <th>Demandeur</th>
                <th>Date creation</th>
                <th>Etat</th>
                <th>Type de demande</th>
                <th>Visa demander</th>
                <th>Action(s)</th>
            </thead>
            <tbody>
                <c:forEach items="${listeDemandes}" var="demande">
                    <tr>
                        <td>${demande.id}</td>
                        <td>${demande.nomdemandeur} ${demande.prenomdemandeur}</td>
                        <td>${demande.datecreation}</td>
                        <td><span class="badge badge-primary">${demande.libelleetatdemande}</span></td>
                        <td>${demande.libelletypedemande}</td>
                        <td>${demande.libelletypevisa}</td>
                        <td style="display: flex; gap: 5px;">
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/demande/detail?id=${demande.id}">
                            <i class="mdi mdi-pen"></i>
                            </a>

                            <a class="btn btn-warning"  href="${pageContext.request.contextPath}/demande/detail?id=${demande.id}">
                            <i  class="mdi mdi-eye"></i>
                            </a>

                            <a class="btn btn-success" href="${pageContext.request.contextPath}/demande/detail?id=${demande.id}">
                            <i  class="mdi mdi-credit-card-scan"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </main>
    </body>
</html>