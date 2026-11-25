package evenement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import modele.Clinique;

class DonMeceneTest {

    @Test
    void testGetDescriptionNonVide() {
        Evenement don = new DonMecene();
        assertFalse(don.getDescription().isBlank());
    }

    @Test
    void testAppliquerAjoute1500AuBudget() {
        Clinique clinique = new Clinique(1000);
        Evenement don = new DonMecene();

        don.appliquer(clinique);

        assertEquals(2500, clinique.getBudget());
    }
}