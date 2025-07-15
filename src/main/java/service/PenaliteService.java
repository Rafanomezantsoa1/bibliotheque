package service;

import entities.Penalite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PenaliteRepository;

import java.util.List;

@Service
public class PenaliteService {

    @Autowired
    private PenaliteRepository penaliteRepository;

    public void save(Penalite penalite) {
        penaliteRepository.save(penalite);
    }

    public List<Penalite> getAll() {
        return penaliteRepository.findAll();
    }

    public Penalite getById(Integer id) {
        return penaliteRepository.findById(id).orElse(null);
    }

    public void deleteById(Integer id) {
        penaliteRepository.deleteById(id);
    }
}
