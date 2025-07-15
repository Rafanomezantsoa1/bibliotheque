package repository;

import entities.EtatPret;
import entities.MouvementPret;
import entities.Pret;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MouvementPretRepository extends JpaRepository<MouvementPret, Integer> {
    // Tu peux ajouter des méthodes personnalisées plus tard

    boolean existsByPretAndEtatPret(Pret pret, EtatPret etatPret);
    List<MouvementPret> findByPret(Pret pret);

    List<MouvementPret> findByEtatPretIn(List<EtatPret> etats);

}
