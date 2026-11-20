package modele;

/**
 * Enumeration Maladie
 * Chaque maladie nécessite un nombre donné de jour pour être traitée
 * A chaque maladoie est associée une prime si elle a été traitée
 *
 * @author Fangnon Akouavi
 */

public enum Maladie {
    GRIPPE(1, 300),
    FRACTURE(2, 600),
    INFARCTUS(3, 1200);

    private final int dureeSoin;
    private final int prime;

    Maladie(int dureeSoin, int prime){
        this.dureeSoin = dureeSoin;
        this.prime = prime;
    }

    private int getDureeSoin(){
        return this.dureeSoin;
    }

    private int getPrime(){
        return this.prime;
    }
}
