package jeu;

import modele.Clinique;
import modele.Difficulte;
import modele.Infirmier;
import modele.Medecin;
import modele.Medicament;
import modele.Patient;
import modele.Soignant;
import modele.BudgetInsuffisant;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Représente une partie de HostPanic!!.
 * <p>
 * Une partie dure 30 jours. Chaque jour, elle enchaîne les étapes
 * de la journée (arrivées, décisions du joueur, soins, salaires, bilan)
 * et vérifie si la partie est terminée.
 * <p>
 * La partie possède la clinique dirigée par le joueur (composition).
 * La clinique est créée avec la partie et n'existe pas sans elle.
 * <p>
 * Partage du travail (règle 4.4 de la feuille de route).
 * Partie dialogue avec le joueur (elle affiche et lit au clavier),
 * tandis que Clinique et les classes du package modele font les calculs
 * sans rien afficher.
 *
 * @author DJELGOU Hamidou
 * @version V1
 */
public class Partie {

    /** Le numéro du jour en cours. Il commence à 1 et augmente de 1 à la fin de chaque journée. */
    private int jour;

    /** La clinique dirigée par le joueur, avec son budget, sa réputation, ses patients et ses soignants. */
    private Clinique clinique;

    /**
     * Le clavier, pour lire les choix du joueur.
     * <p>
     * Il est créé une seule fois pour toute la partie. Si on recréait un Scanner
     * chaque jour, le nouveau ne trouverait plus rien à lire et le jeu planterait.
     * (Attribut technique, absent de l'UML.)
     */
    private Scanner clavier;

    /** La difficulté choisie par le joueur au début de la partie. Elle fixe le budget de départ (V2). */
    private Difficulte difficulte;

    /**
     * Vaut true quand la clinique n'a plus assez d'argent pour payer une dépense obligatoire
     * (salaires ou amende de décès). La partie est alors perdue (V2).
     */
    private boolean faillite;

    /** Le montant de la subvention versée par l'État tous les 7 jours, en euros (V2). */
    private static final int SUBVENTION = 2000;

    /**
     * Crée une nouvelle partie, prête à jouer le jour 1.
     * <p>
     * Le joueur choisit d'abord la difficulté (facile, normal ou difficile).
     * La clinique démarre avec le budget de cette difficulté
     * (15 000 €, 10 000 € ou 6 000 €) et une équipe de départ de deux soignants,
     * Dr Sow, médecin, payé 200 € par jour,
     * et Léa Martin, infirmière, payée 100 € par jour.
     * Si le joueur tape un numéro qui n'existe pas, la difficulté Normal est choisie.
     *
     * @author DJELGOU Hamidou
     */
    public Partie() {
        jour = 1;
        faillite = false;
        clavier = new Scanner(System.in);

        // Le joueur choisit la difficulté (V2)
        System.out.println("Choisissez la difficulté");
        int numero = 1;
        for (Difficulte d : Difficulte.values()) {
            System.out.println(numero + ". " + d + " (budget de départ " + d.getBudgetInitial() + " €)");
            numero++;
        }

        int choix = clavier.nextInt();

        if (choix >= 1 && choix <= Difficulte.values().length) {
            difficulte = Difficulte.values()[choix - 1];   // la difficulté n°1 est à la place 0
        } else {
            System.out.println("Ce numéro n'existe pas, la difficulté NORMAL est choisie.");
            difficulte = Difficulte.NORMAL;
        }
        System.out.println("Difficulté " + difficulte + ", vous démarrez avec " + difficulte.getBudgetInitial() + " €.");

        clinique = new Clinique(difficulte.getBudgetInitial());
        clinique.ajouterSoignant(new Medecin("Sow", "Dr", 200));
        clinique.ajouterSoignant(new Infirmier("Martin", "Léa", 100));
    }

