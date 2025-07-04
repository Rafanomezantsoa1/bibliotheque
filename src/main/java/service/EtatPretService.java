package service;

import entities.EtatPret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.EtatPretRepository;

import java.util.List;

@Service
public class EtatPretService {

    @Autowired
    private EtatPretRepository etatPretRepository;

    public EtatPret getByEtat(String etat) {
        return etatPretRepository.findByEtat(etat);
    }

    public List<EtatPret> getAll() {
        return etatPretRepository.findAll();
    }

    public void save(EtatPret etatPret) {
        etatPretRepository.save(etatPret);
    }
}
