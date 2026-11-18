package Modele;
/**
 * Soignant de la clinique (médecin ou infirmier)
 * Classe abstraite : on ne crée jamais un soignant tout court il faut qu'il soit clairement defini(Medecin ou infirmier)
 * la classe contient le salaire journalier et dit si c'est possible que le soignant peutSoigner(true ou false),
 * que chaque type de soignant redéfinit à sa manière
 *
 * @Author : Desire Dady
 */
public abstract class Soignant extends Personne {
    private int salaire;
    /**
     * Crée un soignant.
     *
     * @param nom le nom du soignant
     * @param prenom le prénom du soignant
     * @param salaire le salaire versé au soignant chaque jour
     */

    public Soignant(String nom, String prenom, int salaire) {
        super(nom,prenom);
        this.salaire = salaire;
    }
     /**
     * Renvoie le salaire journalier du soignant.
     * @return le salaire par jour, en euros
     */

    public int getSalaire() {
        return this.salaire;
    }
    /**
     * Dit si ce soignant peut prendre en charge le patient.
     * La réponse dépend du soignant (médecin, infirmier).
     *
     * @param p represent le patient à soigner
     * @return true si le soignant peut soigner ce patient, false si c'est non
     */

    public abstract boolean peutSoigner(Patient p);
}