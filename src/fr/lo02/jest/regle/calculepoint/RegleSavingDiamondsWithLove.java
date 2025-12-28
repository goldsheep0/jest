package fr.lo02.jest.regle.calculepoint;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import fr.lo02.jest.Carte;
import fr.lo02.jest.enums.*;
import fr.lo02.jest.regle.attributionTrophees.StrategyTrophee;
import fr.lo02.jest.regle.attributionTrophees.StrategyTropheeNull;

public class RegleSavingDiamondsWithLove implements Visitor,Serializable{
	/*
	 * l'attribut totalPoint n'a pas besoin de setter car sa valeur
	 * n'est modifié que par les méthode de la règle.
	 */
private int totalPoint;
	
	private HashSet<Valeur> setValeursRougesVisitees; //Attribut maniuplé uniquement par la class
	private LinkedList<Carte> jest;
	
	
	
	public RegleSavingDiamondsWithLove(LinkedList<Carte> jestJoueur) {
		this.totalPoint=0;
		this.setValeursRougesVisitees=new HashSet<Valeur>();
		this.jest=jestJoueur;//Copie le pointeur vers jestJoueur. Attention toute modification sur this.jest affectera jestJoueur
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
		if(carte.getCouleur()==Couleur.COEUR||carte.getCouleur()==Couleur.CARREAU) {
			if(this.setValeursRougesVisitees.contains(carte.getValeur())) {
				if(carte.getValeur()==Valeur.AS) {
					RegleAsVisitor tempRegleAs = new RegleAsVisitor(this.jest);
					
					if(carte.getCouleur()==Couleur.COEUR) {
						carte.acceptVisitor(tempRegleAs);
					}else {
						Carte tempCarte =new Carte(Valeur.AS,Couleur.COEUR,new StrategyTropheeNull());
						tempCarte.acceptVisitor(tempRegleAs);
					}
					
					
					this.totalPoint+=2*(tempRegleAs.getTotalPoint()+1);
					
				}else {
					this.totalPoint+=carte.getValeur().ordinal();
				}
				this.setValeursRougesVisitees.remove(carte.getValeur());
			}else {
				this.setValeursRougesVisitees.add(carte.getValeur());
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
		Carte coeurAs = new Carte(Valeur.AS,Couleur.COEUR,strat);
		Carte coeur2 = new Carte(Valeur.DEUX,Couleur.COEUR,strat);
		Carte coeur3 = new Carte(Valeur.TROIS,Couleur.COEUR,strat);
		Carte coeur4 = new Carte(Valeur.QUATRE,Couleur.COEUR,strat);
		
		Carte carreauAs = new Carte(Valeur.AS,Couleur.CARREAU,strat);
		Carte carreau2 = new Carte(Valeur.DEUX,Couleur.CARREAU,strat);
		Carte carreau3 = new Carte(Valeur.TROIS,Couleur.CARREAU,strat);
		Carte carreau4 = new Carte(Valeur.QUATRE,Couleur.CARREAU,strat);
		
		
		Carte trefle3 = new Carte(Valeur.TROIS,Couleur.TREFLE,strat);
		Carte pique3 = new Carte(Valeur.TROIS,Couleur.PIQUE,strat);
		
		Carte trefle2 = new Carte(Valeur.DEUX,Couleur.TREFLE,strat);
		Carte pique2 = new Carte(Valeur.DEUX,Couleur.PIQUE,strat);

		Carte piqueAs = new Carte(Valeur.AS,Couleur.PIQUE,strat);
		Carte trefle4 = new Carte(Valeur.QUATRE,Couleur.TREFLE,strat);
		
		
		LinkedList<Carte> jest1 = new LinkedList<Carte>();
		
		jest1.add(coeurAs);
		jest1.add(trefle4);
		jest1.add(carreauAs);
		jest1.add(coeur3);
		jest1.add(carreau3);
		
		RegleSavingDiamondsWithLove regle = new RegleSavingDiamondsWithLove(jest1);
		
		Iterator<Carte> it = jest1.iterator();
		while(it.hasNext()) {
			it.next().acceptVisitor(regle);
		}
		System.out.println(regle.getTotalPoint());
		

		
		
	}
}