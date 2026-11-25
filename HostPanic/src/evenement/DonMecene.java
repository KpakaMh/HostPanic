package evenement;

import modele.Clinique;

/**
 * Événement représentant le don d'un mécène à la clinique.
 * Augmente le budget de la clinique d'un montant fixe.
 * @author Desire Dady
 */
public class DonMecene extends Evenement {

    private final static int Montant_Don = 1500;

    /**
     * Crée un événement de don d'un mécène avec sa description par défaut.
     */
    public DonMecene() {
        super("un généreux mécene a décider de faire un don de 1500 euros a la clinique");
    }

    /**
     * Ajoute le montant du don au budget de la clinique.
     *
     * @param c il s'agit de la clinique qui reçoit le don
     */
    @Override
    public void appliquer(Clinique c) {
        c.modifierBudget(Montant_Don);
    }
}