package repository;

import entities.EtatPret;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtatPretRepository extends JpaRepository<EtatPret, Integer> {
    EtatPret findByEtat(String etat);  
}
