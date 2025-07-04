package repository;

import entities.Pret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PretRepository extends JpaRepository<Pret, Integer> {
    List<Pret> findByAdherentId(int adherentId);
    int countByAdherentIdAndLivreId(int adherentId, int livreId);

    @Query("SELECT p FROM Pret p WHERE p.livre.id = :livreId AND " +
           "(:dateDebut <= p.dateFin AND :dateFin >= p.datePret)")
    List<Pret> findOverlappingPrets(@Param("livreId") int livreId,@Param("dateDebut") LocalDate dateDebut, @Param("dateFin") LocalDate dateFin);

    int countByLivreIdAndDateFinAfter(int livreId, LocalDate date);

    @Query("SELECT l.nbTotal FROM Livre l WHERE l.id = :livreId")
    int getTotalStockByLivreId(@Param("livreId") int livreId);
}
