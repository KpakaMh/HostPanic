package modele;

import static modele.Maladie.GRIPPE;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests de la méthode {@link Clinique#accueillir(Patient)} et de la capacité d'accueil.
 * <p>
 * Ces tests ne supposent pas de valeur précise pour la capacité de départ :
 * ils remplissent la clinique jusqu'au refus, puis vérifient le comportement
 * autour de la limite.
 *
 * @author
 * @version V2
 */
class CliniqueTest {

    /** Borne de sécurité pour éviter une boucle infinie si la clinique n'a pas de limite. */
    private static final int BORNE_SECURITE = 1000;

    /**
     * Essaie d'accueillir des patients jusqu'à ce que la clinique refuse.
     *
     * @param c la clinique à remplir
     * @return le nombre de patients effectivement accueillis
     * @author
     */
    private int remplir(Clinique c) {
        int accueillis = 0;
        while (accueillis < BORNE_SECURITE && c.accueillir(new Patient("Ptient","petit", GRIPPE, 2))) {
            accueillis++;
        }
        return accueillis;
    }

    /**
     * Vérifie qu'un patient est accueilli quand la clinique a de la place.
     *
     * @author
     */
    @Test
    void accueilleUnPatientQuandLaCliniqueAUnePlace() {
        Clinique c = new Clinique(10000);
        int avant = c.getPatients().size();

        boolean accueilli = c.accueillir(new Patient("Patient", "Petit", GRIPPE, 3));

        assertTrue(accueilli);
        assertEquals(avant + 1, c.getPatients().size());
    }

    /**
     * Vérifie qu'un patient est refusé quand la clinique est pleine,
     * et qu'il n'est pas ajouté à la liste.
     *
     * @author Mouhamadou Kpaka
     */
    @Test
    void refuseUnPatientQuandLaCliniqueEstPleine() {
        Clinique c = new Clinique(10000);
        int accueillis = remplir(c);
        assertTrue(accueillis < BORNE_SECURITE, "La clinique doit finir par refuser");

        boolean accueilli = c.accueillir(new Patient("p", "P", GRIPPE, 4));

        assertFalse(accueilli);
        assertEquals(accueillis, c.getPatients().size());
    }

    /**
     * Vérifie qu'augmenter la capacité libère de nouvelles places,
     * et que la clinique refuse à nouveau une fois ces places occupées.
     *
     * @author
     */
    @Test
    void augmenterLaCapaciteRendDesPlacesDisponibles() {
        Clinique c = new Clinique(10000);
        int accueillis = remplir(c);

        c.augmenterCapacite(2);
//
        assertTrue(c.accueillir(new Patient("Patient", "Petit", GRIPPE, 4)));
        assertTrue(c.accueillir(new Patient("Patient", "Patient", GRIPPE, 2)));
        assertFalse(c.accueillir(new Patient("Patient", "Patient",GRIPPE, 3)));
        assertEquals(accueillis + 2, c.getPatients().size());
    }
}