package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import entities.Genre;
import repository.GenreRepository;

public class GenreService {
    private GenreRepository genreRepository;

    @Autowired
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre saveGenre(Genre Genre) {
        return genreRepository.save(Genre);
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre getGenreById(int id) {
        return genreRepository.findById(id).orElse(null);
    }
}
