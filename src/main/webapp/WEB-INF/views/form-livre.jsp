<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form method="post" action="/livre/create">

        <label for="titre">Titre :</label>
        <input type="text" id="titre" name="titre" required><br><br>
    
        <label for="auteur">Auteur :</label>
        <input type="text" id="auteur" name="auteur"><br><br>
    
        <label for="isbn">ISBN :</label>
        <input type="text" id="isbn" name="isbn"><br><br>
    
        <label for="nb_total">Nombre d'exemplaires :</label>
        <input type="number" id="nb_total" name="nb_total" min="1" required><br><br>
    
        <label for="date_publication">Date de publication :</label>
        <input type="date" id="date_publication" name="date_publication"><br><br>
    
        <label for="genres">Genres du livre :</label><br>
        <select name="genres" id="genres" multiple size="5" required>
            <c:forEach var="genre" items="${listGenres}">
                <option value="${genre.idCategorie}">${genre.genre}</option>
            </c:forEach>
        </select>
        <p><small>(Maintenir Ctrl ou Maj pour sélectionner plusieurs genres)</small></p>
    
        <br>
        <button type="submit">Ajouter le livre</button>
    </form>
    
</body>
</html>