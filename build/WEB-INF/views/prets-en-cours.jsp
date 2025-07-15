<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Prêts en cours - Bibliothèque</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        .table-hover tbody tr:hover {
            background-color: #f8f9fa;
        }
        .badge-warning {
            background-color: #ffc107;
            color: #212529;
        }
        .badge-danger {
            background-color: #dc3545;
            color: white;
        }
        .badge-success {
            background-color: #198754;
            color: white;
        }
        .btn-sm {
            padding: 0.25rem 0.5rem;
            font-size: 0.875rem;
        }
        .search-box {
            margin-bottom: 20px;
        }
        .alert {
            margin-bottom: 20px;
        }
    </style>
</head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<tbody>
    <!-- <c:forEach var="pret" items="${pretsEnCours}">
        <tr data-pret-id="${pret.id}">
            <td>
                <strong>${pret.adherent.nom} ${pret.adherent.prenom}</strong><br>
                <small class="text-muted">${pret.adherent.carteNumero}</small>
            </td>
            <td>
                <strong>${pret.livre.titre}</strong><br>
                <small class="text-muted">par ${pret.livre.auteur}</small><br>
                <small class="text-muted">ISBN: ${pret.livre.isbn}</small>
            </td>
            <td>
                <c:set var="dp" value="${pret.datePret.toString()}" />
                ${fn:substring(dp, 8, 10)}/${fn:substring(dp, 5, 7)}/${fn:substring(dp, 0, 4)} ${fn:substring(dp, 11, 16)}
            </td>
            <td>
                <c:set var="df" value="${pret.dateFin.toString()}" />
                ${fn:substring(df, 8, 10)}/${fn:substring(df, 5, 7)}/${fn:substring(df, 0, 4)}
            </td>
            <td>
                <c:choose>
                    <c:when test="${pret.dateFin < now}">
                        <span class="badge badge-danger">
                            <i class="fas fa-exclamation-triangle"></i> En retard
                        </span>
                    </c:when>
                    <c:when test="${pret.dateFin <= threeDaysFromNow}">
                        <span class="badge badge-warning">
                            <i class="fas fa-clock"></i> Expire bientôt
                        </span>
                    </c:when>
                    <c:otherwise>
                        <span class="badge badge-success">
                            <i class="fas fa-check"></i> Normal
                        </span>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:set var="nbProlongements" value="${pret.prolongements.size()}" />
                <c:choose>
                    <c:when test="${nbProlongements == 0}">
                        <span class="badge bg-secondary">Aucun</span>
                    </c:when>
                    <c:otherwise>
                        <span class="badge bg-info">${nbProlongements}</span>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <div class="btn-group" role="group">
                    <c:choose>
                        <c:when test="${pret.prolongements.size() < 2}">
                            <button type="button" class="btn btn-warning btn-sm" 
                                    onclick="ouvrirModalProlongation(${pret.id}, '${pret.adherent.nom} ${pret.adherent.prenom}', '${pret.livre.titre}')">
                                <i class="fas fa-clock"></i> Prolonger
                            </button>
                        </c:when>
                        <c:otherwise>
                            <button type="button" class="btn btn-secondary btn-sm" disabled>
                                <i class="fas fa-ban"></i> Limite atteinte
                            </button>
                        </c:otherwise>
                    </c:choose>
                    
                    <a href="/pret/details/${pret.id}" class="btn btn-info btn-sm">
                        <i class="fas fa-eye"></i> Détails
                    </a>
                </div>
            </td>
        </tr>
    </c:forEach> -->

    <td>
        <table class="table table-borderless">
            <tr>
                <td>
                    <c:choose>
                        <c:when test="${pret.prolongements.size() < 2}">
                            <button type="button" class="btn btn-warning btn-sm" 
                                    onclick="ouvrirModalProlongation(${pret.id}, '${pret.adherent.nom} ${pret.adherent.prenom}', '${pret.livre.titre}')">
                                <i class="fas fa-clock"></i> Prolonger
                            </button>
                        </c:when>
                        <c:otherwise>
                            <button type="button" class="btn btn-secondary btn-sm" disabled>
                                <i class="fas fa-ban"></i> Limite atteinte
                            </button>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>
                    <a href="/pret/details/${pret.id}" class="btn btn-info btn-sm">
                        <i class="fas fa-eye"></i> Détails
                    </a>
                </td>
            </tr>
            <tr>
                <td>
                    <form method="post" action="/pret/rendre/${pret.id}" onsubmit="return confirm('Confirmer le retour du livre ?');">
                        <button type="submit" class="btn btn-success btn-sm">
                            <i class="fas fa-undo"></i> Rendre
                        </button>
                    </form>
                </td>
            </tr>
        </table>
    </td>
    
</tbody>

</html>