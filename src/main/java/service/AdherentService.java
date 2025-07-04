package service;

import entities.Adherent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AdherentRepository;

import java.util.List;

@Service
public class AdherentService {

    @Autowired
    private AdherentRepository adherentRepository;

    public List<Adherent> getAll() {
        return adherentRepository.findAll();
    }

    public Adherent getById(int id) {
        return adherentRepository.findById(id).orElse(null);
    }

    public Adherent save(Adherent adherent) {
        return adherentRepository.save(adherent);
    }

    public void deleteById(int id) {
        adherentRepository.deleteById(id);
    }

    public Adherent findById(int id) {
        return adherentRepository.findById(id).orElse(null);
    }
}
