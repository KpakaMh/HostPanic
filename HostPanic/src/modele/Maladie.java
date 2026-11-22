package modele;

/**
 * Énumération Maladie.
 * Chaque maladie nécessite un nombre donné de jours de soin pour être guérie.
 * À chaque maladie est associée une prime, encaissée par la clinique à la guérison.
 *
 * @author Fangnon Akouavi
 */
public enum Maladie {
    GRIPPE(1, 300),
    FRACTURE(2, 600),
    INFARCTUS(3, 1200);

    private final int dureeSoin;
    private final int prime;

    Maladie(int dureeSoin, int prime) {
        this.dureeSoin = dureeSoin;
        this.prime = prime;
    }

    /** @return le nombre de jours de soin nécessaires pour guérir */
    public int getDureeSoin() {
        return this.dureeSoin;
    }

    /** @return la prime (en €) encaissée quand le patient est guéri */
    public int getPrime() {
        return this.prime;
    }
}