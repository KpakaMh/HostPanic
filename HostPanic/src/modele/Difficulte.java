package modele;

/**
 * Énumération Difficulte.
 * Le joueur choisit la difficulté au début de la partie.
 * Chaque difficulté fixe le budget de départ de la clinique.
 * Plus le budget de départ est petit, plus la partie est difficile.
 *
 * @author DJELGOU Hamidou
 * @version V2
 */
public enum Difficulte {
    FACILE(15000),
    NORMAL(10000),
    DIFFICILE(6000);

    /** Le budget de départ de la clinique, en euros, pour cette difficulté. */
    private final int budgetInitial;

    /**
     * Crée une difficulté avec son budget de départ.
     *
     * @param budgetInitial le budget de départ de la clinique, en euros
     * @author DJELGOU Hamidou
     */
    Difficulte(int budgetInitial) {
        this.budgetInitial = budgetInitial;
    }

    /**
     * Donne le budget de départ de la clinique pour cette difficulté.
     *
     * @return le budget de départ, en euros
     * @author DJELGOU Hamidou
     */
    public int getBudgetInitial() {
        return this.budgetInitial;
    }
}
