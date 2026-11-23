package modele;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**
 * Tests unitaires de la classe Soignant.
 * Comme Soignant estune  abstraite c'est a dire que on l'utilise pas en tant que tel dans le code  
 * les tests passent par une classe fille
 * (PseudoSoignant) qui n'existe que pour ce fichier.
 *
 * Auteur : Desiré Dady
 */
public class SoignantTest {

    private static class PseudoSoignant extends Soignant {
        public PseudoSoignant(String nom, String prenom, int salaire) {
            super(nom, prenom, salaire);
        }

        @Override
        public boolean peutSoigner(Patient p) {
            return true;
        }
    }

    @Test
    public void leConstructeurRangeLeSalaire() {
        Soignant s = new PseudoSoignant("Test", "Jean", 150);
        assertEquals(150, s.getSalaire());
    }

    @Test
    public void leConstructeurTransmetNomEtPrenom() {
        Soignant s = new PseudoSoignant("Test", "Jean", 150);
        assertEquals("Test", s.getNom());
        assertEquals("Jean", s.getPrenom());
    }

    @Test
    public void deuxSoignantsOntChacunLeurSalaire() {
        Soignant a = new PseudoSoignant("A", "A", 100);
        Soignant b = new PseudoSoignant("B", "B", 999);
        assertEquals(100, a.getSalaire());
        assertEquals(999, b.getSalaire());
    }
}
