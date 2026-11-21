// CORRECTION COMPILATION (21/11/2026) : fichier restaure depuis le dernier commit (ca4dc81).
// Il avait ete supprime alors que Patient et Soignant heritent de Personne.
package modele;
public abstract class Personne {
    private String nom;
    private String prenom;


    public Personne(String nom, String prenom){
        this.nom = nom;
        this.prenom = prenom;
    }
    
    public String getNom(){
        return this.nom;
    }

    public String getPrenom(){
        return this.prenom;
    }

}
