package modele;

/**
 * Énumération Maladie.
 * Chaque maladie nécessite un nombre donné de jours de soin pour être guérie.
 * À chaque maladie est associée une prime, encaissée par la clinique à la guérison.
 * Chaque maladie se soigne avec son propre médicament (ajout V2).
 *
 * @author Fangnon Akouavi
 * @author DJELGOU Hamidou (ajout du médicament, V2)
 */
public enum Maladie {
    GRIPPE(1, 300, Medicament.ANTIGRIPPAL),
    GASTRO_ENTERITE(1, 250, Medicament.REHYDRATANT),
    ENTORSE(1, 250, Medicament.BANDAGE),
    CRISE_ASTHME(1, 350, Medicament.BRONCHODILATATEUR),
    MENINGITE(2, 650, Medicament.ANTIBIOTIQUE),
    FRACTURE(2, 600, Medicament.PLATRE),
    PNEUMONIE(2, 700, Medicament.ANTIBIOTIQUE),
    APPENDICITE(2, 800, Medicament.ANESTHESIQUE),
    INFARCTUS(3, 1200, Medicament.ANTICOAGULANT),
    AVC(3, 1300, Medicament.THROMBOLYTIQUE);

    private final int dureeSoin;
    private final int prime;
    /** Le médicament qui soigne cette maladie (ajout V2). */
    private final Medicament medicament;

    Maladie(int dureeSoin, int prime, Medicament medicament) {
        this.dureeSoin = dureeSoin;
        this.prime = prime;
        this.medicament = medicament;
    }

    /** @return le nombre de jours de soin nécessaires pour guérir */
    public int getDureeSoin() {
        return this.dureeSoin;
    }

    /** @return la prime (en €) encaissée quand le patient est guéri */
    public int getPrime() {
        return this.prime;
    }

    /**
     * Donne le médicament qui soigne cette maladie.
     *
     * @return le médicament de cette maladie
     * @author DJELGOU Hamidou
     */
    public Medicament getMedicament() {
        return this.medicament;
    }
}