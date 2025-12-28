package fr.lo02.jest;
import fr.lo02.jest.regle.calculepoint.*;
import fr.lo02.jest.regle.attributionTrophees.*;

import java.io.Serializable;
import java.util.ArrayList;

import fr.lo02.jest.enums.*;

public class Carte implements Serializable{
	
    private Couleur couleur;
    private Valeur valeur;
    private StrategyTrophee strategyTrophee;
    private boolean faceVisible;
    
    /**
     * Constructeur de la classe Carte.
     * @param valeur
     * @param couleur
     * @param strategyTrophee
     */
    public Carte (Valeur valeur, Couleur couleur, StrategyTrophee strategyTrophee) {
		this.couleur=couleur;
		this.valeur=valeur;
		this.setStrategyTrophee(strategyTrophee);
		this.faceVisible = false;
    }

    public Couleur getCouleur() {
    	return couleur;
    }

    public Valeur getValeur() {
    	return valeur;
    }
    
    public boolean estFaceVisible() {
    	return faceVisible;
    }
    
    public void setFaceVisible(boolean ret) {
    	faceVisible = ret;
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
    public Joueur executeStrategyTrophee(ArrayList<Joueur> joueurs) {
    	
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
    
    public String toStringTrophee() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.toString());
		sb.append(this.strategyTrophee.toString());
		return sb.toString();
    }  
	
    public static void main(String[]args){


    }
    
    public Carte compareTo(Carte c) {
    	if (valeur.ordinal() > c.valeur.ordinal()) {
    		return this;
    	} else if (valeur.ordinal() < c.valeur.ordinal()) {
    		return c;
    	} else {
    		if (couleur.ordinal() < c.couleur.ordinal()) {
    			return this;
    		} else {
    			return c;
    		}
    	}
    }
}
