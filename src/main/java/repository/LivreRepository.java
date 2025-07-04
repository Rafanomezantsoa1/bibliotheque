package repository;

import entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Integer> {
     List<Livre> findByTitreContainingIgnoreCase(String titre);
    List<Livre> findByAuteurContainingIgnoreCase(String auteur);
    List<Livre> findByIsbnContainingIgnoreCase(String isbn);

    @Query("SELECT l FROM Livre l JOIN l.genres g WHERE g.id_genre = :genreId")
    List<Livre> findByGenreId(@Param("genreId") Integer genreId);

    
}
