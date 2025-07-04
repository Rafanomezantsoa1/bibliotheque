package repository;

import entities.NormePret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NormePretRepository extends JpaRepository<NormePret, Integer> {
    // NormePret findByLivreIdAndProfilId(int livreId, int profilId);


    // @Query("SELECT n FROM NormePret n WHERE n.livre.id = :livreId AND n.profil.id = :profilId")
    // NormePret findByLivreIdAndProfilId(@Param("livreId") int livreId, @Param("profilId") int profilId);

    @Query("SELECT n FROM NormePret n WHERE n.livre.id = :livreId AND n.profil.id = :profilId")
    NormePret findByLivreIdAndProfilId(@Param("livreId") int livreId, @Param("profilId") int profilId);


}