    /**
     * Joue une journée complète, étape par étape, puis passe au jour suivant.
     * <p>
     * Étape 1, arrivées. De 1 à 3 nouveaux patients entrent dans la clinique.
     * Étape 2, décisions. Le joueur achète d'abord des médicaments s'il le souhaite (V2),
     * car chaque soin consomme le médicament de la maladie du patient.
     * Ensuite, pour chaque soignant, il choisit au clavier le patient
     * à soigner (0 = personne), puis la clinique accepte ou refuse l'affectation.
     * Étapes 3 et 4. Aggravations, guérisons et décès, calculés par la clinique.
     * Étape 5, salaires. La clinique paie chaque soignant.
     * Étape 6. Tous les 7 jours, l'État verse une subvention de 2 000 €. Événement du jour (à venir, V2).
     * Étape 7, bilan. Affichage du budget, de la réputation et du nombre de patients.
     * <p>
     * Si la partie se termine ce jour-là, la méthode annonce la victoire ou la défaite.
     *
     * @author DJELGOU Hamidou
     */
    public void jouerJournee() throws BudgetInsuffisant {
        System.out.println();
        System.out.println("========== Jour " + jour + " ==========");

        // ----- Étape 1, arrivées des patients -----
        // Patient.arriveeDuMatin() crée les nouveaux patients du jour (méthode de Fangnon),
        // la clinique les accueille et on annonce leur arrivée au joueur.
        System.out.println("Arrivées du matin");
        for (Patient p : Patient.arriveeDuMatin()) {
            boolean accueilli = clinique.accueillir(p);   // false si la clinique est pleine
            if (accueilli) {
                System.out.println(p.getPrenom() + " " + p.getNom() + " arrive (" + p.getMaladie()
                        + ", gravité " + p.getGravite() + ")");
            } else {
                System.out.println(p.getPrenom() + " " + p.getNom() + " est refusé, la clinique est pleine.");
            }
        }

        // ----- Étape 2a, le joueur achète des médicaments (V2) -----
        // Le joueur peut acheter plusieurs sortes de médicaments, l'une après l'autre.
        // Il tape 0 quand il a fini ses achats.
        boolean achatEnCours = true;
        while (achatEnCours) {
            System.out.println();
            System.out.println("Budget " + clinique.getBudget() + " €. Quel médicament acheter ? (0 = fini)");
            int numeroMed = 1;
            for (Medicament m : Medicament.values()) {
                System.out.println(numeroMed + ". " + m + " (" + m.getPrix() + " €, en stock "
                        + clinique.compterMedicaments(m) + ")");
                numeroMed++;
            }

            int choixMed = clavier.nextInt();

            if (choixMed <= 0) {
                achatEnCours = false;
            } else if (choixMed > Medicament.values().length) {
                System.out.println("Ce numéro n'existe pas.");
            } else {
                Medicament m = Medicament.values()[choixMed - 1];   // le médicament n°1 est à la place 0
                System.out.println("Combien de " + m + " ?");
                int quantite = clavier.nextInt();
                try {
                    clinique.acheterMedicaments(m, quantite);
                    System.out.println("Stock de " + m + " : " + clinique.compterMedicaments(m));
                } catch (BudgetInsuffisant e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        // ----- Étape 2b, le joueur affecte chaque soignant à un patient -----
        ArrayList<Patient> patients = clinique.getPatients();

        for (Soignant s : clinique.getSoignants()) {
            System.out.println();
            System.out.println("Quel patient doit soigner " + s.getNom() + " ? (0 = personne)");

            // On affiche les patients avec un numéro qui commence à 1
            int numero = 1;
            for (Patient p : patients) {
                System.out.println(numero + ". " + p.getPrenom() + " " + p.getNom() + " (gravité " + p.getGravite()
                        + ", " + p.getMaladie() + ", il faut " + p.getMaladie().getMedicament() + ")");
                numero++;
            }

            int choix = clavier.nextInt();

            if (choix <= 0) {
                System.out.println(s.getNom() + " ne soigne personne aujourd'hui.");
            } else if (choix > patients.size()) {
                System.out.println("Ce numéro n'existe pas.");
            } else {
                Patient p = patients.get(choix - 1);   // le patient n°1 est à la place 0
                boolean reussi = clinique.affecter(s, p);
                if (reussi) {
                    System.out.println(s.getNom() + " soigne " + p.getNom() + ".");
                } else if (clinique.compterMedicaments(p.getMaladie().getMedicament()) == 0) {
                    System.out.println("Soin impossible, il n'y a plus de " + p.getMaladie().getMedicament() + " en stock.");
                } else {
                    System.out.println(s.getNom() + " ne peut pas soigner ce patient.");
                }
            }
        }

        // ----- Étapes 3 et 4, guérisons, aggravations, décès -----
        // La clinique fait les calculs de fin de journée (méthode finirJournee de Clinique).
        // ----- Étape 5, salaires -----
        // Si la clinique n'a plus assez d'argent pour une amende ou pour les salaires,
        // Clinique lance l'exception BudgetInsuffisant (écrite par Dady).
        // On la récupère ici avec catch, et la clinique fait faillite.
        try {
            clinique.finirJournee();
            clinique.payerSalaires();
        } catch (BudgetInsuffisant e) {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println("La clinique ne peut plus payer ses dépenses, elle fait faillite.");
            faillite = true;
        }

        // ----- Étape 6, subvention et événements (V2) -----
        // Tous les 7 jours (jours 7, 14, 21 et 28), l'État verse une subvention.
        // jour % 7 donne le reste de la division de jour par 7, il vaut 0 quand jour est un multiple de 7.
        if (jour % 7 == 0) {
            clinique.modifierBudget(SUBVENTION);
            System.out.println();
            System.out.println("L'État verse une subvention de " + SUBVENTION + " €.");
        }
        // À FAIRE utiliser tirerEvenement(), puis evenement.appliquer(clinique).

        // ----- Étape 7, bilan -----
        System.out.println();
        System.out.println("--- Bilan du jour " + jour + " ---");
        System.out.println("Budget : " + clinique.getBudget() + " €");
        System.out.println("Réputation : " + clinique.getReputation() + " / 100");
        System.out.println("Patients à la clinique : " + clinique.getPatients().size());

        jour++;

        // Si la partie vient de se terminer, on annonce le résultat
        if (estTerminee()) {
            System.out.println();
            if (jour > 30 && !faillite && clinique.getReputation() > 0) {
                System.out.println("VICTOIRE ! Votre clinique a tenu 30 jours.");
            } else {
                System.out.println("DÉFAITE... Votre clinique a fermé.");
            }
        }
    }

    /**
     * Indique si la partie est finie.
     * <p>
     * La partie s'arrête dès que les 30 jours sont passés, que la clinique a fait faillite
     * (plus assez d'argent pour les salaires ou une amende), ou que la réputation de la clinique est tombée à 0.
     * Cette méthode dit seulement si c'est fini, pas si le joueur a gagné.
     * Elle est utilisée par Main pour savoir quand arrêter de jouer des journées.
     *
     * @return true si la partie est terminée, false si elle continue
     * @author DJELGOU Hamidou
     */
    public boolean estTerminee() {
        return jour > 30 || faillite || clinique.getReputation() <= 0;
    }

    /**
     * Calcule le score du joueur à la fin de la partie.
     * <p>
     * La formule sera choisie avec le groupe en V3.
     * En attendant, la méthode renvoie 0.
     *
     * @return le score de la partie (0 pour l'instant)
     * @author DJELGOU Hamidou
     */
    public int calculerScore() {
        int score = 0;
        // À FAIRE (V3) formule du score à valider avec le groupe.
        return score;
    }
}
