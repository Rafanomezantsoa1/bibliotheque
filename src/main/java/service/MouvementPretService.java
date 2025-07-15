package service;

import entities.EtatPret;
import entities.MouvementPret;
import entities.Pret;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.MouvementPretRepository;

import java.util.List;
import java.util.stream.Collectors;

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

    public boolean existsByPretAndEtat(Pret pret, EtatPret etatPret) {
        return mouvementPretRepository.existsByPretAndEtatPret(pret, etatPret);
    }

    public List<MouvementPret> findByPret(Pret pret) {
        return mouvementPretRepository.findByPret(pret);
    }

    public List<MouvementPret> findDerniersMouvementsParEtat(EtatPret etat1, EtatPret etat2) {
        return mouvementPretRepository.findByEtatPretIn(List.of(etat1, etat2));
    }

    public List<Pret> getPretsEnCoursOuProlonges(EtatPret etat1, EtatPret etat2) {
        List<MouvementPret> mouvements = mouvementPretRepository.findByEtatPretIn(List.of(etat1, etat2));
        return mouvements.stream()
                        .map(MouvementPret::getPret)
                        .distinct()
                        .collect(Collectors.toList());
    }

    public List<MouvementPret> getMouvementsByEtats(List<EtatPret> etats) {
        return mouvementPretRepository.findByEtatPretIn(etats);
    }
    

}
