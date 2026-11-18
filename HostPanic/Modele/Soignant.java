package Modele;

public class Soignant {
    private int salaire;
}
public Soignant(String nom, int salaire) {
        super(nom);
        this.salaire = salaire;
    }

public int getSalaire() {
        return this.salaire;
    }

    public abstract boolean peutSoigner(Patient p);{
}