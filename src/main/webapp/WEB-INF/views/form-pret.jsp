<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8" />
    <title>Formulaire de prêt</title>
</head>
<body>
    <h2>Formulaire de prêt</h2>

    <form action="insert-pret" method="post">

        <label for="livre">Choisissez un livre</label>
        <select name="livre" id="livre" required>
            <option value="" disabled selected>-- Sélectionnez un livre --</option>
            <c:forEach var="livre" items="${livres}">
                <option value="${livre.id}">${livre.titre}</option>
            </c:forEach>
        </select>

        <br/><br/>

        <label for="type">Type de prêt</label>
        <select name="type" id="type" required>
            <option value="" disabled selected>-- Sélectionnez un type --</option>
            <c:forEach var="type" items="${types}">
                <option value="${type.id}">${type.type}</option>
            </c:forEach>
        </select>

        <br/><br/>

        <label for="nbr">Nombre d'exemplaires</label>
        <input type="number" name="nbr" id="nbr" min="1" required />

        <br/><br/>

        <label for="id_adherent">Numéro adhérent</label>
        <input type="number" name="id_adherent" id="id_adherent" min="1" required />

        <br/><br/>

        <label for="date">Date début du prêt</label>
        <input type="date" name="date_debut" id="date" required />

        <label for="date">Date fin du prêt</label>
        <input type="date" name="date_fin" id="date" required />

        <br/><br/>

        <button type="submit">Valider</button>
    </form>
</body>
</html>
