<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form action="insert-pret" method="get">
        <label for="livre">Choisissez une livre</label>
        <select name="livre" id="livre">
            <option value="titre">titre du livre</option>
        </select>

        <label for="type">Type</label>
        <select name="type" id="type">
            <option value="idtype">nom</option>
        </select>

        <label for="nbr"> Nombre d'exemplaire</label>
        <input type="number" name="nbr" id="nbr">

        <label for="adherent">Numero Adhérent</label>
        <input type="number" name="id_adherent" id="adherent">

        <label for="date">Date du pret</label>
        <input type="date" name="date" id="date">
    </form>
</body>
</html>