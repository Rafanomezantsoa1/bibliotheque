package controller;

import entities.Genre;
import service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/genres")
    public String getListGenre(Model model) {
        List<Genre> genres = genreService.getAllGenres();
        model.addAttribute("listGenres", genres);
        return "form-livre"; 
    }


}
