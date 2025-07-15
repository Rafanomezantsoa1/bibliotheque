<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8" />
    <title>Erreur</title>
</head>
<body>
    <div class="container">
        <h1>Erreur</h1>
        <p>
            <c:out value="${error}" default="Une erreur est survenue."/>
        </p>
        <p>
            <a href="form-pret">Retour au formulaire</a>
        </p>
    </div>
</body>
</html>
