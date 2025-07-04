package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entities.TypePret;
import repository.TypePretRepository;

@Service
public class TypePretService {
    @Autowired
    private TypePretRepository typePretRepository;

    public List<TypePret> getAll() {
        return typePretRepository.findAll();
    }

    public TypePret save(TypePret typePret) {
        return typePretRepository.save(typePret);
    }

    public TypePret getById(int id) {
        return typePretRepository.findById(id).orElse(null);
    }

    public void deleteById(int id) {
        typePretRepository.deleteById(id);
    }

    public TypePret findById(int id) {
        return typePretRepository.findById(id).orElse(null);
    }
}
