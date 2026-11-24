package modele;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//** test unitaire


public class InfirmierTest {

    private Infirmier infirmier;

    @BeforeEach
    void setup() {
        infirmier = new Infirmier("antony", "fo", 9);
    }

    @Test
    void testGetterSetter() {
        assertEquals("antony", infirmier.getPrenom());
        assertEquals("fo", infirmier.getNom());
        assertEquals(9, infirmier.getSalaire());
    }

    @Test
    void testAcceptGraviteLimite() {
        // Adapte les paramètres selon le constructeur exact de ta classe Patient
        Patient patient1 = new Patient("aaa", "bbb", Maladie.GRIPPE, 1, 6);
        Patient patientgrave = new Patient("cccc", "dddd", Maladie.GRIPPE, 5, 6);

        assertTrue(infirmier.peutSoigner(patient1));
        assertFalse(infirmier.peutSoigner(patientgrave));
    }
}