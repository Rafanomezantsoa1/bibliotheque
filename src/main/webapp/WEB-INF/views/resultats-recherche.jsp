<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Résultats de la recherche</title></head>
<body>
<h1>Résultats de la recherche</h1>

<c:if test="${empty resultats}">
    <p>Aucun livre trouvé.</p>
</c:if>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Titre</th>
        <th>Auteur</th>
        <th>ISBN</th>
    </tr>
    <c:forEach var="livre" items="${resultats}">
        <tr>
            <td>${livre.id}</td>
            <td>${livre.titre}</td>
            <td>${livre.auteur}</td>
            <td>${livre.isbn}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
