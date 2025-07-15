package service;

import entities.Pret;
import entities.EtatPret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PretRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class PretService {

    private PretRepository pretRepository;

    @Autowired
    public PretService(PretRepository pretRepository) {
        this.pretRepository = pretRepository;
    }

    public List<Pret> getAll() {
        return pretRepository.findAll();
    }

    public Pret save(Pret pret) {
        return pretRepository.save(pret);
    }

    public Pret getById(int id) {
        return pretRepository.findById(id).orElse(null);
    }

    public Pret findById(Integer id) {
        return pretRepository.findById(id).orElse(null);
    }

    public void deleteById(int id) {
        pretRepository.deleteById(id);
    }

    public List<Pret> getByAdherent(int adherentId) {
        return pretRepository.findByAdherentId(adherentId);
    }

    public PretRepository getPretRepository() {
        return pretRepository;
    }

    public int countByAdherentAndLivre(int adherentId, int livreId) {
        return pretRepository.countByAdherentIdAndLivreId(adherentId, livreId);
    }

    public Pret saveAndReturn(Pret pret) {
        return pretRepository.save(pret);
    }
    
    public boolean isLivreDisponible(int livreId, LocalDate debut, LocalDate fin) {
        int nbPretsEnCours = pretRepository.countByLivreIdAndDateFinAfterAndEtatPret_EtatIn(
            livreId, debut, Arrays.asList("en_cours", "prolongé")
        );
        
        int totalExemplaires = pretRepository.getTotalStockByLivreId(livreId);
        
        return nbPretsEnCours < totalExemplaires;
    }

  
    public List<Pret> getPretsEnCours() {
        return pretRepository.findByEtatPret_EtatIn(Arrays.asList("en_cours", "prolongé"));
    }

   
    public List<Pret> getPretsEnCoursAvecStatut() {
        LocalDate now = LocalDate.now();
        LocalDate threeDaysFromNow = now.plusDays(3);
        
        List<Pret> pretsEnCours = pretRepository.findByEtatPret_EtatIn(Arrays.asList("en_cours", "prolongé"));
        
        for (Pret pret : pretsEnCours) {
            if (pret.getDateFin().isBefore(now)) {
                pret.setStatutAffichage("retard");
            } else if (pret.getDateFin().isBefore(threeDaysFromNow) || pret.getDateFin().isEqual(threeDaysFromNow)) {
                pret.setStatutAffichage("expire-bientot");
            } else {
                pret.setStatutAffichage("normal");
            }
        }
        
        return pretsEnCours;
    }

    
    public List<Pret> getPretsEnRetard() {
        LocalDate now = LocalDate.now();
        return pretRepository.findByDateFinBeforeAndEtatPret_EtatIn(now, Arrays.asList("en_cours", "prolongé"));
    }

    public List<Pret> getPretsExpirantBientot(int jours) {
        LocalDate now = LocalDate.now();
        LocalDate limitDate = now.plusDays(jours);
        return pretRepository.findByDateFinBetweenAndEtatPret_EtatIn(now, limitDate, Arrays.asList("en_cours", "prolongé"));
    }

   
    public List<Pret> getPretsEnCoursByAdherent(int adherentId) {
        return pretRepository.findByAdherentIdAndEtatPret_EtatIn(adherentId, Arrays.asList("en_cours", "prolongé"));
    }

 
    public List<Pret> getPretsEnCoursByLivre(int livreId) {
        return pretRepository.findByLivreIdAndEtatPret_EtatIn(livreId, Arrays.asList("en_cours", "prolongé"));
    }

   
    public boolean peutEtreProlonge(Pret pret) {
        if (pret == null) return false;
        
        String etatPret = pret.getEtatPret().getEtat();
        if (!"en_cours".equals(etatPret) && !"prolongé".equals(etatPret)) {
            return false;
        }
        
        int nbProlongements = pret.getProlongements() != null ? pret.getProlongements().size() : 0;
        if (nbProlongements >= 2) {
            return false;
        }
        
        return true;
    }

  
    public void marquerCommeRetourne(Pret pret) {
        pret.setDateRetour(LocalDate.now());
        pretRepository.save(pret);
    }

    public int getJoursRetard(Pret pret) {
        LocalDate now = LocalDate.now();
        if (pret.getDateFin().isBefore(now)) {
            return (int) java.time.temporal.ChronoUnit.DAYS.between(pret.getDateFin(), now);
        }
        return 0;
    }

    public PretStatistiques getStatistiques() {
        List<Pret> pretsEnCours = getPretsEnCours();
        List<Pret> pretsEnRetard = getPretsEnRetard();
        List<Pret> pretsExpirantBientot = getPretsExpirantBientot(3);
        
        return new PretStatistiques(
            pretsEnCours.size(),
            pretsEnRetard.size(),
            pretsExpirantBientot.size()
        );
    }

    public static class PretStatistiques {
        private int totalPretsEnCours;
        private int pretsEnRetard;
        private int pretsExpirantBientot;

        public PretStatistiques(int totalPretsEnCours, int pretsEnRetard, int pretsExpirantBientot) {
            this.totalPretsEnCours = totalPretsEnCours;
            this.pretsEnRetard = pretsEnRetard;
            this.pretsExpirantBientot = pretsExpirantBientot;
        }
        // Getters
        public int getTotalPretsEnCours() { return totalPretsEnCours; }
        public int getPretsEnRetard() { return pretsEnRetard; }
        public int getPretsExpirantBientot() { return pretsExpirantBientot; }
    }
}