package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import entities.GenreLivre;
import repository.GenreLivreRepository;

public class GenreLivreService {
    private GenreLivreRepository genreLivreRepository;

    @Autowired
    public void setGenreLivreRepository(GenreLivreRepository genreLivreRepository) {
        this.genreLivreRepository = genreLivreRepository;
    }

    public GenreLivre saveGenreLivre(GenreLivre Genre) {
        return genreLivreRepository.save(Genre);
    }

    public List<GenreLivre> getAllGenreLivres() {
        return genreLivreRepository.findAll();
    }

    public GenreLivre getGenreLivreById(int id) {
        return genreLivreRepository.findById(id).orElse(null);
    }
}

