package repository;

import entities.Pret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PretRepository extends JpaRepository<Pret, Integer> {
    List<Pret> findByAdherentId(int adherentId);
}
