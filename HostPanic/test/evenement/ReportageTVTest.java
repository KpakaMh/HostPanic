package evenement;

import modele.Clinique;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


/**
 * Tests de l'événement ReportageTV.
 *
 * @author Fangnon Akouavi
 */
public class ReportageTVTest {

    private Clinique clinique;
    private ReportageTV reportage;

    /** Avant chaque test : une clinique neuve (10 000 €, réputation 50). */
    @BeforeEach
    public void preparer() {
        clinique = new Clinique(10000);
        reportage = new ReportageTV();
    }

    @Test
    public void reportageAugmenteLaReputationDe15() {
        reportage.appliquer(clinique);
        assertEquals(65, clinique.getReputation());
    }

    @Test
    public void reportageNeChangePasLeBudget() {
        reportage.appliquer(clinique);
        assertEquals(10000, clinique.getBudget());
    }

    @Test
    public void reportageAUneDescription() {
        assertNotNull(reportage.getDescription());
    }
}