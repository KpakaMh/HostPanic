package modele;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests de l'énumération Maladie : durée de soin et prime de chaque maladie.
 *
 * @author Fangnon Akouavi
 */
public class MaladieTest {

    @Test
    public void grippeDure1JourEtRapporte300() {
        assertEquals(1, Maladie.GRIPPE.getDureeSoin());
        assertEquals(300, Maladie.GRIPPE.getPrime());
    }

    @Test
    public void fractureDure2JoursEtRapporte600() {
        assertEquals(2, Maladie.FRACTURE.getDureeSoin());
        assertEquals(600, Maladie.FRACTURE.getPrime());
    }

    @Test
    public void infarctusDure3JoursEtRapporte1200() {
        assertEquals(3, Maladie.INFARCTUS.getDureeSoin());
        assertEquals(1200, Maladie.INFARCTUS.getPrime());
    }
}