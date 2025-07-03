<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Livres</title>
</head>
<body>
    <form action="recherche" method="get">
        <select name="genre_recherche">
            <option value="titre">Titre</option>
            <option value="auteur">Auteur</option>
            <option value="isbn">ISBN</option>
        </select>
        <input type="text" name="a_chercher" placeholders="entrer ici ce que vous voulez chercher">
        <input type="submit" value="rechercher">
    </form>
</body>
</html>
