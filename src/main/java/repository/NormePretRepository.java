package repository;

import entities.NormePret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NormePretRepository extends JpaRepository<NormePret, Integer> {
}
