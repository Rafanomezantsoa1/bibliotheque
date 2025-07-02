<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Liste des Livres</title>
</head>
<body>
    <h1>Liste des Livres</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Titre</th>
                <th>Auteur</th>
                <th>ISBN</th>
                <th>Date de publication</th>
                <th>Nombre total</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="livre" items="${livres}">
            <tr>
                <td>${livre.id}</td>
                <td>${livre.titre}</td>
                <td>${livre.auteur}</td>
                <td>${livre.isbn}</td>
                <td>${livre.datePublication}</td>
                <td>${livre.nbTotal}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
