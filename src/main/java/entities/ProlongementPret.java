package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "prolongement_pret")
public class ProlongementPret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "duree_jour")
    private int dureeJour;

    @ManyToOne
    @JoinColumn(name = "id_pret")
    private Pret pret;

    public ProlongementPret() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDureeJour() {
        return dureeJour;
    }

    public void setDureeJour(int dureeJour) {
        this.dureeJour = dureeJour;
    }

    public Pret getPret() {
        return pret;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
    }
}
