<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Livres par Genre</title>
</head>
<body>
    <h1>Recherche de livres par genre</h1>

    <form action="classification" method="get">
        <label for="genre">Choisir un genre :</label>
        <select name="genre" id="genre">
            <c:forEach var="g" items="${genres}">
                <option value="${g.id_genre}" <c:if test="${g.id_genre == selectedGenreId}">selected</c:if>>
                    ${g.genre}
                </option>
                
            </c:forEach>
        </select>
        <input type="submit" value="Rechercher">
    </form>

    <hr>

    <h2>Résultat :</h2>
    <c:if test="${empty livres}">
        <p>Aucun livre trouvé pour ce genre.</p>
    </c:if>

    <c:if test="${not empty livres}">
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Titre</th>
                    <th>Auteur</th>
                    <th>ISBN</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="livre" items="${livres}">
                    <tr>
                        <td>${livre.id}</td>
                        <td>${livre.titre}</td>
                        <td>${livre.auteur}</td>
                        <td>${livre.isbn}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>
