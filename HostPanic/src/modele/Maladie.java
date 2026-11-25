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
    FRACTURE(2, 600, Medicament.PLATRE),
    INFARCTUS(3, 1200, Medicament.ANTICOAGULANT);

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