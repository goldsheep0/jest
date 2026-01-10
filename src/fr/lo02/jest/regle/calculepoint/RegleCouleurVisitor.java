package fr.lo02.jest.regle.calculepoint;

import java.util.Iterator;
import java.util.LinkedList;

import fr.lo02.jest.Carte;
import fr.lo02.jest.enums.*;
import fr.lo02.jest.regle.attributionTrophees.StrategyTrophee;
import fr.lo02.jest.regle.attributionTrophees.StrategyTropheeNull;

/**
 * Règle de calcul des point pour les cartes en fonction de leur couleur.
 */
public class RegleCouleurVisitor implements Visitor{
	
	/**
	 * Contient le total des points après que chaque carte du jest d'un joueur a visité cette carte.
	 */
	private int totalPoint;
	
	public RegleCouleurVisitor() {
		this.totalPoint=0;
	}
	
	public int getTotalPoint() {
		return totalPoint;
	}
	
	
	/**
	 * Cette fonction permet de rajouter de la valeur au jest en fonction de la couleur des cartes présente à l'intérieur :<br>
	 * Pique et tréfle : ajoute sa valeur à la valeur du jest<br>
	 * Carreau : retire sa valeur à la valeur du jest
	 * 
	 * @param carte
	 */
	public void visitCarte(Carte carte) {
		/*
		 * On utilise ordinal() car les enum sont placés dans un ordre qui leur donne les bonnes valeurs
		 */
		switch(carte.getCouleur()) {
			case Couleur.PIQUE:
				this.totalPoint += carte.getValeur().ordinal();
				break;
			case Couleur.TREFLE:
				this.totalPoint += carte.getValeur().ordinal();
				break;
			case Couleur.CARREAU:
				this.totalPoint -= carte.getValeur().ordinal();
				break;
			default:
				break; //Si la couleur n'est ni trèfle, ni pique, ni carreau, aucune valeur n'est changée.
		}
		
		
	}
	
	/**
	 * Permet de test la classe avec différents cas qui sont :<br>
	 * Une carte pique : ajoute sa valeur au jest<br>
	 * Une carte trefle : ajoute sa valeur au jest<br>
	 * Une carte carreau : soustrait sa valeur au jest<br>
	 * Les coeurs et joker ne valent rien car ils sont calculés dans d'autres règles.<br>
	 * Les As ne valent qu'un point car leur règle spécifique détermine si ils n'en vaudront qu'1 ou si 4 points seront ajoutés.
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
		
		jest1.add(joker);
		jest1.add(coeur4);
		jest1.add(piqueAs);
		jest1.add(pique2);
		jest1.add(trefle3);
		
		RegleCouleurVisitor regle = new RegleCouleurVisitor();
		
		Iterator<Carte> it = jest1.iterator();
		while(it.hasNext()) {
			it.next().acceptVisitor(regle);
		}
		System.out.println(regle.getTotalPoint());
		

		
		
	}
	
}
