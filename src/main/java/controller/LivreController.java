package controller;

import java.util.List;
import entities.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import service.LivreService;

@Controller
public class LivreController {

    @Autowired
    private LivreService livreService;

    @GetMapping("/")
    public String afficherListeLivres(Model model) {
        List<Livre> livres = livreService.getAllLivres();
        model.addAttribute("livres", livres);
        return "liste-livres";  // Ce nom correspond à la JSP : liste-livres.jsp
    }
}
