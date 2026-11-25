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
    /** Le stock de médicaments. Chaque médicament acheté est ajouté dans la liste (V2, DJELGOU Hamidou). */
    private final ArrayList<Medicament> medicaments;
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
        this.medicaments = new ArrayList<>(); // Au départ, le stock de médicaments est vide (les achats interviennent en V2).
        this.patients = new ArrayList<>();
        this.soignants = new ArrayList<>();
        this.soignantsOccupes = new ArrayList<>();
        this.patientsSoignes = new ArrayList<>();
        this.guerisDuJour = new ArrayList<>();
        this.decedesDuJour = new ArrayList<>();
        this.capacite = 10; // La clinique peut accueillir 10 patients en même temps au départ.
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
     * Compte combien il reste d'un médicament dans le stock.
     * La boucle passe sur chaque médicament du stock et ajoute 1
     * au compteur à chaque fois qu'elle trouve le médicament demandé.
     *
     * @param m le médicament à compter
     * @return le nombre de médicaments de ce type dans le stock
     * @author DJELGOU Hamidou
     */
    public int compterMedicaments(Medicament m) {
        int nombre = 0;
        for (Medicament x : this.medicaments) {
            if (x == m) {
                nombre++;
            }
        }
        return nombre;
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
     * Accueille un patient si la clinique a encore de la place.
     *
     * @param p le patient à accueillir
     * @return true si le patient est accueilli, false si la clinique est pleine
     * @author Mouhamadou Kpaka Adesanya
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
     * Depuis la V2, il faut aussi qu'il reste dans le stock le médicament de la maladie du patient.
     * Chaque soin consomme un médicament (ajout V2, DJELGOU Hamidou).
     *
     * @param s le soignant
     * @param p le patient
     * @return true si l'affectation a eu lieu, false sinon
     * @author DJELGOU Hamidou (ajout des médicaments, V2)
     */
    public boolean affecter(Soignant s, Patient p) {
        boolean possible = this.soignants.contains(s) //Le soignant s fait bien partie de l'équipe de la clinique.
                && this.patients.contains(p) // Le patient p est bien présent dans la clinique
                && !this.soignantsOccupes.contains(s) //Le soignant n'est pas déjà occupé aujourd'hui (le ! veut dire "non").
                && !this.patientsSoignes.contains(p) //Le patient n'a pas déjà été pris en charge par un autre soignant.
                && s.peutSoigner(p) // Le soignant a les compétences requises pour traiter ce patient (par exemple, un infirmier ne peut pas soigner une gravité 4, contrairement au médecin).
                && this.medicaments.contains(p.getMaladie().getMedicament()); // Il reste dans le stock le médicament de la maladie du patient (V2).
        if (possible) {
            p.recevoirSoin();
            this.medicaments.remove(p.getMaladie().getMedicament()); // Le soin consomme un médicament (V2).
            this.soignantsOccupes.add(s);// Le soignant est maintenant occupé pour la journée.
            this.patientsSoignes.add(p);// Le patient a été soigné aujourd'hui.
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
     * Achète une quantité d'un médicament et paie son prix.
     * Chaque médicament acheté est ajouté une fois dans le stock,
     * puis la clinique paie la quantité multipliée par le prix du médicament.
     *
     * @param m le médicament à acheter
     * @param quantite le nombre de médicaments à acheter
     * @author DJELGOU Hamidou
     */
    public void acheterMedicaments(Medicament m, int quantite) {
        if (quantite > 0) {
            for (int i = 0; i < quantite; i++) {
                this.medicaments.add(m); // un médicament de plus dans le stock
            }
            this.modifierBudget(-quantite * m.getPrix());
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

    /**
     * Termine la journée de la clinique (étapes 3 et 4 de la journée).
     * Cette méthode est appelée par Partie, une fois que le joueur a affecté ses soignants
     * et avant le paiement des salaires. Elle fait seulement les calculs, sans rien afficher.
     * <p>
     * Étape 3, les aggravations. La méthode passe sur chaque patient de la clinique.
     * Un patient qui n'est pas dans la liste patientsSoignes n'a reçu aucun soin aujourd'hui,
     * alors sa gravité augmente de 1 (méthode aggraver de Patient).
     * <p>
     * Étape 4, les guérisons et les décès.
     * Un patient guéri (sa durée de soin restante est à 0) rapporte à la clinique la prime
     * de sa maladie (getPrime de Maladie) et 2 points de réputation (POINTS_GUERISON).
     * Un patient décédé (sa gravité a atteint 5) coûte 500 € d'amende (AMENDE_DECES)
     * et fait perdre 10 points de réputation (POINTS_DECES).
     * Dans les deux cas, le patient quitte la clinique.
     * Les patients qui ne sont ni guéris ni décédés restent pour le lendemain.
     * Les patients guéris et décédés du jour sont aussi rangés dans les listes
     * guerisDuJour et decedesDuJour, pour que Partie puisse les afficher (ajout de Mouhamadou Kpaka).
     * <p>
     * Fin de journée. Les listes soignantsOccupes et patientsSoignes sont vidées,
     * pour que chaque soignant puisse de nouveau être affecté le lendemain
     * et que chaque patient puisse de nouveau recevoir un soin.
     *
     * @author DJELGOU Hamidou
     * @author Mouhamadou Kpaka (listes guerisDuJour et decedesDuJour)
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
     * @author Mouhamadou Kpaka Adesanya
     */
    public void augmenterCapacite(int places) {
       capacite += places;
    }
}