package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import entities.Livre;
import repository.LivreRepository;

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
}
