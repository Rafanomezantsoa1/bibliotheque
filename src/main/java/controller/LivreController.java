// package controller;

// import java.util.List;
// import entities.Livre;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import service.LivreService;

// @Controller
// public class LivreController {

//     @Autowired
//     private LivreService livreService;

//     @GetMapping("/")
//     public String afficherListeLivres(Model model) {
//         List<Livre> livres = livreService.getAllLivres();
//         model.addAttribute("livres", livres);
//         return "liste-livres";  // Ce nom correspond à la JSP : liste-livres.jsp
//     }
// }

package controller;

import entities.Genre;
import entities.Livre;
import entities.GenreLivre;
import service.GenreService;
import service.LivreService;
import service.GenreLivreService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LivreController {

    @Autowired
    private LivreService livreService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private GenreLivreService genreLivreService;

    @GetMapping("/")
    public String handleFormRecherche(Model model, HttpServletRequest request) {
        return "form-recherche-livre";
    }

    @GetMapping("/form-livre")
    public String handleFormLivre(Model model, HttpServletRequest request) {
        if (request.getParameter("idLivre") != null) {
            Livre livre = livreService.getLivreById(Integer.parseInt(request.getParameter("idLivre")));
            model.addAttribute("idLivre", livre.getId());
            model.addAttribute("livre", livre);
        }

        List<Genre> listGenres = genreService.getAllGenres();
        model.addAttribute("listGenres", listGenres);
        return "form-livre";
    }

    @PostMapping("/livre")
    public String handleCreateOrUpdateLivre(HttpServletRequest request, Model model) {
        String titre = request.getParameter("titre");
        String auteur = request.getParameter("auteur");
        String datePublication = request.getParameter("datePublication"); // Assumes format: yyyy-MM-dd
        String isbn = request.getParameter("isbn");
        int nbTotal = Integer.parseInt(request.getParameter("nbTotal"));
        String[] genreIds = request.getParameterValues("genres");

        Livre livre = new Livre();
        livre.setTitre(titre);
        livre.setAuteur(auteur);
        livre.setIsbn(isbn);
        livre.setNbTotal(nbTotal);
        livre.setDatePublication(LocalDate.parse(datePublication));  // assumes valid date input

        if (request.getParameter("idLivre") != null) {
            livre.setId(Integer.parseInt(request.getParameter("idLivre")));
            model.addAttribute("message", "Livre modifié avec succès !");
        } else {
            model.addAttribute("message", "Livre inséré avec succès !");
        }

        livreService.saveLivre(livre);

        if (genreIds != null) {
            for (String genreId : genreIds) {
                Genre genre = genreService.getGenreById(Integer.parseInt(genreId));
                GenreLivre gl = new GenreLivre();
                gl.setLivre(livre);
                gl.setGenre(genre);
                genreLivreService.saveGenreLivre(gl);
            }
        }

        List<Genre> listGenres = genreService.getAllGenres();
        model.addAttribute("listGenres", listGenres);
        return "form-livre";
    }

    @GetMapping("/list-livre")
    public String handleListLivre(Model model) {
        List<Livre> listLivres = livreService.getAllLivres();
        model.addAttribute("listLivres", listLivres);
        return "liste-livre";
    }

    @GetMapping("/detail-livre")
    public String handleDetailLivre(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("idLivre"));
        Livre livre = livreService.getLivreById(id);
        model.addAttribute("livre", livre);
        return "detail-livre";
    }

    @GetMapping("/delete-livre")
    public String handleDeleteLivre(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("idLivre"));
        livreService.deleteLivreById(id);
        model.addAttribute("message", "Livre supprimé avec succès !");
        model.addAttribute("listLivres", livreService.getAllLivres());
        return "list-livre";
    }

    @GetMapping("/recherche")
    public String rechercherLivre(@RequestParam("genre_recherche") String genre,
                                @RequestParam("a_chercher") String valeur,
                                Model model) {
        List<Livre> resultats;

        switch (genre) {
            case "titre":
                resultats = livreService.rechercherParTitre(valeur);
                break;
            case "auteur":
                resultats = livreService.rechercherParAuteur(valeur);
                break;
            case "isbn":
                resultats = livreService.rechercherParIsbn(valeur);
                break;
            default:
                resultats = List.of(); // liste vide
        }

        model.addAttribute("resultats", resultats);
        return "resultats-recherche"; 
    }

    @GetMapping("/classification")
    public String filterByGenre(@RequestParam(value = "genre", required = false) Integer genreId, Model model) {
        List<Genre> genres = genreService.getAllGenres();
        model.addAttribute("genres", genres); // envoie les genres à la JSP

        if (genreId != null) {
            List<Livre> livres = livreService.getLivresByGenreId(genreId);
            model.addAttribute("livres", livres); // envoie les livres filtrés
            model.addAttribute("selectedGenreId", genreId); // pour pré-cocher dans le select
        }

        return "resultat-classification"; // nom du fichier JSP
    }

}
