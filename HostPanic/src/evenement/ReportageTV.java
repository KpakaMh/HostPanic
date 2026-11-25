package evenement;

import modele.Clinique;

/**
 * Événement bonus : un reportage TV flatteur sur la clinique.
 * La réputation de la clinique augmente de 15 points.
 *
 * @author Fangnon Akouavi
 */
public class ReportageTV extends Evenement {

    /** Points de réputation gagnés grâce au reportage. */
    private static final int GAIN_REPUTATION = 15;

    /** Crée l'événement avec son texte. */
    public ReportageTV() {
        super("Un reportage TV flatteur parle de votre clinique : +15 de réputation.");
    }

    @Override
    public void appliquer(Clinique c) {
        c.modifierReputation(GAIN_REPUTATION);
    }
}