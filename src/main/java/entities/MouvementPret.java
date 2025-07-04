package entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "mouvement_pret")
public class MouvementPret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_pret")
    private Pret pret;

    @ManyToOne
    @JoinColumn(name = "id_etat_pret")
    private EtatPret etatPret;

    @Column(name = "date_retour")
    private LocalDate dateRetour;

    public MouvementPret() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pret getPret() {
        return pret;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
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
}
