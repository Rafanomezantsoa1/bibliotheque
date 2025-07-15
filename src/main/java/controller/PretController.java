package controller;

import java.util.ArrayList;
import java.util.List;
import entities.*;
import service.*;
import dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    private ProlongementPretService prolongementPretService;

    @Autowired
    private PenaliteService penaliteService;


    @GetMapping("/api/livres")
    @ResponseBody
    public List<LivreDTO> getLivres() {
        List<Livre> livres = livreService.getAllLivres();
        return livres.stream()
                .map(LivreDTO::fromEntity)
                .toList();
    }

    @GetMapping("/form-pret")
    public String afficherFormulairePret(Model model) {
        model.addAttribute("livres", livreService.getAllLivres());
        model.addAttribute("types", typePretService.getAll());
        return "form-pret";
    }

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

        // 4. Préparation des objets liés
        Livre livre = livreService.getLivreById(livreId);
        TypePret typePret = typePretService.findById(typePretId);
        EtatPret etatEnCours = etatPretService.getById(1); // état "en cours"

        // 5. Boucle sur le nombre de prêts à faire
        for (int i = 0; i < nbr; i++) {
            Pret pret = new Pret();
            pret.setAdherent(adherent);
            pret.setLivre(livre);
            pret.setTypePret(typePret);
            pret.setDatePret(dateDebut.atStartOfDay());
            pret.setDateFin(dateFin);
            pretService.save(pret);

            // Mouvement associé (état "en cours")
            MouvementPret mouvementPret = new MouvementPret();
            mouvementPret.setPret(pret);
            mouvementPret.setEtatPret(etatEnCours);
            mouvementPret.setDateRetour(null);
            mouvementPretService.save(mouvementPret);
        }

        // 6. Redirection ou confirmation
        model.addAttribute("message", "Prêt(s) inséré(s) avec succès.");
        return "redirect:/pret/success"; // ou une autre page
    }

    @PostMapping("/retourner-pret")
    public String retournerPret(@RequestParam("id_pret") Integer idPret, Model model) {
        // 1. Récupérer le prêt
        Pret pret = pretService.getById(idPret);
        if (pret == null) {
            model.addAttribute("errorMessage", "Prêt introuvable.");
            return "errorPage";
        }

        // 2. Vérifier s'il est déjà retourné
        EtatPret etatRetourne = etatPretService.getByEtat("retourne");
        boolean dejaRetourne = mouvementPretService.existsByPretAndEtat(pret, etatRetourne);
        if (dejaRetourne) {
            model.addAttribute("errorMessage", "Ce prêt a déjà été retourné.");
            return "errorPage";
        }

        // 3. Enregistrer le retour dans mouvement_pret
        MouvementPret retour = new MouvementPret();
        retour.setPret(pret);
        retour.setEtatPret(etatRetourne);
        retour.setDateRetour(LocalDate.now());
        mouvementPretService.save(retour);

        // 4. Vérifier si retour en retard
        if (LocalDate.now().isAfter(pret.getDateFin())) {
            // Il est en retard → créer une pénalité
            Penalite penalite = new Penalite();
            penalite.setPret(pret);
            penalite.setAdherent(pret.getAdherent());
            penalite.setDateDebut(LocalDate.now());
            penalite.setDateFin(LocalDate.now().plusDays(7)); // Exemple : 7 jours de pénalité

            penaliteService.save(penalite);

            model.addAttribute("message", "Prêt retourné en retard. Une pénalité a été appliquée.");
        } else {
            model.addAttribute("message", "Prêt retourné à temps. Aucun souci !");
        }

        return "confirmation-retour"; // à adapter selon ton interface
    }

    // @GetMapping("/pret-en-cours")
    // public String afficherPretsEnCours(Model model) {
    // // Récupérer la liste des prêts en cours ou prolongés via ton service
    // List<Pret> pretsEnCours = pretService.getAll();

    // model.addAttribute("pretsEnCours", pretsEnCours);

    // // Retourner la page JSP (sans extension) qui affichera la liste
    // return "liste-prets";
    // }

    // @GetMapping("/pret-en-cours")
    // public String afficherPretsEnCoursOuProlonges(Model model) {
    // EtatPret etatEnCours = etatPretService.getByEtat("en cours");
    // EtatPret etatProlonge = etatPretService.getByEtat("prolongé");

    // System.out.println("etatEnCours: " + (etatEnCours != null ?
    // etatEnCours.getEtat() : "null"));
    // System.out.println("etatProlonge: " + (etatProlonge != null ?
    // etatProlonge.getEtat() : "null"));

    // if (etatEnCours == null || etatProlonge == null) {
    // model.addAttribute("errorMessage", "Les états requis n'existent pas dans la
    // base.");
    // return "errorPage";
    // }

    // List<Pret> pretsActifs =
    // mouvementPretService.getPretsEnCoursOuProlonges(etatEnCours, etatProlonge);
    // model.addAttribute("prets", pretsActifs);
    // return "liste-prets";
    // }

    // modif

    // @GetMapping("/en-cours")
    // public String afficherPretsEnCours(Model model) {
    // List<Pret> pretsEnCours = pretService.getPretsEnCours();
    // model.addAttribute("pretsEnCours", pretsEnCours);
    // return "prets-en-cours";
    // }

    @GetMapping("/en-cours")
    public String afficherPretsEnCours(Model model) {
        List<Pret> pretsEnCours = pretService.getPretsEnCours();

        // Initialiser les prolongements (évite LazyInitializationException)
        for (Pret pret : pretsEnCours) {
            pret.setProlongements(new ArrayList<>(prolongementPretService.getByPret(pret.getId())));
        }

        model.addAttribute("pretsEnCours", pretsEnCours);
        model.addAttribute("now", LocalDate.now());
        model.addAttribute("threeDaysFromNow", LocalDate.now().plusDays(3));
        return "prets-en-cours";
    }

    // Prolonger un prêt
    @PostMapping("/prolonger-pret")
    public String prolongerPret(
            @RequestParam("id_pret") Integer idPret,
            @RequestParam("jours") int jours,
            Model model,
            RedirectAttributes redirectAttributes) {

        try {
            // 1. Vérifications de base
            if (jours <= 0) {
                redirectAttributes.addFlashAttribute("errorMessage",
                        "La durée de prolongation doit être positive.");
                return "redirect:/pret/en-cours";
            }

            // 2. Récupérer le prêt
            Pret pret = pretService.getById(idPret);
            if (pret == null) {
                redirectAttributes.addFlashAttribute("errorMessage",
                        "Prêt introuvable.");
                return "redirect:/pret/en-cours";
            }

            // 3. Vérifier si le prêt est en cours
            if (!"en_cours".equals(pret.getEtatPret().getEtat())) {
                redirectAttributes.addFlashAttribute("errorMessage",
                        "Ce prêt ne peut pas être prolongé car il n'est pas en cours.");
                return "redirect:/pret/en-cours";
            }

            // 4. Vérifier s'il n'y a pas déjà trop de prolongements
            int nombreProlongements = prolongementPretService.countByPret(pret);
            if (nombreProlongements >= 2) { // Limite à 2 prolongements par exemple
                redirectAttributes.addFlashAttribute("errorMessage",
                        "Ce prêt a déjà été prolongé le maximum de fois autorisé.");
                return "redirect:/pret/en-cours";
            }

            // 5. Calculer la nouvelle date de fin
            LocalDate nouvelleDateFin = pret.getDateFin().plusDays(jours);
            pret.setDateFin(nouvelleDateFin);

            // 6. Mettre à jour l'état du prêt à "prolongé"
            EtatPret etatProlonge = etatPretService.getByEtat("prolongé");
            if (etatProlonge == null) {
                // Créer l'état s'il n'existe pas
                etatProlonge = new EtatPret();
                etatProlonge.setEtat("prolongé");
                etatPretService.save(etatProlonge);
            }

            // 7. Créer un mouvement de prêt pour tracer la prolongation
            MouvementPret mouvement = new MouvementPret();
            mouvement.setPret(pret);
            mouvement.setEtatPret(etatProlonge);
            mouvement.setDateRetour(null); // Pas encore retourné
            mouvementPretService.save(mouvement);

            // 8. Sauvegarder le prêt mis à jour
            pretService.save(pret);

            // 9. Créer un enregistrement de prolongement
            ProlongementPret prolongement = new ProlongementPret();
            prolongement.setDureeJour(jours);
            prolongement.setPret(pret);
            prolongementPretService.save(prolongement);

            // 10. Message de succès
            redirectAttributes.addFlashAttribute("successMessage",
                    "Prêt prolongé de " + jours + " jour(s). Nouvelle date de fin : " +
                            nouvelleDateFin.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Erreur lors de la prolongation du prêt : " + e.getMessage());
        }

        return "redirect:/pret-en-cours";
    }

    // Afficher les détails d'un prêt
    @GetMapping("/details/{id}")
    public String afficherDetailsPret(@PathVariable Integer id, Model model) {
        Pret pret = pretService.getById(id);
        if (pret == null) {
            model.addAttribute("errorMessage", "Prêt introuvable.");
            return "errorPage";
        }

        List<ProlongementPret> prolongements = prolongementPretService.getByPret(pret);
        model.addAttribute("pret", pret);
        model.addAttribute("prolongements", prolongements);
        return "details-pret";
    }

}
