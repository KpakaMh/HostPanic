// CORRECTION COMPILATION (21/11/2026) : package "Jeu" renomme en "jeu" (minuscule, comme le dossier et l'UML).
//
// AJOUT V1 (21/11/2026) : main() enchaîne maintenant chaque étape de la journée.
// Supprimés : l'attribut "maladie" et le constructeur Main(Maladie), inutilisés,
// et le patient de test dans afficherMenu() : patient1.affecter() n'existe pas
// (l'affectation passe par Clinique.affecter(soignant, patient), appelée par Partie).
package jeu;

/**
 * Classe principale d'HostPanic!!
 * <p>
 * Point d'entrée du programme. Affiche le menu, puis enchaîne les journées
 * jusqu'à la fin de la partie. Main ne calcule rien : elle appelle les étapes
 * de {@link Partie} (règle 4.4 de la feuille de route).
 * </p>
 *
 * @author Mouhamadou Kpaka
 * @version V1
 */
public class Main {

    /**
     * Méthode principale.
     * <p>
     * Déroulement d'une journée :
     * <ol>
     *   <li>arrivées de nouveaux patients ;</li>
     *   <li>affectation de chaque soignant à un patient (choix du joueur) ;</li>
     *   <li>soins : guérisons et aggravations ;</li>
     *   <li>décès des patients arrivés à gravité 5 ;</li>
     *   <li>paiement des salaires ;</li>
     *   <li>événement du jour (V2) ;</li>
     *   <li>bilan de la journée.</li>
     * </ol>
     * </p>
     *
     * @param args arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        afficherMenu();
        Partie partie = new Partie();

        while (!partie.estTerminee()) {
            partie.afficherDebutJournee();
            partie.arriverPatients();      // étape 1 : arrivées
            partie.affecterSoignants();    // étape 2 : affectations
            partie.terminerSoins();        // étapes 3 et 4 : guérisons, aggravations, décès
            partie.payerSalaires();        // étape 5 : salaires (étape 6, événements : V2)
            partie.afficherBilan();        // étape 7 : bilan
            partie.attendreJourSuivant();  // pause : Entrée pour continuer (ajout 22/11/2026)
            partie.passerAuJourSuivant();
        }

        partie.afficherResultat();
    }

    /**
     * Affiche le menu de démarrage du jeu.
     * <p>
     * Affiche le titre HostPanic!!, l'objectif et les règles principales.
     * </p>
     */
    private static void afficherMenu() {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║              HostPanic!!                   ║");
        System.out.println("║   30 jours pour gérer votre clinique       ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Bienvenue ! Vous allez diriger une clinique pendant 30 jours.");
        System.out.println("Soignez les patients, gérez votre équipe et votre budget.");
        System.out.println("Objectif : Atteindre le jour 30 avec un budget positif et une réputation > 0.");
        System.out.println();
        System.out.println("Règles :");
        System.out.println("- Chaque jour, affectez un soignant à un patient (un soin = gravité - 1).");
        System.out.println("- Un patient non soigné s'aggrave. À gravité 5, il décède (amende).");
        System.out.println("- Un patient guéri (gravité 0) rapporte la prime de sa maladie.");
        System.out.println("- Le médecin soigne tout le monde, l'infirmier seulement les cas légers.");
    }
}
