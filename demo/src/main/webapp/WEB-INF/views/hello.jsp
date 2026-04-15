<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
</head>
<body>

    <form action="/" method="post">
        <div class="form-group">
            <label for="">Nom</label>
            <input type="text" value="Deraman" placeholder="Ex. Deraman" name="nom">
        </div>
        <div class="form-group">
            <label for="">Prenom</label>
            <input type="text" value="SuperMan" placeholder="Ex. SuperMan" name="prenom">
        </div>
        <button type="submit">Enregistrer</button>
    </form>

    <c:if test="${not empty message}">
        <span>Message : <b>${message}</b></span>
    </c:if>
    <c:if test="${not empty error}">
        <span>Error : <b>${message}</b></span>
    </c:if>

</body>
</html>