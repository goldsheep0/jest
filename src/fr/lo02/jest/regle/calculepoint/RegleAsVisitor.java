package fr.lo02.jest.regle.calculepoint;

import fr.lo02.jest.Carte;

import java.io.Serializable;
import java.util.*;
import fr.lo02.jest.enums.*;
import fr.lo02.jest.regle.attributionTrophees.*;

/**
 * Règle de calcul des point pour les As.
 */
public class RegleAsVisitor implements Visitor{
	
	/**
	 * Contient le total des points après que chaque carte du jest d'un joueur a visité cette carte.
	 */
	private int totalPoint;
	
	/**
	 * Contient une référence vers les cartes du jest du joueur dont on est en train de calculer les points.
	 */
	private LinkedList<Carte> jest;
	
	/**
	 * Constructeur de la classe RegleAsVisitor
	 * 
	 * /!\ NE PAS PASSER L'OBJET JEST EN ARGUMENT
	 * @param jestJoueur
	 */
	public RegleAsVisitor(LinkedList<Carte> jestJoueur) {
		this.totalPoint=0;
		this.jest=jestJoueur;//Copie le pointeur vers jestJoueur. Attention toute modification sur this.jest affectera jestJoueur
	}
	
	public int getTotalPoint() {
		return totalPoint;
	}
	
	/**
	 * Ajoute 4 points à la valeur d'un jocker si le jocker est la seule carte de sa couleur dans le jest.<br>
	 * La règle du jeu stipule qu'un jocker vaut 5 points si il est le seul de sa couleur et 1 point sinon.<br>
	 * On considère ici que la valeur par défaut d'un jocker est 1 avec une valeur additionelle de 4 si il est le seul de sa couleur.
	 * 
	 * @param carte
	 */
	public void visitCarte(Carte carte) {
		if(carte.getValeur()==Valeur.AS) {
			boolean hasNoSameFace; //Tant que l'as est la seule carte de sa couleur dans le jest, cette variable reste à true
			Iterator<Carte> it1;
			it1= this.jest.iterator();
			hasNoSameFace=true;
			Carte currentCarte;
			while(it1.hasNext()) {
				currentCarte = it1.next();
				if(currentCarte.getCouleur()==carte.getCouleur()&&currentCarte.getValeur()!=carte.getValeur()) {
					hasNoSameFace=false;
				}
			}
			if(hasNoSameFace) {
				totalPoint+=4;
			}
		}
	}
	
	
	/**
	 * Permet de test la classe avec différents cas qui sont :<br>
	 * Un as et aucune carte de la même couleur : +4 point sur le jest<br>
	 * Un as et au moins une carte de la même couleur : 0 point<br>
	 * Aucun as : 0 point
	 * @param args
	 */
	public static void main(String[] args) {

		StrategyTrophee strat= new StrategyTropheeNull();
		
		Carte joker = new Carte(Valeur.JOKER,Couleur.JOKER,strat);
		Carte coeurAs = new Carte(Valeur.AS,Couleur.COEUR,strat);;
		Carte coeur2 = new Carte(Valeur.DEUX,Couleur.COEUR,strat);;
		Carte coeur3 = new Carte(Valeur.TROIS,Couleur.COEUR,strat);;
		Carte coeur4 = new Carte(Valeur.QUATRE,Couleur.COEUR,strat);;
		
		Carte piqueAs = new Carte(Valeur.AS,Couleur.PIQUE,strat);
		Carte carreau3 = new Carte(Valeur.TROIS,Couleur.CARREAU,strat);
		Carte trefle4 = new Carte(Valeur.QUATRE,Couleur.TREFLE,strat);
		Carte pique2 = new Carte(Valeur.DEUX,Couleur.PIQUE,strat);
		Carte trefle3 = new Carte(Valeur.TROIS,Couleur.TREFLE,strat);
		
		LinkedList<Carte> jest1 = new LinkedList<Carte>();
		
		jest1.add(coeurAs);
		jest1.add(carreau3);
		jest1.add(trefle4);
		jest1.add(trefle3);
		jest1.add(piqueAs);
		
		RegleAsVisitor regle = new RegleAsVisitor(jest1);
		
		Iterator<Carte> it = jest1.iterator();
		while(it.hasNext()) {
			it.next().acceptVisitor(regle);
		}
		System.out.println(regle.getTotalPoint());


		
		
	}
}