<%@ page import="entities.Livre" %>
<%@ page import="entities.Genre" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%
  List<Genre> listGenres = (List<Genre>) request.getAttribute("listGenres");
  Livre livre = (Livre) request.getAttribute("livre");
  String message = (String) request.getAttribute("message");
  String idLivre = (String) request.getAttribute("idLivre");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8" />
  <title>Formulaire Livre</title>
  <style>
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }

    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      min-height: 100vh;
      padding: 20px;
      display: flex;
      justify-content: center;
      align-items: center;
    }

    .container {
      background: rgba(255, 255, 255, 0.95);
      backdrop-filter: blur(15px);
      border-radius: 20px;
      padding: 40px;
      box-shadow: 0 25px 50px rgba(0, 0, 0, 0.2);
      max-width: 800px;
      width: 100%;
      border: 1px solid rgba(255, 255, 255, 0.3);
    }

    h2 {
      text-align: center;
      color: #333;
      margin-bottom: 30px;
      font-size: 2.5em;
      text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
      background: linear-gradient(45deg, #667eea, #764ba2);
      -webkit-background-clip: text;
      -webkit-text-fill-color: transparent;
      background-clip: text;
    }

    .alert {
      background: linear-gradient(45deg, #28a745, #20c997);
      color: white;
      padding: 15px;
      border-radius: 10px;
      margin-bottom: 20px;
      text-align: center;
      box-shadow: 0 4px 15px rgba(40, 167, 69, 0.3);
      border: none;
    }

    .alert strong {
      font-size: 1.1em;
    }

    .form-horizontal {
      margin-bottom: 30px;
    }

    .form-group {
      display: flex;
      align-items: center;
      margin-bottom: 25px;
      min-height: 60px;
    }

    .control-label {
      flex: 0 0 200px;
      font-weight: 600;
      color: #555;
      text-align: right;
      margin-right: 20px;
      font-size: 1.1em;
    }

    .form-control {
      flex: 1;
      max-width: 400px;
      padding: 12px 16px;
      border: 2px solid #e0e0e0;
      border-radius: 10px;
      font-size: 16px;
      transition: all 0.3s ease;
      background: rgba(255, 255, 255, 0.9);
    }

    .form-control:focus {
      outline: none;
      border-color: #667eea;
      box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
      transform: translateY(-1px);
    }

    .form-control:hover {
      border-color: #667eea;
    }

    select.form-control {
      min-height: 120px;
      padding: 10px;
    }

    select.form-control option {
      padding: 8px;
      margin: 2px 0;
    }

    .btn {
      background: linear-gradient(45deg, #667eea, #764ba2);
      color: white;
      border: none;
      padding: 15px 30px;
      border-radius: 25px;
      cursor: pointer;
      font-weight: 600;
      font-size: 1.1em;
      transition: all 0.3s ease;
      box-shadow: 0 4px 15px rgba(102, 126, 234, 0.3);
      text-transform: uppercase;
      letter-spacing: 1px;
    }

    .btn:hover {
      transform: translateY(-2px);
      box-shadow: 0 8px 25px rgba(102, 126, 234, 0.5);
      background: linear-gradient(45deg, #764ba2, #667eea);
    }

    .btn:active {
      transform: translateY(0);
    }

    .submit-container {
      display: flex;
      justify-content: center;
      margin-top: 30px;
    }

    .navigation {
      text-align: center;
      margin-top: 30px;
    }

    .nav-link {
      display: inline-block;
      background: linear-gradient(45deg, #28a745, #20c997);
      color: white;
      text-decoration: none;
      padding: 12px 25px;
      border-radius: 25px;
      transition: all 0.3s ease;
      font-weight: 500;
      box-shadow: 0 4px 15px rgba(40, 167, 69, 0.3);
    }

    .nav-link:hover {
      transform: translateY(-2px);
      box-shadow: 0 8px 25px rgba(40, 167, 69, 0.5);
      background: linear-gradient(45deg, #20c997, #28a745);
      text-decoration: none;
      color: white;
    }

    .form-section {
      background: rgba(255, 255, 255, 0.5);
      padding: 30px;
      border-radius: 15px;
      margin-bottom: 20px;
      border: 1px solid rgba(255, 255, 255, 0.3);
    }

    input[type="date"] {
      color: #555;
    }

    input[type="number"] {
      -moz-appearance: textfield;
    }

    input[type="number"]::-webkit-outer-spin-button,
    input[type="number"]::-webkit-inner-spin-button {
      -webkit-appearance: none;
      margin: 0;
    }

    @media (max-width: 768px) {
      .container {
        padding: 20px;
        margin: 10px;
      }
      
      .form-group {
        flex-direction: column;
        align-items: flex-start;
      }
      
      .control-label {
        text-align: left;
        margin-bottom: 10px;
        margin-right: 0;
        flex: none;
      }
      
      .form-control {
        max-width: 100%;
      }
      
      h2 {
        font-size: 2em;
      }
    }

    .required::after {
      content: " *";
      color: #dc3545;
      font-weight: bold;
    }
  </style>
</head>
<body>
  <div class="container">

    <% if (message != null) { %>
      <div class="alert">
        <strong><%= message %></strong>
      </div>
    <% } %>

    <% if (idLivre == null) { %>
      <h2>Ajouter un Livre</h2>
    <% } else { %>
      <h2>Modifier un Livre</h2>
    <% } %>

    <div class="form-section">
      <form action="livre" method="post" class="form-horizontal">
        <% if (idLivre != null) { %>
          <input type="hidden" name="idLivre" value="<%= idLivre %>" />
        <% } %>

        <div class="form-group">
          <label for="titre" class="control-label required">Titre</label>
          <input type="text" id="titre" name="titre" class="form-control"
                 value="<%= livre != null ? livre.getTitre() : "" %>" required 
                 placeholder="Entrez le titre du livre" />
        </div>

        <div class="form-group">
          <label for="auteur" class="control-label required">Auteur</label>
          <input type="text" id="auteur" name="auteur" class="form-control"
                 value="<%= livre != null ? livre.getAuteur() : "" %>" required 
                 placeholder="Nom de l'auteur" />
        </div>

        <div class="form-group">
          <label for="datePublication" class="control-label required">Date de publication</label>
          <input type="date" id="datePublication" name="datePublication" class="form-control"
                 value="<%= livre != null && livre.getDatePublication() != null ? livre.getDatePublication() : "" %>" required />
        </div>

        <div class="form-group">
          <label for="isbn" class="control-label required">ISBN</label>
          <input type="text" id="isbn" name="isbn" class="form-control"
                 value="<%= livre != null ? livre.getIsbn() : "" %>" required 
                 placeholder="Format: 978-XXXXXXXXX" />
        </div>

        <div class="form-group">
          <label for="nbTotal" class="control-label required">Nombre total</label>
          <input type="number" id="nbTotal" name="nbTotal" class="form-control"
                 value="<%= livre != null ? livre.getNbTotal() : "" %>" required 
                 min="1" placeholder="Quantité disponible" />
        </div>

        <div class="form-group">
          <label for="genres" class="control-label required">Genres</label>
          <select id="genres" name="genres" class="form-control" multiple required>
            <% for (Genre g : listGenres) { 
                  boolean selected = false;
                  if (livre != null && livre.getGenres() != null) {
                    selected = livre.getGenres().stream().anyMatch(lg -> lg.getId_genre() == g.getId_genre());
                  }
            %>
              <option value="<%= g.getId_genre() %>" <%= selected ? "selected" : "" %>>
                <%= g.getGenre() %>
              </option>
            <% } %>
          </select>
        </div>

        <div class="submit-container">
          <button type="submit" class="btn">
            <% if (idLivre == null) { %>
              Ajouter le livre
            <% } else { %>
              Modifier le livre
            <% } %>
          </button>
        </div>
      </form>
    </div>

    <div class="navigation">
      <a href="list-livre" class="nav-link">
        ← Retour à la liste des livres
      </a>
    </div>

  </div>
</body>
</html>