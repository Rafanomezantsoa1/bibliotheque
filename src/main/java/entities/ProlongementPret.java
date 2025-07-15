package entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prolongement_pret")
public class ProlongementPret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "duree_jour")
    private Integer dureeJour;

    @ManyToOne
    @JoinColumn(name = "id_pret")
    private Pret pret;

    @Column(name = "date_prolongement")
    private LocalDate dateProlongement = LocalDate.now();

    // Getters et setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDureeJour() {
        return dureeJour;
    }

    public void setDureeJour(Integer dureeJour) {
        this.dureeJour = dureeJour;
    }

    public Pret getPret() {
        return pret;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
    }

    public LocalDate getDateProlongement() {
        return dateProlongement;
    }

    public void setDateProlongement(LocalDate dateProlongement) {
        this.dateProlongement = dateProlongement;
    }
}
