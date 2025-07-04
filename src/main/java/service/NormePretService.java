package service;

import entities.NormePret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.NormePretRepository;

import java.util.List;

@Service
public class NormePretService {

    @Autowired
    private NormePretRepository normePretRepository;

    public List<NormePret> getAll() {
        return normePretRepository.findAll();
    }

    public NormePret save(NormePret normePret) {
        return normePretRepository.save(normePret);
    }

    public NormePret getById(int id) {
        return normePretRepository.findById(id).orElse(null);
    }

    public void deleteById(int id) {
        normePretRepository.deleteById(id);
    }

    public NormePret findByLivreAndProfil(int livreId, int profilId) {
        return normePretRepository.findByLivreIdAndProfilId(livreId, profilId);
    }

    public NormePret getByLivreIdAndProfilId(int livreId, int profilId) {
        return normePretRepository.findByLivreIdAndProfilId(livreId, profilId);
    }

    public NormePret findByLivreIdAndProfilId(int livreId, int profilId) {
        return normePretRepository.findByLivreIdAndProfilId(livreId, profilId);
    }
}
