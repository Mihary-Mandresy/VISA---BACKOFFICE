<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/table-demande.css">
        <title>Liste des Demandes avec des details</title>
    </head>
    <body>
        <table>
            <tr>
                <th>#</th>
                <th>date de creation</th>
                <th>identifiant passeport </th>
                <th>identifiant visa transformable</th>
                <th>etat</th>
                <th>type de demande</th>
                <th> nom et prenom(s) demandeur</th>
                <th>type de visa</th>
                <th>Action(s)</th>
            </tr>
            <c:forEach items="${listeDemandes}" var="demande">
                <tr>
                    <td>${demande.id}</td>
                    <td>${demande.datecreation}</td>
                    <td>${demande.idpassport}</td>
                    <td>${demande.idvisatransformable}</td>
                    <td>${demande.libelleetatdemande}</td>
                    <td>${demande.libelletypedemande}</td>
                    <td>${demande.nomdemandeur} ${demande.prenomdemandeur}</td>
                    <td>${demande.libelletypevisa}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/demande/detail?id=${demande.id}">
                        <button class="btn-edit">Modifier</button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>