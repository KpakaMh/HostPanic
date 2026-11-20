package jeu;

import modele.Clinique;
import modele.Infirmier;
import modele.Medecin;
import modele.Patient;
import modele.Soignant;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Représente une partie de HostPanic!!.
 * <p>
 * Une partie dure 30 jours. Chaque jour, elle enchaîne les étapes
 * de la journée (arrivées, décisions du joueur, soins, salaires, bilan)
 * et vérifie si la partie est terminée.
 * <p>
 * La partie possède la clinique dirigée par le joueur (composition) :
 * la clinique est créée avec la partie et n'existe pas sans elle.
 * <p>
 * Partage du travail (règle 4.4 de la feuille de route) :
 * Partie dialogue avec le joueur (elle affiche et lit au clavier),
 * tandis que Clinique et les classes du package modele font les calculs
 * sans rien afficher.
 *
 * @author DJELGOU Hamidou
 * @version V1
 */
public class Partie {

    /** Le numéro du jour en cours : commence à 1 et augmente de 1 à la fin de chaque journée. */
    private int jour;

    /** La clinique dirigée par le joueur : son budget, sa réputation, ses patients et ses soignants. */
    private Clinique clinique;

    // À FAIRE (V2) : ajouter l'attribut Difficulte quand la classe Difficulte existera.

    /**
     * Crée une nouvelle partie, prête à jouer le jour 1.
     * <p>
     * La clinique démarre avec 10 000 € (budget du mode Normal ; en V2, il dépendra
     * de la difficulté choisie) et une équipe de départ de deux soignants :
     * <ul>
     *   <li>Dr Sow, médecin, payé 200 € par jour ;</li>
     *   <li>Léa Martin, infirmière, payée 100 € par jour.</li>
     * </ul>
     *
     * @author DJELGOU Hamidou
     */
    public Partie() {
        jour = 1;
        // 10 000 € = budget du mode Normal (en V2, il viendra de Difficulte)
        clinique = new Clinique(10000);
        clinique.ajouterSoignant(new Medecin("Sow", "Dr", 200));
        clinique.ajouterSoignant(new Infirmier("Martin", "Léa", 100));
    }

    /**
     * Joue une journée complète, étape par étape, puis passe au jour suivant.
     * <p>
     * Déroulement d'une journée :
     * <ul>
     *   <li>Étape 1, arrivées : de nouveaux patients entrent dans la clinique (à venir, binôme 1).</li>
     *   <li>Étape 2, décisions : pour chaque soignant, le joueur choisit au clavier le patient
     *       à soigner (0 = personne) ; la clinique accepte ou refuse l'affectation.</li>
     *   <li>Étapes 3 et 4 : soins, aggravations, guérisons et décès (à venir, méthode de Clinique).</li>
     *   <li>Étape 5, salaires : la clinique paie chaque soignant.</li>
     *   <li>Étape 6 : événement du jour (V2).</li>
     *   <li>Étape 7, bilan : affichage du budget, de la réputation et du nombre de patients.</li>
     * </ul>
     * Si la partie se termine ce jour-là, la méthode annonce la victoire ou la défaite.
     *
     * @author DJELGOU Hamidou
     */
    public void jouerJournee() {
        Scanner clavier = new Scanner(System.in);

        System.out.println();
        System.out.println("========== Jour " + jour + " ==========");

        // ----- Étape 1 : arrivées des patients -----
        // À FAIRE : quand la méthode d'arrivée du binôme 1 existera,
        // appeler clinique.accueillir(p) pour chaque nouveau patient.

        // ----- Étape 2 : le joueur affecte chaque soignant à un patient -----
        ArrayList<Patient> patients = clinique.getPatients();

        for (Soignant s : clinique.getSoignants()) {
            System.out.println();
            System.out.println("Quel patient doit soigner " + s.getNom() + " ? (0 = personne)");

            // On affiche les patients avec un numéro qui commence à 1
            int numero = 1;
            for (Patient p : patients) {
                System.out.println(numero + ". " + p.getNom() + " (gravité " + p.getGravite() + ")");
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
                } else {
                    System.out.println(s.getNom() + " ne peut pas soigner ce patient.");
                }
            }
        }

        // ----- Étapes 3 et 4 : guérisons, aggravations, décès -----
        // À FAIRE : appeler la méthode de fin de journée de Clinique (écrite par Johanna,
        // nom à confirmer en groupe, proposition : clinique.finirJournee()).

        // ----- Étape 5 : salaires -----
        clinique.payerSalaires();

        // ----- Étape 6 : événements (V2) -----
        // À FAIRE : utiliser tirerEvenement(), puis evenement.appliquer(clinique).

        // ----- Étape 7 : bilan -----
        System.out.println();
        System.out.println("--- Bilan du jour " + jour + " ---");
        System.out.println("Budget : " + clinique.getBudget() + " €");
        System.out.println("Réputation : " + clinique.getReputation() + " / 100");
        System.out.println("Patients à la clinique : " + clinique.getPatients().size());

        jour++;

        // Si la partie vient de se terminer, on annonce le résultat
        if (estTerminee()) {
            System.out.println();
            if (jour > 30 && clinique.getBudget() >= 0 && clinique.getReputation() > 0) {
                System.out.println("VICTOIRE ! Votre clinique a tenu 30 jours.");
            } else {
                System.out.println("DÉFAITE... Votre clinique a fermé.");
            }
        }
    }

    /**
     * Indique si la partie est finie.
     * <p>
     * La partie s'arrête dès qu'une de ces trois situations arrive :
     * <ul>
     *   <li>les 30 jours sont passés ;</li>
     *   <li>le budget de la clinique est passé sous 0 € ;</li>
     *   <li>la réputation de la clinique est tombée à 0.</li>
     * </ul>
     * Cette méthode dit seulement si c'est fini, pas si le joueur a gagné.
     * Elle est utilisée par Main pour savoir quand arrêter de jouer des journées.
     *
     * @return true si la partie est terminée, false si elle continue
     * @author DJELGOU Hamidou
     */
    public boolean estTerminee() {
        return jour > 30 || clinique.getBudget() < 0 || clinique.getReputation() <= 0;
    }

    /**
     * Calcule le score du joueur à la fin de la partie.
     * <p>
     * La formule sera choisie avec le groupe en V3 ;
     * en attendant, la méthode renvoie 0.
     *
     * @return le score de la partie (0 pour l'instant)
     * @author DJELGOU Hamidou
     */
    public int calculerScore() {
        int score = 0;
        // À FAIRE (V3) : formule du score à valider avec le groupe.
        return score;
    }
}
