package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entities.Livre;
import repository.LivreRepository;

@Service
public class LivreService {
    private LivreRepository livreRepository;

    @Autowired
    public void setLivreRepository(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    public Livre saveLivre(Livre livre) {
        return livreRepository.save(livre);
    }

    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    public Livre getLivreById(int id) {
        return livreRepository.findById(id).orElse(null);
    }

    public void deleteLivreById(int id) {
        livreRepository.deleteById(id);
    }

    public List<Livre> rechercherParTitre(String titre) {
        return livreRepository.findByTitreContainingIgnoreCase(titre);
    }
    
    public List<Livre> rechercherParAuteur(String auteur) {
        return livreRepository.findByAuteurContainingIgnoreCase(auteur);
    }
    
    public List<Livre> rechercherParIsbn(String isbn) {
        return livreRepository.findByIsbnContainingIgnoreCase(isbn);
    }
    
    public List<Livre> getLivresByGenreId(Integer genreId) {
        return livreRepository.findByGenreId(genreId);
    }
}
