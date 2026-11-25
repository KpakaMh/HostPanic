package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe Patient de la clinique.
 * Un patient a un nom, un prénom, un numéro unique, une maladie,
 * une gravité (de 0 à 5) et une durée de soin restante (en jours).
 *
 * @author Fangnon Akouavi
 */
public class Patient extends Personne {

    /** Gravité à laquelle le patient décède. */
    public static final int GRAVITE_DECES = 5;
    /** Gravité maximale d'un patient à son arrivée. */
    public static final int GRAVITE_MAX_ARRIVEE = 4;
    /** Nombre maximal de patients qui arrivent chaque matin. */
    public static final int ARRIVEES_MAX = 3;

    private static final String[] NOMS = {
        "Martin", "Bernard", "Dubois", "Diallo", "Moreau", "Laurent", "Traore", "Garcia"
    };
    private static final String[] PRENOMS = {
        "Awa", "Paul", "Lina", "Hugo", "Emma", "Moussa", "Chloe", "Yanis"
    };
    private static final Random HASARD = new Random();

    private static int compteur = 1;

    private final int numero;
    private final Maladie maladie;
    private int gravite;
    private int joursRestants;

    /**
     * Crée un patient dont la maladie et la gravité sont imposées
     * (utilisé par exemple pour l'épidémie de grippe, ou dans les tests).
     * La durée de soin restante est celle de la maladie.
     *
     * @param nom le nom du patient
     * @param prenom le prénom du patient
     * @param maladie la maladie du patient
     * @param gravite la gravité à l'arrivée, entre 1 et 4
     * @throws IllegalArgumentException si la gravité n'est pas entre 1 et 4
     */
    public Patient(String nom, String prenom, Maladie maladie, int gravite) {
        super(nom, prenom);
        if (gravite < 1 || gravite > GRAVITE_MAX_ARRIVEE) {
            throw new IllegalArgumentException("La gravité à l'arrivée doit être entre 1 et 4");
        }
        this.numero = compteur++;
        this.maladie = maladie;
        this.gravite = gravite;
        this.joursRestants = maladie.getDureeSoin();
    }

    /**
     * Crée un patient avec une maladie tirée au hasard
     * et une gravité tirée au hasard entre 1 et 4 (surcharge du constructeur).
     *
     * @param nom le nom du patient
     * @param prenom le prénom du patient
     */
    public Patient(String nom, String prenom) {
        this(nom, prenom, maladieAuHasard(), 1 + HASARD.nextInt(GRAVITE_MAX_ARRIVEE));
    }

    /** @return une maladie tirée au hasard */
    private static Maladie maladieAuHasard() {
        Maladie[] maladies = Maladie.values();
        return maladies[HASARD.nextInt(maladies.length)];
    }

    /**
     * Étape 1 de la journée : fait arriver de 1 à 3 nouveaux patients,
     * chacun avec un nom, une maladie et une gravité tirés au hasard.
     * La méthode ne fait que créer les patients : c'est la classe Partie
     * qui les fait accueillir par la clinique et qui affiche leur arrivée.
     *
     * @return la liste des patients arrivés ce matin
     */
    public static List<Patient> arriveeDuMatin() {
        int nombre = 1 + HASARD.nextInt(ARRIVEES_MAX);
        List<Patient> arrivees = new ArrayList<>();
        for (int i = 0; i < nombre; i++) {
            String nom = NOMS[HASARD.nextInt(NOMS.length)];
            String prenom = PRENOMS[HASARD.nextInt(PRENOMS.length)];
            arrivees.add(new Patient(nom, prenom));
        }
        return arrivees;
    }

    /** @return le numéro unique du patient */
    public int getNumero() {
        return this.numero;
    }

    /** @return la maladie du patient */
    public Maladie getMaladie() {
        return this.maladie;
    }

    /** @return la gravité actuelle du patient */
    public int getGravite() {
        return this.gravite;
    }

    /** @return le nombre de jours de soin restants avant la guérison */
    public int getJoursRestants() {
        return this.joursRestants;
    }

    /**
     * Le patient reçoit un soin : sa durée de soin restante baisse de 1 jour.
     * Elle ne descend jamais en dessous de 0.
     */
    public void recevoirSoin() {
        if (this.joursRestants > 0) {
            this.joursRestants--;
        }
    }

    /**
     * Le patient n'a pas été soigné aujourd'hui : sa gravité augmente de 1.
     * Elle ne dépasse jamais 5.
     */
    public void aggraver() {
        if (this.gravite < GRAVITE_DECES) {
            this.gravite++;
        }
    }

    /**
     * @return true si le patient est guéri (durée de soin restante à 0), false sinon
     */
    public boolean estGueri() {
        return this.joursRestants == 0;
    }

    /**
     * @return true si le patient est décédé (gravité 5), false sinon
     */
    public boolean estDecede() {
        return this.gravite == GRAVITE_DECES;
    }
}