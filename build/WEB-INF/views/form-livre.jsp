<%@ page import="entities.Livre" %>
<%@ page import="entities.Genre" %>
<%@ page import="java.util.List" %>

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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylelalaina.css" />
</head>
<body>
  <div class="container">

    <% if (message != null) { %>
      <div class="alert alert-info text-center">
        <strong><%= message %></strong>
      </div>
    <% } %>

    <% if (idLivre == null) { %>
      <h2 class="text-center">Ajouter un Livre</h2>
    <% } else { %>
      <h2 class="text-center">Modifier un Livre</h2>
    <% } %>

    <form action="livre" method="post" class="form-horizontal">
      <% if (idLivre != null) { %>
        <input type="hidden" name="idLivre" value="<%= idLivre %>" />
      <% } %>

      <div class="form-group">
        <label for="titre" class="col-sm-3 control-label">Titre :</label>
        <div class="col-sm-6">
          <input type="text" id="titre" name="titre" class="form-control"
                 value="<%= livre != null ? livre.getTitre() : "" %>" required />
        </div>
      </div>

      <div class="form-group">
        <label for="auteur" class="col-sm-3 control-label">Auteur :</label>
        <div class="col-sm-6">
          <input type="text" id="auteur" name="auteur" class="form-control"
                 value="<%= livre != null ? livre.getAuteur() : "" %>" required />
        </div>
      </div>

      <div class="form-group">
        <label for="datePublication" class="col-sm-3 control-label">Date de publication :</label>
        <div class="col-sm-6">
          <input type="date" id="datePublication" name="datePublication" class="form-control"
                 value="<%= livre != null && livre.getDatePublication() != null ? livre.getDatePublication() : "" %>" required />
        </div>
      </div>

      <div class="form-group">
        <label for="isbn" class="col-sm-3 control-label">ISBN :</label>
        <div class="col-sm-6">
          <input type="text" id="isbn" name="isbn" class="form-control"
                 value="<%= livre != null ? livre.getIsbn() : "" %>" required />
        </div>
      </div>

      <div class="form-group">
        <label for="nbTotal" class="col-sm-3 control-label">Nombre total :</label>
        <div class="col-sm-6">
          <input type="number" id="nbTotal" name="nbTotal" class="form-control"
                 value="<%= livre != null ? livre.getNbTotal() : "" %>" required />
        </div>
      </div>

      <div class="form-group">
        <label for="genres" class="col-sm-3 control-label">Genres :</label>
        <div class="col-sm-6">
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
      </div>

      <div class="form-group">
        <div class="col-sm-offset-3 col-sm-6">
          <button type="submit" class="btn btn-primary">Soumettre</button>
        </div>
      </div>
    </form>

    <div class="list-group text-center" style="max-width: 300px; margin: 30px auto;">
      <a href="list-livre" class="list-group-item list-group-item-action">
        Retour à la liste des livres
      </a>
    </div>

  </div>
</body>
</html>
