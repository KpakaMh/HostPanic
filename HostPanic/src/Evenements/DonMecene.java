package Evenements;

import modele.Clinique;

public class DonMecene extends Evenement {
    
    private final static int Montant_Don = 1500;
    @Override 
    public String getDescription(){
        return "Un merveilleux mécene a décider de faire un don de 1500 a la clinique";
    }@Override
    public void appliquer(Clinique c){
        c.modifierBudget (Montant_Don);}

}
