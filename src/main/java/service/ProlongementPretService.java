package service;

import entities.Pret;
import entities.ProlongementPret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProlongementPretRepository;

import java.util.List;

@Service
public class ProlongementPretService {

    @Autowired
    private ProlongementPretRepository prolongementPretRepository;

    public List<ProlongementPret> getAll() {
        return prolongementPretRepository.findAll();
    }

    public ProlongementPret save(ProlongementPret prolongementPret) {
        return prolongementPretRepository.save(prolongementPret);
    }

    public ProlongementPret getById(int id) {
        return prolongementPretRepository.findById(id).orElse(null);
    }

    public void deleteById(int id) {
        prolongementPretRepository.deleteById(id);
    }

    public List<ProlongementPret> getByPret(int pretId) {
        return prolongementPretRepository.findByPretId(pretId);
    }

    public int countByPret(Pret pret) {
    return prolongementPretRepository.countByPret(pret);
}

public List<ProlongementPret> getByPret(Pret pret) {
    return prolongementPretRepository.findByPret(pret);
}

}
