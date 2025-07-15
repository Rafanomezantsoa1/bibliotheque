<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Liste des prêts en cours</title>
    <!-- <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 8px;
            border: 1px solid #ccc;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        h2 {
            margin-top: 20px;
        }
    </style> -->
</head>
<body>

<h2>Liste des prêts en cours ou prolongés</h2>

<c:if test="${empty pretsEnCours}">
    <p>Aucun prêt en cours ou prolongé.</p>
</c:if>

<c:if test="${not empty pretsEnCours}">
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Adhérent</th>
                <th>Livre</th>
                <th>Date de prêt</th>
                <th>Date de fin</th>
                <th>Type de prêt</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="pret" items="${pretsEnCours}">
                <tr>
                    <td>${pret.id}</td>
                    <td>${pret.adherent.nom} ${pret.adherent.prenom}</td>
                    <td>${pret.livre.titre}</td>
                    <td>${pret.datePret.toLocalDate()}</td>
                    <td>${pret.dateFin}</td>
                    <td>${pret.typePret.type}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>

</body>
</html>
