package evenement;

import modele.BudgetInsuffisant;
import modele.Clinique;

/**
 * Classe mère des événements de HostPanic.
 * <p>
 * Un événement possède une description, affichée au joueur quand il survient,
 * et un effet appliqué sur la clinique. La description est commune à tous
 * les événements : elle est donc stockée ici une seule fois.
 * <p>
 * La classe est abstraite : seules les classes filles créent des événements.
 * Chaque fille doit fournir son effet dans {@link #appliquer(Clinique)}.
 *
 * @author Mouhamadou Adesanya Kpaka
 * @version V2
 */
public abstract class Evenement {

    /** Texte affiché au joueur quand l'événement survient. Ne change jamais. */
    private final String description;

    /**
     * Crée un événement avec sa description.
     * <p>
     * Appelé par le constructeur de chaque classe fille, via {@code super(...)}.
     *
     * @param description le texte affiché au joueur
     * @author
     */
    public Evenement(String description) {
        this.description = description;
    }

    /**
     * Renvoie le texte affiché au joueur quand l'événement survient.
     *
     * @return la description de l'événement
     * @author
     */
    public String getDescription() {
        return description;
    }

    /**
     * Applique l'effet de l'événement sur la clinique.
     * <p>
     * Chaque classe fille définit son propre effet. Cette méthode ne fait
     * ni affichage ni lecture au clavier.
     *
     * @param c la clinique concernée par l'événement
     * @author
     */
    public abstract void appliquer(Clinique c) throws BudgetInsuffisant;
}