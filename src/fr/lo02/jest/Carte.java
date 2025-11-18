package fr.lo02.jest;
public class Carte {
	
    private Couleur couleur;
    private Valeur valeur;
    private StrategyTrophee strategyTrophee;

    public Carte (Valeur valeur, Couleur couleur, StrategyTrophee strategyTrophee) {
		this.setCouleur(couleur);
		this.setValeur(valeur);
		this.setStrategyTrophee(strategyTrophee);
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
    
    public void setStrategyTrophee(StrategyTrophee strategyTropheeArg) {
    	this.strategyTrophee = strategyTropheeArg;
    }
    
    public StrategyTrophee getStrategyTrophee() {
    	return this.strategyTrophee;
    }	
    /**
     * Permet de calculer, à partir de la liste des joueurs, et donc de leur jest
     * quel joueur ajoute cette carte dans son jest. Appelle la bonne méthode en fonction
     * de la stratégie donnée à la carte.
     * 
     * @param joueurs
     * @return
     */
    public Joueur executeStrategyTrophee(Joueur[] joueurs) {
    	
    	return this.strategyTrophee.execute(joueurs);
    }
    
    /**
     * Utilisé pour calculer les points rapportés par une carte en fonction d'une règle précise passée en argument.
     * Les points seront stockés dans l'instance de la règle et récupérée par le joueur une fois toutes les cartes
     * passées dans cette règle.
     * 
     * @param visitor
     */
    public void acceptVisitor(Visitor visitor) {
    	visitor.visitCarte(this);
    }
    
    /**
     * Renvoie la carte sous forme de chaine de caractères affichable.
     */
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
