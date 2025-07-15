package dto;

import entities.Livre;

public class LivreDTO {
    private Integer id;
    private String titre;
    private String auteur;
    private String isbn;
    private String datePublication;

    public static LivreDTO fromEntity(Livre livre) {
        LivreDTO dto = new LivreDTO();
        dto.id = livre.getId();
        dto.titre = livre.getTitre();
        dto.auteur = livre.getAuteur();
        dto.isbn = livre.getIsbn();
        if (livre.getDatePublication() != null) {
            dto.datePublication = livre.getDatePublication().toString();
        }
        return dto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }

}
