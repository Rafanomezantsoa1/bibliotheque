<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion Bibliothèque</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .container {
            background: rgba(255, 255, 255, 0.95);
            backdrop-filter: blur(10px);
            border-radius: 20px;
            padding: 40px;
            box-shadow: 0 25px 50px rgba(0, 0, 0, 0.2);
            max-width: 600px;
            width: 100%;
            border: 1px solid rgba(255, 255, 255, 0.3);
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
            font-size: 2.5em;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
        }

        .section {
            margin-bottom: 30px;
            padding: 20px;
            background: rgba(255, 255, 255, 0.7);
            border-radius: 15px;
            border-left: 4px solid #667eea;
        }

        .section h2 {
            color: #555;
            margin-bottom: 15px;
            font-size: 1.3em;
        }

        a {
            display: inline-block;
            background: linear-gradient(45deg, #667eea, #764ba2);
            color: white;
            text-decoration: none;
            padding: 12px 25px;
            border-radius: 25px;
            margin: 5px 10px 5px 0;
            transition: all 0.3s ease;
            font-weight: 500;
            box-shadow: 0 4px 15px rgba(102, 126, 234, 0.3);
        }

        a:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(102, 126, 234, 0.5);
            background: linear-gradient(45deg, #764ba2, #667eea);
        }

        form {
            display: inline-block;
            margin: 5px 10px 5px 0;
        }

        input[type="submit"] {
            background: linear-gradient(45deg, #667eea, #764ba2);
            color: white;
            border: none;
            padding: 12px 25px;
            border-radius: 25px;
            cursor: pointer;
            font-weight: 500;
            transition: all 0.3s ease;
            box-shadow: 0 4px 15px rgba(102, 126, 234, 0.3);
        }

        input[type="submit"]:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(102, 126, 234, 0.5);
            background: linear-gradient(45deg, #764ba2, #667eea);
        }

        .books-section {
            border-left-color: #28a745;
        }

        .loans-section {
            border-left-color: #ffc107;
        }

        .books-section a {
            background: linear-gradient(45deg, #28a745, #20c997);
            box-shadow: 0 4px 15px rgba(40, 167, 69, 0.3);
        }

        .books-section a:hover {
            background: linear-gradient(45deg, #20c997, #28a745);
            box-shadow: 0 8px 25px rgba(40, 167, 69, 0.5);
        }

        .loans-section a,
        .loans-section input[type="submit"] {
            background: linear-gradient(45deg, #ffc107, #fd7e14);
            box-shadow: 0 4px 15px rgba(255, 193, 7, 0.3);
        }

        .loans-section a:hover,
        .loans-section input[type="submit"]:hover {
            background: linear-gradient(45deg, #fd7e14, #ffc107);
            box-shadow: 0 8px 25px rgba(255, 193, 7, 0.5);
        }

        @media (max-width: 768px) {
            .container {
                padding: 20px;
                margin: 10px;
            }
            
            h1 {
                font-size: 2em;
            }
            
            a, input[type="submit"] {
                display: block;
                width: 100%;
                margin: 10px 0;
                text-align: center;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Gestion Bibliothèque</h1>
        
        <div class="section books-section">
            <h2>Gestion des Livres</h2>
            <a href="form-livre">Ajouter un livre</a>
            <a href="list-livre">Liste des livres</a>
            <!-- <a href="recherche">Rechercher un livre</a> -->
            <a href="classification">Classer par genre</a>
        </div>

        <div class="section loans-section">
            <h2>Gestion des Prêts</h2>
            <a href="form-pret">Faire un prêt</a>
            <!-- <a href="form-prolongement">Prolonger un prêt</a> -->
            <form action="en-cours" method="get">
                <input type="submit" value="Liste des prêts">
            </form>
        </div>
    </div>
</body>
</html>