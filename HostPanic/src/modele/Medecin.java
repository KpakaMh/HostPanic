package modele;
/**
 * Représente un médecin au sein de la clinique.
 * Le médecin dispose de compétences avancées et peut soigner n'importe quel patient,
 * quelle que soit sa maladie ou sa gravité.
 * 
 * @author Valentin Painceira
 */
public class Medecin extends Soignant {

    /**
     * Constructeur permettant de créer un nouveau médecin avec son nom, son prénom et son salaire.
     * 
     * @param nom Le nom de famille du médecin
     * @param prenom Le prénom du médecin
     * @param salaire Le salaire journalier du médecin 
     */
    public Medecin(String nom, String prenom, int salaire) {
        super(nom, prenom, salaire);
    }

    /**
     * Indique si ce médecin peut prendre en charge un patient donné.
     * Le médecin étant polyvalent, il accepte tous les patients sans restriction.
     * 
     * @param p Le patient que l'on souhaite examiner
     * @return true dans tous les cas, car le médecin peut soigner tous les patients
     */
    @Override 
    public boolean peutSoigner(Patient p) {
        return true;
    }
}


// faire des sous classe medecin en f des maladie
// 
//true if (patientetudie getgrippe) && (Maladie) && (Maladie INfractus)
//    // faire que ca donne la recompense de soin soit donnet et que le patient parte
//
//else System.out.println("Le medecin n'a pas les competence pour soigner
//ce patient. Il retourne en salle d'attente" {

    
//}")
    // faire que le patient soit renvoyer en salle d'attente
    