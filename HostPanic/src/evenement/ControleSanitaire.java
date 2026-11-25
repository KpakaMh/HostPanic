package evenement;

import java.util.Random;
import modele.Clinique;

/**
 * Représente la classe ControleSanitaire, un événement
 * où le joueur subit une pénalité si l'hôpital n'est pas nettoyé,
 * ou gagne en réputation si le contrôle est réussi.
 *
 * @author Valentin Painceira
 */
public class ControleSanitaire extends Evenement {

    private static final int AMENDE = -300;
    private static final int GAIN_REPUTATION = 10;
    private final Random random;

    /**
     * Constructeur permettant de créer un
     * nouveau contrôle sanitaire pouvant
     * donner une amende ou un gain de réputation
     * aléatoirement.
     *
     * @param Description ce que fait l'événement
     */
    public ControleSanitaire(String Description) {
        super(Description);
        this.random = new Random();
    }

    @Override
    public String getDescription() {
        return "controle sanitaire vous devez nettoyer l'hopital";
    }

    /**
     * @param c qui correspond à la clinique
     * - pour V1 au hasard, voir après :
     * - réécrire des caractères ex (ZQSSSDDDZZQ) dans un temps donné
     * - gagner => réputation
     * - perdre => amende
     * - nombre à faire en fonction de la difficulté
     */
    @Override
    public void appliquer(Clinique c) {
        if (this.random.nextBoolean()) {
            c.modifierReputation(GAIN_REPUTATION);
        } else {
            c.modifierBudget(AMENDE);
        }
    }
}
