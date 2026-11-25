package modele;

// CORRECTION COMPILATION (21/11/2026) : imports "Modele.Patient" et "Modele.Soignant" supprimes.
// Le package "Modele" n'existe plus (renomme "modele") et ces classes sont dans le meme package.

import java.util.ArrayList;

/**
 * Représente la clinique : son budget, sa réputation, ses patients et ses soignants. Cette classe calcule mais n'affiche rien et ne lit rien au clavier : le dialogue avec le joueur est fait par Partie.
 * 
 * @author Johanna Ballo
 */
public class Clinique {
    private static final int REPUTATION_DEPART = 50;  
    private static final int REPUTATION_MAX = 100;
    private static final int POINTS_GUERISON = 2;
    private static final int POINTS_DECES = 10;
    private static final int AMENDE_DECES = 500;

    private int budget;
    private int reputation;
    private int medicaments;
    private final ArrayList<Patient> patients;
    private final ArrayList<Soignant> soignants;
    private final ArrayList<Soignant> soignantsOccupes;
    private final ArrayList<Patient> patientsSoignes;
    // AJOUT V1 (21/11/2026) : bilan de la dernière fin de journée, lu par Partie pour l'affichage.
    private final ArrayList<Patient> guerisDuJour;
    private final ArrayList<Patient> decedesDuJour;

    /** Nombre maximum de patients que la clinique peut accueillir en même temps.
     * @author Mouhamadou Kpaka
     * @version V2
     * */

    private int capacite;

/**
 * Initialise une nouvelle clinique avec le budget de départ défini selon la difficulté choisie.
 * 
 * @param budgetInitial le montant initial alloué à la clinique (ex: 10 000 € en mode normal)
 */
public Clinique(int budgetInitial) {
        this.budget = budgetInitial;
        this.reputation = REPUTATION_DEPART;
        this.medicaments = 0; // Initialise le stock de médicaments à zéro au démarrage de la partie (les achats interviennent en V2).
        this.patients = new ArrayList<>();
        this.soignants = new ArrayList<>();
        this.soignantsOccupes = new ArrayList<>();
        this.patientsSoignes = new ArrayList<>();
        this.guerisDuJour = new ArrayList<>();
        this.decedesDuJour = new ArrayList<>();
    }

    //Les getters
     /**
     * les getters
     */
    public int getBudget() {
        return this.budget;
    }

    /**
     * Retourne la réputation.
     */
    public int getReputation() {
        return this.reputation;
    }

    /**
     * Retourne le stock de médicaments.
     */
    public int getMedicaments() {
        return this.medicaments;
    }

    /**
     * Retourne une copie de la liste des patients.
     */
    public ArrayList<Patient> getPatients() {
        return new ArrayList<>(this.patients);
    }

    /**
     * Retourne une copie de la liste des soignants.
     */
    public ArrayList<Soignant> getSoignants() {
        return new ArrayList<>(this.soignants);
    }



//Les méthodes

     /**
     * Ajoute ou retire de l'argent au budget.
     *
     * @param montant le montant à ajouter (négatif pour retirer)
     */
    public void modifierBudget(int montant) {
        this.budget += montant;
    }

    /**
     * Modifie la réputation, qui reste toujours entre 0 et 100.
     *
     * @param points les points à ajouter (négatif pour retirer)
     */
    public void modifierReputation(int points) {
        int nouvelle = this.reputation + points;
        this.reputation = Math.max(0, Math.min(REPUTATION_MAX, nouvelle));
    }


    /**
     * Accueille un patient si la clinique a encore de la place. Cette méthode évolue, passant de la v1 à v2
     *
     * @param p le patient à accueillir
     * @return true si le patient est accueilli, false si la clinique est pleine
     * @author Joahana
     */
    public boolean accueillir(Patient p) {
        if (patients.size() >= capacite) {
            return false;
        }
        patients.add(p);
        return true;
    }
    /**
     * Ajoute un soignant à l'équipe, sans coût (équipe de départ).
     *
     * @param s le soignant à ajouter
     */
    public void ajouterSoignant(Soignant s) {
        if (s != null) {
            this.soignants.add(s);
        }
    }


