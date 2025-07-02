package repository;

import entities.GenreLivre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreLivreRepository extends JpaRepository<GenreLivre, Integer> {
}

