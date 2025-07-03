package service;

import entities.Pret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PretRepository;

import java.util.List;

@Service
public class PretService {

    @Autowired
    private PretRepository pretRepository;

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
}
