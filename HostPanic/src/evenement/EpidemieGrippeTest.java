package evenement;

import static org.junit.jupiter.api.Assertions.*;

import modele.Clinique;
import org.junit.jupiter.api.Test;

/**
 * Tests de {@link EpidemieGrippe} : description et arrivée de cinq patients.
 *
 * @author
 * @version V2
 */
class EpidemieGrippeTest {

    /**
     * Vérifie que la description n'est pas vide.
     *
     * @author
     */
    @Test
    void descriptionNonVide() {
        assertFalse(new EpidemieGrippe().getDescription().isBlank());
    }

    /**
     * Vérifie que l'épidémie ajoute exactement cinq patients à la clinique.
     * <p>
     * Ce test suppose que la capacité de départ de la clinique est au moins de 5 places.
     *
     * @author
     */
    @Test
    void ajouteCinqPatients() {
        Clinique c = new Clinique(10000);
        int avant = c.getPatients().size();

        new EpidemieGrippe().appliquer(c);

        assertEquals(avant + 5, c.getPatients().size());
    }
}