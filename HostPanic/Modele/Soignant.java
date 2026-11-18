package Modele;

public abstract class Soignant extends Personne {
    private int salaire;

    public Soignant(String nom, String prenom, int salaire) {
        super(nom,prenom);
        this.salaire = salaire;
    }

    public int getSalaire() {
        return this.salaire;
    }

    public abstract boolean peutSoigner(Patient p);
}