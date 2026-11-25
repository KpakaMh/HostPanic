package modele;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Tests de la classe Patient (et de Personne, via le nom et le prénom).
 * Les patients reprennent l'exemple du jour 1 de la feuille de route : Awa, Paul et Lina.
 *
 * @author Fangnon Akouavi
 */
public class PatientTest {

    @Test
    public void deuxPatientsOntDesNumerosQuiSeSuivent() {
        Patient awa = new Patient("Diallo", "Awa", Maladie.GRIPPE, 2);
        Patient paul = new Patient("Martin", "Paul", Maladie.FRACTURE, 3);
        assertEquals(awa.getNumero() + 1, paul.getNumero());
    }

    @Test
    public void nouveauPatientADureeDeSoinDeSaMaladie() {
        Patient lina = new Patient("Moreau", "Lina", Maladie.INFARCTUS, 4);
        assertEquals(3, lina.getJoursRestants());
        assertEquals(4, lina.getGravite());
    }

    @Test
    public void aggraverAugmenteLaGraviteDe1() {
        Patient paul = new Patient("Martin", "Paul", Maladie.FRACTURE, 3);
        paul.aggraver();
        assertEquals(4, paul.getGravite());
        assertFalse(paul.estDecede());
    }

    @Test
    public void patientDecedeAGravite5() {
        Patient lina = new Patient("Moreau", "Lina", Maladie.INFARCTUS, 4);
        lina.aggraver();
        assertEquals(5, lina.getGravite());
        assertTrue(lina.estDecede());
    }

    @Test
    public void graviteNeDepassePas5() {
        Patient lina = new Patient("Moreau", "Lina", Maladie.INFARCTUS, 4);
        lina.aggraver();
        lina.aggraver();
        assertEquals(5, lina.getGravite());
    }

    @Test
    public void recevoirSoinBaisseLaDureeRestanteDe1() {
        Patient lina = new Patient("Moreau", "Lina", Maladie.INFARCTUS, 4);
        lina.recevoirSoin();
        assertEquals(2, lina.getJoursRestants());
        assertFalse(lina.estGueri());
    }

    @Test
    public void fractureGuerieApresDeuxSoins() {
        Patient paul = new Patient("Martin", "Paul", Maladie.FRACTURE, 3);
        paul.recevoirSoin();
        paul.recevoirSoin();
        assertEquals(0, paul.getJoursRestants());
        assertTrue(paul.estGueri());
    }

    @Test
    public void soinNeModifiePasLaGravite() {
        Patient awa = new Patient("Diallo", "Awa", Maladie.GRIPPE, 2);
        awa.recevoirSoin();
        assertEquals(2, awa.getGravite());
    }

    @Test
    public void getNomEtGetPrenomRenvoientLesValeursDonnees() {
        Patient awa = new Patient("Diallo", "Awa", Maladie.GRIPPE, 2);
        assertEquals("Diallo", awa.getNom());
        assertEquals("Awa", awa.getPrenom());
    }

    @Test
    public void graviteHorsDe1a4EstRefuseeALaCreation() {
        assertThrows(IllegalArgumentException.class,
                () -> new Patient("Martin", "Paul", Maladie.FRACTURE, 7));
        assertThrows(IllegalArgumentException.class,
                () -> new Patient("Martin", "Paul", Maladie.FRACTURE, 5));
        assertThrows(IllegalArgumentException.class,
                () -> new Patient("Martin", "Paul", Maladie.FRACTURE, 0));
    }

    @Test
    public void patientAuHasardArriveAvecGravite1a4() {
        for (int i = 0; i < 100; i++) {
            Patient p = new Patient("Test", "Hasard");
            assertTrue(p.getGravite() >= 1 && p.getGravite() <= 4);
        }
    }

    @Test
    public void arriveeDuMatinFaitVenir1a3Patients() {
        for (int i = 0; i < 100; i++) {
            List<Patient> arrivees = Patient.arriveeDuMatin();
            assertTrue(arrivees.size() >= 1 && arrivees.size() <= 3);
        }
    }
}