package modele;

/**
 * Exception levée lorsque le budget de la clinique est insuffisant
 * pour effectuer une opération ou appliquer un événement.
 * @author Desire Dady
 */
public class BudgetInsuffisant extends Exception {
/**
     * Construit une nouvelle exception avec un message détaillant
     * la raison de l'insuffisance budgétaire.
     *
     * @param message le message explicatif de l'erreur
     */
    public BudgetInsuffisant(String message) {
        super(message);
    }
}