package modele;

/**
 * Classe abstraite Personne.
 * Porte le nom et le prénom, communs aux patients et aux soignants.
 *
 * @author Fangnon Akouavi
 */
public abstract class Personne {
    private final String nom;
    private final String prenom;

    /**
     * Crée une personne.
     * @param nom le nom de la personne
     * @param prenom le prénom de la personne
     */
    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    /** @return le nom de la personne */
    public String getNom() {
        return this.nom;
    }

    /** @return le prénom de la personne */
    public String getPrenom() {
        return this.prenom;
    }
}