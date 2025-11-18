package fr.lo02.jest.regle.calculepoint;

import fr.lo02.jest.Carte;
import java.util.*;
import fr.lo02.jest.enums.*;
import fr.lo02.jest.regle.attributionTrophees.StrategyTrophee;
import fr.lo02.jest.regle.attributionTrophees.StrategyTropheeNull;

public class ReglePairesNoiresVisitor implements Visitor{
	/*
	 * l'attribut totalPoint n'a pas besoin de setter car sa valeur
	 * n'est modifié que par les méthode de la règle.
	 */
	private int totalPoint;
	
	private HashSet<Valeur> setValeursNoiresVisitees; //Attribut maniuplé uniquement par la class
	
	public ReglePairesNoiresVisitor() {
		this.totalPoint=0;
		this.setValeursNoiresVisitees=new HashSet<Valeur>();
	}
	
	public int getTotalPoint() {
		return totalPoint;
	}
	
	/**
	 * Une paire de couleur noire rajoute 2 points à la valeur du jest
	 * 
	 * @param carte
	 */
	public void visitCarte(Carte carte) {
		if(carte.getCouleur()==Couleur.PIQUE||carte.getCouleur()==Couleur.TREFLE) {
			if(this.setValeursNoiresVisitees.contains(carte.getValeur())) {
				this.totalPoint+=2;
				this.setValeursNoiresVisitees.remove(carte.getValeur());
			}else {
				this.setValeursNoiresVisitees.add(carte.getValeur());
			}
		}	
	}
	
	/**
	 * Permet de test la classe avec différents cas :
	 * On s'attend à ce que une paire de cartes noires (pique et trefle) rajoute 2 points au jest, par paire.
	 * Aucune paire ne rajoute aucun point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		StrategyTrophee strat= new StrategyTropheeNull();
		
		Carte joker = new Carte(Valeur.JOKER,Couleur.JOKER,strat);
		Carte coeurAs = new Carte(Valeur.AS,Couleur.COEUR,strat);;
		Carte coeur2 = new Carte(Valeur.DEUX,Couleur.COEUR,strat);;
		Carte coeur3 = new Carte(Valeur.TROIS,Couleur.COEUR,strat);;
		Carte coeur4 = new Carte(Valeur.QUATRE,Couleur.COEUR,strat);;
		
		
		Carte trefle3 = new Carte(Valeur.TROIS,Couleur.TREFLE,strat);
		Carte pique3 = new Carte(Valeur.TROIS,Couleur.PIQUE,strat);
		
		Carte trefle2 = new Carte(Valeur.DEUX,Couleur.TREFLE,strat);
		Carte pique2 = new Carte(Valeur.DEUX,Couleur.PIQUE,strat);

		
		Carte carreau3 = new Carte(Valeur.TROIS,Couleur.CARREAU,strat);
		Carte piqueAs = new Carte(Valeur.AS,Couleur.PIQUE,strat);
		Carte trefle4 = new Carte(Valeur.QUATRE,Couleur.TREFLE,strat);
		
		
		LinkedList<Carte> jest1 = new LinkedList<Carte>();
		
		jest1.add(trefle3);
		jest1.add(joker);
		jest1.add(piqueAs);
		jest1.add(pique2);
		jest1.add(trefle2);
		
		ReglePairesNoiresVisitor regle = new ReglePairesNoiresVisitor();
		
		Iterator<Carte> it = jest1.iterator();
		while(it.hasNext()) {
			it.next().acceptVisitor(regle);
		}
		System.out.println(regle.getTotalPoint());
		

		
		
	}
}