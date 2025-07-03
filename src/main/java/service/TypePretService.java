package service;

import entities.TypePret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.TypePretRepository;

import java.util.List;

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
}
