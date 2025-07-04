package service;

import entities.Pret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PretRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class PretService {

    private PretRepository pretRepository;

    @Autowired
    public PretService(PretRepository pretRepository) {
        this.pretRepository = pretRepository;
    }

    public List<Pret> getAll() {
        return pretRepository.findAll();
    }

    public Pret save(Pret pret) {
        return pretRepository.save(pret);
    }

    public Pret getById(int id) {
        return pretRepository.findById(id).orElse(null);
    }

    public void deleteById(int id) {
        pretRepository.deleteById(id);
    }

    public List<Pret> getByAdherent(int adherentId) {
        return pretRepository.findByAdherentId(adherentId);
    }

    public PretRepository getPretRepository() {
        return pretRepository;
    }

    public int countByAdherentAndLivre(int adherentId, int livreId) {
        return pretRepository.countByAdherentIdAndLivreId(adherentId, livreId);
    }

    // public Pret saveAndReturn(Pret pret) {
    //     return pretRepository.save(pret);
    // }
    
    // public boolean isLivreDisponible(int livreId, LocalDate dateDebut, LocalDate dateFin) {
    //     int nbPretsEnCours = pretRepository.countByLivreIdAndDateFinAfter(livreId, dateDebut);
    //     return nbPretsEnCours < pretRepository.getTotalStockByLivreId(livreId);
    // }

    public Pret saveAndReturn(Pret pret) {
        return pretRepository.save(pret);
    }
    
    public boolean isLivreDisponible(int livreId, LocalDate debut, LocalDate fin) {
        return true;
    }
    
}