    /**
     * Affecte un soignant à un patient pour la journée.
     * L'affectation réussit si le soignant et le patient font partie de la clinique, ne sont pas déjà pris aujourd'hui, et si le soignant peut soigner ce patient. Le patient reçoit alors un soin.
     *
     * @param s le soignant
     * @param p le patient
     * @return true si l'affectation a eu lieu, false sinon
     */
    public boolean affecter(Soignant s, Patient p) {
        boolean possible = this.soignants.contains(s) //Le soignant s fait bien partie de l'équipe de la clinique.
                && this.patients.contains(p) // Le patient p est bien présent dans la clinique
                && !this.soignantsOccupes.contains(s) //Le soignant n'est pas déjà occupé aujourd'hui (le ! veut dire "non").
                && !this.patientsSoignes.contains(p) //Le patient n'a pas déjà été pris en charge par un autre soignant.
                && s.peutSoigner(p); // Le soignant a les compétences requises pour traiter ce patient (par exemple, un infirmier ne peut pas soigner une gravité 4, contrairement au médecin).
        if (possible) {
            p.recevoirSoin();
            this.soignantsOccupes.add(s);
            this.patientsSoignes.add(p);
        }
        return possible; //Si toutes ces conditions sont vraies en même temps, la variable possible prend la valeur true, et l'affectation peut avoir lieu.
    }

    /**
     * Ajoute un soignant à l'équipe de la clinique et déduit le coût du recrutement.
     * 
     * @param s le soignant à recruter
     */
    public void recruter(Soignant s) {
        if (s != null) {
            this.soignants.add(s);
            this.budget -= 500; // Coût d'un recrutement : 500 €
        }
    }

    /**
     * Achète une quantité de médicaments et met à jour le budget de la clinique.
     * 
     * @param quantite le nombre de médicaments à acheter
     */
    public void acheterMedicaments(int quantite) {
        if (quantite > 0) {
            this.medicaments += quantite;
            this.budget -= (quantite * 50); // Un médicament coûte 50 €
        }
    }

    /**
     * Paie le salaire journalier de chaque soignant de l'équipe.
     * 
     */
    public void payerSalaires() {
        int total = 0;
        for (Soignant s : this.soignants) {
            if (s != null) {
                total += s.getSalaire(); // Récupère le salaire du soignant
            }
        }
        this.modifierBudget(-total); // Déduit le total du budget via la méthode dédiée
    }

    // ===== AJOUT V1 (21/11/2026) : étapes 3 et 4 de la journée =====

    /**
     * Termine la journée de soins (étapes 3 et 4).
     * <ul>
     *   <li>Un patient soigné aujourd'hui et dont la gravité vaut 0 est guéri :
     *       il quitte la clinique, qui touche la prime de sa maladie et gagne de la réputation.</li>
     *   <li>Un patient non soigné aujourd'hui s'aggrave (gravité + 1).</li>
     *   <li>Un patient dont la gravité atteint 5 décède : il quitte la clinique,
     *       qui paie une amende et perd de la réputation.</li>
     * </ul>
     * Les affectations du jour sont ensuite effacées pour le lendemain.
     */
    public void finirJournee() {
        this.guerisDuJour.clear();
        this.decedesDuJour.clear();

        for (Patient p : new ArrayList<>(this.patients)) {
            if (!this.patientsSoignes.contains(p)) {
                p.aggraver();
            }
            if (p.estGueri()) {
                this.patients.remove(p);
                this.guerisDuJour.add(p);
                this.modifierBudget(p.getMaladie().getPrime());
                this.modifierReputation(POINTS_GUERISON);
            } else if (p.estDecede()) {
                this.patients.remove(p);
                this.decedesDuJour.add(p);
                this.modifierBudget(-AMENDE_DECES);
                this.modifierReputation(-POINTS_DECES);
            }
        }

        this.soignantsOccupes.clear();
        this.patientsSoignes.clear();
    }

    /**
     * Retourne une copie des patients guéris lors de la dernière fin de journée.
     *
     * @return les patients guéris
     */
    public ArrayList<Patient> getGuerisDuJour() {
        return new ArrayList<>(this.guerisDuJour);
    }

    /**
     * Retourne une copie des patients décédés lors de la dernière fin de journée.
     *
     * @return les patients décédés
     */
    public ArrayList<Patient> getDecedesDuJour() {
        return new ArrayList<>(this.decedesDuJour);
    }

    /**
     * Augmente la capacité d'accueil de la clinique.
     *
     * @param places nombre de places supplémentaires, positif
     * @author
     */
    public void augmenterCapacite(int places) {
        capacite += places;
    }
}