<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Livres</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 30px;
        }
        th, td {
            border: 1px solid #aaa;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #eee;
        }
    </style>
</head>
<body>
    <h1>Liste des Livres avec leurs Genres</h1>

    <c:if test="${not empty message}">
        <p style="color: green; font-weight: bold;">${message}</p>
    </c:if>

    <table>
        <thead>
            <tr>
                <th>Titre</th>
                <th>Auteur</th>
                <th>Date de Publication</th>
                <th>ISBN</th>
                <th>Quantité</th>
                <th>Genres</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="livre" items="${listLivres}">
                <tr>
                    <td>${livre.titre}</td>
                    <td>${livre.auteur}</td>
                    <td>${livre.datePublication}</td>
                    <td>${livre.isbn}</td>
                    <td>${livre.nbTotal}</td>
                    <td>
                        
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
