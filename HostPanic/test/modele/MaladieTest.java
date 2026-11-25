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
    public void grippe1Jour300EurosAntigrippal() {
        assertEquals(1, Maladie.GRIPPE.getDureeSoin());
        assertEquals(300, Maladie.GRIPPE.getPrime());
        assertEquals(Medicament.ANTIGRIPPAL, Maladie.GRIPPE.getMedicament());
    }

    @Test
    public void fracture2Jours600eurosPlatre() {
        assertEquals(2, Maladie.FRACTURE.getDureeSoin());
        assertEquals(600, Maladie.FRACTURE.getPrime());
        assertEquals(Medicament.PLATRE, Maladie.FRACTURE.getMedicament());
    }

    @Test
    public void infarctus3Jours1200eurosAnticoagulant() {
        assertEquals(3, Maladie.INFARCTUS.getDureeSoin());
        assertEquals(1200, Maladie.INFARCTUS.getPrime());
        assertEquals(Medicament.ANTICOAGULANT, Maladie.INFARCTUS.getMedicament());
    }
}