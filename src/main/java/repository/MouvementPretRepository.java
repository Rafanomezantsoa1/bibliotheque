package repository;

import entities.MouvementPret;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MouvementPretRepository extends JpaRepository<MouvementPret, Integer> {
    // Tu peux ajouter des méthodes personnalisées plus tard
}
