package modele;

/**
 * Classe Patient de l'hôpital.
 * Un patient a un nom, un prénom, un numero unique d'identification, 
 * une maladie avec une gravité et un nombre de jours necessaires pour être guéri.
 *
 * @author Fangnon Akouavi
 */

public class Patient extends Personne {
    private static int COMPTEUR = 1;
    private final int numero;
    private Maladie maladie;
    private int gravite;
    private int joursRestants;


    public Patient(String nom, String prenom, int numero, Maladie maladie, int gravite, int joursRestants){
        super(nom, prenom);
        this.numero = COMPTEUR++;
        this.maladie = maladie;
        this.gravite = gravite;
        this.joursRestants = joursRestants;

    }

    /* Les accesseurs pour acceder aux attributs */
    public int getNumero(){
        return this.numero;
    }

    public Maladie getMaladie(){
        return this.maladie;
    }

    public int getGravite(){
        return this.gravite;
    }

    public int GetJoursRestants(){
        return this.joursRestants;
    }

    /**
     * Lorsqu'un patient recoit un soin, sa gravite diminue de 1
     * La gravité ne peut jamais descendre en dessous de 0
     */
    public void recevoirSoin(){
        this.gravite = this.gravite - 1;

        if (this.gravite < 0){
            this.gravite = 0;
        }
    }

    /** L'etat du patient s'aggrave s'il n'est pas pris en charge jusqu'au lendemain (partie+1)
     * La graviténe peut jamais dépasser 5
    */
    public void aggraver(){
        this.gravite = this.gravite + 1 ;

        if (this.gravite > 5){
            this.gravite = 5;
        }
    }

    /**
     * Le patient est gueri si sa gravitédescend à 0 
     * @return true si le patient est guéri, et non sinon
    */
    public boolean estGueri(){
        return this.gravite == 0;

    }

    /**
     * Le patient décède si sa gravité atteint 5 
     * @return True si le patient décède
    */
    public boolean estDecede(){
        return this.gravite == 5;

    }




}
