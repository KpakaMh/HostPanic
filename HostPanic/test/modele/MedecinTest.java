package modele;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class MedecinTest {
    private Medecin medecin;

    @BeforeEach
    void setUp() {
        medecin = new Medecin("Leroy", "Marc", 320);
    }

    @Test
    void testConstructeurEtAttributs() {
        assertEquals("Leroy", medecin.getNom());
        assertEquals("Marc", medecin.getPrenom());
        assertEquals(320, medecin.getSalaire());
    }

    @Test
    void testPeutSoignerToutesGravitesEtMaladies() {
        Patient pGravite1 = new Patient("A", "B", Maladie.GRIPPE, 1, 2);
        Patient pGravite5 = new Patient("C", "D", Maladie.GRIPPE, 5, 8);

        assertTrue(medecin.peutSoigner(pGravite1));
        assertTrue(medecin.peutSoigner(pGravite5));
    }
}
