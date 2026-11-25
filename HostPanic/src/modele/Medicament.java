package modele;

/**
 * Énumération Medicament.
 * Chaque maladie se soigne avec son propre médicament.
 * La clinique achète les médicaments, et chaque soin en consomme un.
 * Pour l'instant, tous les médicaments coûtent 50 € (règle de la feuille de route),
 * mais chacun garde son propre prix pour pouvoir le changer plus tard.
 *
 * @author DJELGOU Hamidou
 * @version V2
 */
public enum Medicament {
    ANTIGRIPPAL(50),
    PLATRE(50),
    ANTICOAGULANT(50),
    REHYDRATANT(50),
    BANDAGE(50),
    BRONCHODILATATEUR(50),
    ANTIBIOTIQUE(50),
    ANESTHESIQUE(50),
    THROMBOLYTIQUE(50);

    /** Le prix d'un médicament, en euros. */
    private final int prix;

    /**
     * Crée un médicament avec son prix.
     *
     * @param prix le prix d'un médicament, en euros
     * @author DJELGOU Hamidou
     */
    Medicament(int prix) {
        this.prix = prix;
    }

    /**
     * Donne le prix d'un médicament.
     *
     * @return le prix, en euros
     * @author DJELGOU Hamidou
     */
    public int getPrix() {
        return this.prix;
    }
}
