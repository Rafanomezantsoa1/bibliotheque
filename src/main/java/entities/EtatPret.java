package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "etat_pret")
public class EtatPret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "etat", length = 20)
    private String etat;

    public EtatPret() {
    }

    public EtatPret(String etat) {
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
