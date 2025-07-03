package repository;

import entities.ProlongementPret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProlongementPretRepository extends JpaRepository<ProlongementPret, Integer> {
    List<ProlongementPret> findByPretId(int pretId);
}
