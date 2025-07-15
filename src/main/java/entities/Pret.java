package entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pret")
public class Pret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_adherent")
    private Adherent adherent;

    @ManyToOne
    @JoinColumn(name = "id_livre")
    private Livre livre;

    @Column(name = "date_pret")
    private LocalDateTime datePret;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @ManyToOne
    @JoinColumn(name = "id_type")
    private TypePret typePret;

    @ManyToOne
    @JoinColumn(name = "id_etat_pret")
    private EtatPret etatPret;

    @Column(name = "date_retour")
    private LocalDate dateRetour;

    @Transient 
    private String statutAffichage;

    @OneToMany(mappedBy = "pret", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProlongementPret> prolongements;

    @OneToMany(mappedBy = "pret", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MouvementPret> mouvements;

    @OneToMany(mappedBy = "pret", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Penalite> penalites;



    public Pret() {
    }

    public Pret(Adherent adherent, Livre livre, LocalDateTime datePret, LocalDate dateFin, TypePret typePret) {
        this.adherent = adherent;
        this.livre = livre;
        this.datePret = datePret;
        this.dateFin = dateFin;
        this.typePret = typePret;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public LocalDateTime getDatePret() {
        return datePret;
    }

    public void setDatePret(LocalDateTime datePret) {
        this.datePret = datePret;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public TypePret getTypePret() {
        return typePret;
    }

    public void setTypePret(TypePret typePret) {
        this.typePret = typePret;
    }

    public EtatPret getEtatPret() {
        return etatPret;
    }

    public void setEtatPret(EtatPret etatPret) {
        this.etatPret = etatPret;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }

    public String getStatutAffichage() {
        return statutAffichage;
    }

    public void setStatutAffichage(String statutAffichage) {
        this.statutAffichage = statutAffichage;
    }

    public List<ProlongementPret> getProlongements() {
        return prolongements;
    }

    public void setProlongements(List<ProlongementPret> prolongements) {
        this.prolongements = prolongements;
    }

    public List<MouvementPret> getMouvements() {
        return mouvements;
    }

    public void setMouvements(List<MouvementPret> mouvements) {
        this.mouvements = mouvements;
    }

    public List<Penalite> getPenalites() {
        return penalites;
    }

    public void setPenalites(List<Penalite> penalites) {
        this.penalites = penalites;
    }

    // === MÉTHODES UTILITAIRES ===

    /**
     * Vérifie si le prêt est en retard
     */
    public boolean isEnRetard() {
        return dateFin != null && dateFin.isBefore(LocalDate.now());
    }

    /**
     * Calcule le nombre de jours de retard
     */
    public int getJoursRetard() {
        if (isEnRetard()) {
            return (int) java.time.temporal.ChronoUnit.DAYS.between(dateFin, LocalDate.now());
        }
        return 0;
    }

    /**
     * Vérifie si le prêt expire bientôt (dans les 3 prochains jours)
     */
    public boolean expireBientot() {
        LocalDate dans3Jours = LocalDate.now().plusDays(3);
        return dateFin != null && dateFin.isBefore(dans3Jours) && !isEnRetard();
    }

    /**
     * Retourne le nombre de prolongements
     */
    public int getNombreProlongements() {
        return prolongements != null ? prolongements.size() : 0;
    }

    /**
     * Vérifie si le prêt peut être prolongé
     */
    public boolean peutEtreProlonge() {
        if (etatPret == null)
            return false;

        String etat = etatPret.getEtat();
        return ("en_cours".equals(etat) || "prolongé".equals(etat)) && getNombreProlongements() < 2;
    }

    @Override
    public String toString() {
        return "Pret{" +
                "id=" + id +
                ", adherent=" + (adherent != null ? adherent.getNom() + " " + adherent.getPrenom() : "null") +
                ", livre=" + (livre != null ? livre.getTitre() : "null") +
                ", datePret=" + datePret +
                ", dateFin=" + dateFin +
                ", etatPret=" + (etatPret != null ? etatPret.getEtat() : "null") +
                '}';
    }
}
