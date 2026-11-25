package evenement;

import modele.Clinique;
import modele.Maladie;
import modele.Patient;

import static modele.Maladie.GRIPPE;

/**
 * Épidémie de grippe : cinq patients grippés arrivent d'un coup à la clinique.
 * <p>
 * Cet événement est le seul à créer des patients avec une maladie imposée
 * (la grippe). Les patients sont ensuite accueillis par la méthode
 * {@link Clinique#accueillir(Patient)}.
 *
 * @author
 * @version V2
 */
public class EpidemieGrippe extends Evenement {

    /** Nombre de patients grippés qui arrivent d'un coup. Valeur fixe. */
    private static final int NB_PATIENTS = 5;

    /**
     * Crée l'événement « épidémie de grippe ».
     *
     * @author Mouhamadou Kpaka
     */
    public EpidemieGrippe() {
        super("Une épidémie de grippe frappe la ville : cinq patients arrivent.");
    }

    /**
     * Crée cinq patients atteints de la grippe et les accueille dans la clinique.
     * <p>
     * La création passe par le constructeur de {@link Patient} qui impose
     * la maladie. La clinique se charge ensuite de les enregistrer.
     *
     * @param c la clinique qui accueille les patients
     * @author
     */

    @Override
    public void appliquer(Clinique c) {
        c.augmenterCapacite(NB_PATIENTS);
        for (int i = 0; i < NB_PATIENTS; i++) {
            Patient p = new Patient("p", "P", GRIPPE, 2);
            c.accueillir(p);
        }
    }
}