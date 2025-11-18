package fr.lo02.jest.regle.calculepoint;

import fr.lo02.jest.Carte;
import java.util.*;
import fr.lo02.jest.enums.*;
import fr.lo02.jest.regle.attributionTrophees.*;

public class RegleAsVisitor implements Visitor{
	/*
	 * l'attribut totalPoint n'a pas besoin de setter car sa valeur
	 * n'est modifié que par les méthode de la règle.
	 */
	private int totalPoint;
	private LinkedList<Carte> jest;
	
	/**
	 * Constructeur de la classe RegleAsVisitor
	 * 
	 *\/!\ PAS PASSER OBJECT JEST EN ARGUMENT
	 * @param LinkedList<Carte> Jest
	 */
	public RegleAsVisitor(LinkedList<Carte> jestJoueur) {
		this.totalPoint=0;
		this.jest=jestJoueur;
	}
	
	public int getTotalPoint() {
		return totalPoint;
	}
	
	/**
	 * Ajoute 4 points à la valeur d'un jocker si le jocker est la seule carte de sa couleur
	 * dans le jest. 
	 * La règle du jeu stipule qu'un jocker vaut 5 points si il est le seul de sa couleur et 1
	 * point sinon. On considère ici que la valeur par défaut d'un jocker est 1 avec une valeur
	 * additionelle de 4 si il est le seul de sa couleur.
	 * 
	 * @param carte
	 */
	public void visitCarte(Carte carte) {
		if(carte.getValeur()==Valeur.AS) {
			this.jest.remove(carte);
			boolean hasNoSameFace=true; //Tant que l'as est la seule carte de sa couleur dans le jest, cette variable reste à true
			Iterator<Carte> it = this.jest.iterator();
			while(it.hasNext()) {
				if(it.next().getCouleur()==carte.getCouleur()) {
					hasNoSameFace=false;
				}
			}
			if(hasNoSameFace) {
				totalPoint+=4;
			}
		}
	}
	
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
		jest1.add(coeur3);
		jest1.add(coeur2);
		jest1.add(coeurAs);
		
		RegleAsVisitor regle = new RegleAsVisitor(jest1);
		
		Iterator<Carte> it = jest1.iterator();
		while(it.hasNext()) {
			it.next().acceptVisitor(regle);
		}
		System.out.println(regle.getTotalPoint());


		
		
	}
}