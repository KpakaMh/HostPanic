package modele;

// CORRECTION COMPILATION (21/11/2026) : import "Modele.Soignant" supprime (package inexistant, Soignant est dans le meme package).

/**
 * Représente un infirmier
 * peut soigner seulement les gravités 1à3
 * n'importe quelle maladie
 * 
 * @author Valentin Painceira
*/
public class Infirmier extends Soignant {
    /** 
     * constructeur de l'infirmier avec le nom prenom et le salaire
     * 
     * caracteristique de l'infirmier
     * @param nom nom de famille
     * @param prenom prenom
     * @param salaire salaire par jour
     */
    public Infirmier(String nom, String prenom, int salaire){
        super(nom, prenom, salaire);
    }
    /**    
     * indique si l'infirmier peut soigner
     * selon ca limite de gravite 3 maximum
     * 
    * @param p patient examiner
     * @return true si 1;2
     * @return false si 3;4;5
     * 
     */
@Override 
public boolean peutSoigner (Patient p){
return p.getGravite() < 4;}}
