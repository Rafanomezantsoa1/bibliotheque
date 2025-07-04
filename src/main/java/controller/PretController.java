package controller;

import entities.*;
import service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Controller
public class PretController {

    @Autowired
    private PretService pretService;

    @Autowired
    private LivreService livreService;

    @Autowired
    private TypePretService typePretService;

    @Autowired
    private AdherentService adherentService;

    @Autowired
    private NormePretService normePretService;

    @Autowired
    private EtatPretService etatPretService;

    @Autowired
    private MouvementPretService mouvementPretService;

    @GetMapping("/form-pret")
    public String afficherFormulairePret(Model model) {
        model.addAttribute("livres", livreService.getAllLivres());
        model.addAttribute("types", typePretService.getAll());
        return "form-pret";
    }


    // @PostMapping("/insert-pret")
    // public String insertPret(
    //     @RequestParam("livre") int livreId,
    //     @RequestParam("type") int typeId,
    //     @RequestParam("nbr") int nbrExemplaires,
    //     @RequestParam("id_adherent") int adherentId,
    //     @RequestParam("date_debut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
    //     @RequestParam("date_fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin,
    //     Model model) {

    //     if (dateFin.isBefore(dateDebut)) {
    //         model.addAttribute("error", "La date de fin doit être après la date de début.");
    //         return afficherFormulairePret(model);
    //     }

    //     long jours = ChronoUnit.DAYS.between(dateDebut, dateFin);

    //     Livre livre = livreService.getLivreById(livreId);
    //     TypePret typePret = typePretService.getById(typeId);
    //     Adherent adherent = adherentService.getById(adherentId);

    //     if (livre == null || typePret == null || adherent == null) {
    //         model.addAttribute("error", "Livre, type ou adhérent invalide.");
    //         return afficherFormulairePret(model);
    //     }

    //     if (!adherent.getStatut().getEtat().equalsIgnoreCase("actif")) {
    //         model.addAttribute("error", "L'adhérent n'est pas actif.");
    //         return afficherFormulairePret(model);
    //     }

    //     // 🔍 Rechercher la norme applicable
    //     NormePret norme = normePretService.findByLivreAndProfil(livre.getId(), adherent.getProfil().getId());

    //     if (norme == null) {
    //         model.addAttribute("error", "Aucune norme trouvée pour ce livre et ce profil.");
    //         return afficherFormulairePret(model);
    //     }

    //     if (norme == null) {
    //         model.addAttribute("error", "Aucune norme définie pour ce livre et profil.");
    //         return afficherFormulairePret(model);
    //     }
        
    //     if (norme.getDuree() == null) {
    //         model.addAttribute("error", "La durée de prêt pour cette norme est non définie.");
    //         return afficherFormulairePret(model);
    //     }

    //     if (jours >  norme.getDuree()) {
    //         model.addAttribute("error", "La durée dépasse la norme autorisée de " + norme.getDuree() + " jours.");
    //         return afficherFormulairePret(model);
    //     }

    //     // 🔢 Compter les prêts en cours de cet adhérent pour ce livre
    //     int nbPretsActuels = pretService.countByAdherentAndLivre(adherentId, livreId);

    //     if (nbPretsActuels + nbrExemplaires > norme.getNbMax()) {
    //         model.addAttribute("error", "Nombre d'exemplaires dépasse la norme autorisée (" + norme.getNbMax() + ").");
    //         return afficherFormulairePret(model);
    //     }

    //     // ✅ Tous les contrôles sont passés, on enregistre les prêts
    //     for (int i = 0; i < nbrExemplaires; i++) {
    //         Pret pret = new Pret();
    //         pret.setLivre(livre);
    //         pret.setTypePret(typePret);
    //         pret.setAdherent(adherent);
    //         pret.setDatePret(LocalDateTime.now());
    //         pret.setDateFin(dateFin);
    //         pretService.save(pret);
    //     }

    //     model.addAttribute("message", "Prêt(s) inséré(s) avec succès.");
    //     return afficherFormulairePret(model);
    // }

    @PostMapping("/insert-pret")
public String insertPret(@RequestParam("livre") int livreId,
                         @RequestParam("type") int typePretId,
                         @RequestParam("nbr") int nbr,
                         @RequestParam("id_adherent") int idAdherent,
                         @RequestParam("date_debut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
                         @RequestParam("date_fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin,
                         Model model) {

    // 1. Récupérer l’adhérent
    Adherent adherent = adherentService.findById(idAdherent);
    if (adherent == null) {
        model.addAttribute("errorMessage", "Adhérent introuvable.");
        return "errorPage";
    }

    // 2. Récupérer le profil associé à l’adhérent
    Profil profil = adherent.getProfil();
    if (profil == null) {
        model.addAttribute("errorMessage", "Profil de l'adhérent introuvable.");
        return "errorPage";
    }

    // 3. Chercher la norme de prêt correspondante au livre et profil
    NormePret normePret = normePretService.findByLivreIdAndProfilId(livreId, profil.getId());

    if (normePret == null || normePret.getDuree() == null) {
        model.addAttribute("errorMessage", "La durée de prêt n'est pas définie pour ce livre et ce profil.");
        return "errorPage";
    }

    // 4. Création du prêt
    Livre livre = livreService.getLivreById(livreId);
    TypePret typePret = typePretService.findById(typePretId);

    Pret pret = new Pret();
    pret.setAdherent(adherent);
    pret.setLivre(livre);
    pret.setTypePret(typePret);
    pret.setDatePret(dateDebut.atStartOfDay());
    pret.setDateFin(dateFin);

    pretService.save(pret);

    // 5. Redirection ou confirmation
    return "redirect:/pret/success"; // à adapter à ton flux
}



}
