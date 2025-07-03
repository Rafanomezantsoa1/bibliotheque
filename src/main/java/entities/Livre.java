package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "livre") 
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "titre")
    private String titre;

    @Column(name = "auteur")
    private String auteur;

    @Column(name = "date_publication")
    private LocalDate datePublication;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "nb_total")
    private int nbTotal;

    @ManyToMany
    @JoinTable(
        name = "genre_livre",
        joinColumns = @JoinColumn(name = "id_livre"),
        inverseJoinColumns = @JoinColumn(name = "id_genre")
    )

    private List<Genre> genres;

    public Livre() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getNbTotal() {
        return nbTotal;
    }

    public void setNbTotal(int nbTotal) {
        this.nbTotal = nbTotal;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
   
}
