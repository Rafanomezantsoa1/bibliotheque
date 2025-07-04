package service;

import entities.MouvementPret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.MouvementPretRepository;

import java.util.List;

@Service
public class MouvementPretService {

    @Autowired
    private MouvementPretRepository mouvementPretRepository;

    public void save(MouvementPret mouvementPret) {
        mouvementPretRepository.save(mouvementPret);
    }

    public List<MouvementPret> getAll() {
        return mouvementPretRepository.findAll();
    }

    public MouvementPret getById(int id) {
        return mouvementPretRepository.findById(id).orElse(null);
    }
}
