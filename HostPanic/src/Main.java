/**
 * Classe principale d'HostPanic!!
 * <p>
 * Point d'entrée du programme. Affiche le menu initial et lance une partie.
 * </p>
 *
 * @author Mouhamadou Kpaka
 * @version 1.0
 */
public class Main {

    /**
     * Méthode principale.
     * <p>
     * Lance le jeu en affichant le menu et en créant une nouvelle partie.
     * </p>
     *
     * @param args arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        afficherMenu();
        Partie partie = new Partie();
        partie.jouerPartie();
    }

    /**
     * Affiche le menu de démarrage du jeu.
     * <p>
     * Affiche le titre HostPanic!! et les instructions de lancement.
     * </p>
     */
    private static void afficherMenu() {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║                 HostPanic!!                ║");
        System.out.println("║           Gestion de Clinique              ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Bienvenue ! Vous allez diriger une clinique pendant 30 jours.");
        System.out.println("Soignez les patients, gérez votre équipe et votre budget.");
        System.out.println("Objectif : Atteindre le jour 30 avec un budget positif et une réputation > 0.");
        System.out.println();
    }
}