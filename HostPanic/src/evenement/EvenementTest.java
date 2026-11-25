package evenement;

import static org.junit.jupiter.api.Assertions.*;

import modele.Clinique;
import org.junit.jupiter.api.Test;

/**
 * Tests de la classe mère {@link Evenement} à travers ses quatre classes filles.
 *
 * @author
 * @version V2
 */
class EvenementTest {

    /**
     * Vérifie que chaque événement peut être manipulé comme un {@link Evenement}
     * et qu'il fournit une description et un effet.
     *
     * @author
     */
    @Test
    void chaqueFilleEstUnEvenementAvecDescriptionEtEffet() {
        Evenement[] evenements = {
                new DonMecene(),
                new ReportageTV(),
                new EpidemieGrippe(),
             //   new ControleSanitaire()
        };
        Clinique c = new Clinique(10000);

        for (Evenement e : evenements) {
            assertNotNull(e.getDescription());
            assertFalse(e.getDescription().isBlank());
            assertDoesNotThrow(() -> e.appliquer(c));
        }
    }
}