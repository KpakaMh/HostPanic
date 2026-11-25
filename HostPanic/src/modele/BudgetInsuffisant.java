package modele;

/**
 * Exception levée lorsque le budget de la clinique est insuffisant
 * pour effectuer une opération ou appliquer un événement.
 */
public class BudgetInsuffisant extends Exception {

    public BudgetInsuffisant(String message) {
        super(message);
    }
}