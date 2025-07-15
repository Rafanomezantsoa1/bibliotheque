package repository;

import entities.Pret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

// @Repository
// public interface PretRepository extends JpaRepository<Pret, Integer> {
//     List<Pret> findByAdherentId(int adherentId);
//     int countByAdherentIdAndLivreId(int adherentId, int livreId);

//     @Query("SELECT p FROM Pret p WHERE p.livre.id = :livreId AND " +
//            "(:dateDebut <= p.dateFin AND :dateFin >= p.datePret)")
//     List<Pret> findOverlappingPrets(@Param("livreId") int livreId,@Param("dateDebut") LocalDate dateDebut, @Param("dateFin") LocalDate dateFin);

//     int countByLivreIdAndDateFinAfter(int livreId, LocalDate date);

//     @Query("SELECT l.nbTotal FROM Livre l WHERE l.id = :livreId")
//     int getTotalStockByLivreId(@Param("livreId") int livreId);
// }

@Repository
public interface PretRepository extends JpaRepository<Pret, Integer> {

    List<Pret> findByAdherentId(int adherentId);

    int countByAdherentIdAndLivreId(int adherentId, int livreId);

    @Query("SELECT p FROM Pret p WHERE p.livre.id = :livreId AND " +
            "(:dateDebut <= p.dateFin AND :dateFin >= p.datePret)")
    List<Pret> findOverlappingPrets(@Param("livreId") int livreId,
            @Param("dateDebut") LocalDate dateDebut,
            @Param("dateFin") LocalDate dateFin);

    int countByLivreIdAndDateFinAfter(int livreId, LocalDate date);

    @Query("SELECT l.nbTotal FROM Livre l WHERE l.id = :livreId")
    int getTotalStockByLivreId(@Param("livreId") int livreId);

    List<Pret> findByEtatPret_EtatIn(List<String> etats);

    List<Pret> findByDateFinBeforeAndEtatPret_EtatIn(LocalDate date, List<String> etats);

    List<Pret> findByDateFinBetweenAndEtatPret_EtatIn(LocalDate dateDebut, LocalDate dateFin, List<String> etats);

    List<Pret> findByAdherentIdAndEtatPret_EtatIn(int adherentId, List<String> etats);

    List<Pret> findByLivreIdAndEtatPret_EtatIn(int livreId, List<String> etats);

    int countByLivreIdAndDateFinAfterAndEtatPret_EtatIn(int livreId, LocalDate date, List<String> etats);

    List<Pret> findByAdherentIdOrderByDatePretDesc(int adherentId);

    List<Pret> findByLivreIdOrderByDatePretDesc(int livreId);

    int countByAdherentIdAndEtatPret_EtatIn(int adherentId, List<String> etats);

    List<Pret> findByDatePretBetween(LocalDate dateDebut, LocalDate dateFin);

    List<Pret> findByTypePret_Type(String type);

    @Query("SELECT COUNT(p) FROM Pret p WHERE p.etatPret.etat IN :etats AND p.dateFin < :date")
    int countPretsEnRetard(@Param("etats") List<String> etats, @Param("date") LocalDate date);

    @Query("SELECT COUNT(p) FROM Pret p WHERE p.etatPret.etat IN :etats AND p.dateFin BETWEEN :dateDebut AND :dateFin")
    int countPretsExpirantBientot(@Param("etats") List<String> etats,
            @Param("dateDebut") LocalDate dateDebut,
            @Param("dateFin") LocalDate dateFin);

    @Query("SELECT DISTINCT p FROM Pret p JOIN p.penalites pen WHERE pen.dateFin >= :date")
    List<Pret> findPretsAvecPenalitesActives(@Param("date") LocalDate date);

    @Query("SELECT p FROM Pret p WHERE p.etatPret.etat IN :etats ORDER BY p.datePret ASC")
    List<Pret> findPretsEnCoursLesplusAnciens(@Param("etats") List<String> etats);

    @Query("SELECT COUNT(pr) FROM ProlongementPret pr WHERE pr.pret.id = :pretId")
    int countProlongementsByPretId(@Param("pretId") int pretId);

    List<Pret> findByAdherentIdAndEtatPret_Etat(int adherentId, String etat);

    List<Pret> findByLivreIdAndEtatPret_Etat(int livreId, String etat);

    @Query("SELECT p FROM Pret p " +
            "JOIN MouvementPret mp ON mp.pret.id = p.id " +
            "JOIN EtatPret ep ON ep.id = mp.etatPret.id " +
            "WHERE ep.etat = 'en cours'")
    List<Pret> findPretsEnCours();
}
