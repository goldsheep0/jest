package fr.lo02.jest;
public class Carte {
	
    private Couleur couleur;
    private Valeur valeur;

    public Carte (Valeur valeur, Couleur couleur) {
		this.setCouleur(couleur);
		this.setValeur(valeur);
    }

    public Couleur getCouleur() {
    	return couleur;
    }

    public void setCouleur(Couleur couleur) {
    	this.couleur = couleur;
	}

    public Valeur getValeur() {
    	return valeur;
    }

    public void setValeur(Valeur valeur) {
	    this.valeur = valeur;
    }

    public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.valeur);
		sb.append(" de ");
		sb.append(this.couleur);
		return sb.toString();
    }   
	
    public static void main(String[]args){


    }
}
