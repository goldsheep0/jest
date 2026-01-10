package fr.lo02.jest.regle.calculepoint;

import java.util.*;

import fr.lo02.jest.Carte;
import fr.lo02.jest.enums.*;
import fr.lo02.jest.regle.attributionTrophees.StrategyTrophee;
import fr.lo02.jest.regle.attributionTrophees.StrategyTropheeNull;

/**
 * Règle de calcul des point pour les cartes de coeur.
 */
public class RegleJockerCoeurVisitor implements Visitor{
	
	/**
	 * Contient le total des points après que chaque carte du jest d'un joueur a visité cette carte.
	 */
	private int totalPoint;
	
	/**
	 * Contient une référence vers les cartes du jest du joueur dont on est en train de calculer les points.
	 */
	private LinkedList<Carte> jest;

	public RegleJockerCoeurVisitor(LinkedList<Carte> jestJoueur) {
		this.totalPoint=0;
		this.jest=jestJoueur;//Copie le pointeur vers jestJoueur. Attention toute modification sur this.jest affectera jestJoueur
	}
	
	public int getTotalPoint() {
		return totalPoint;
	}
	
	
	/**
	 * La valeur des coeur et des joker sont interdépendantes :<br>
	 * Des coeurs sans joker ne font rien.<br>
	 * Un joker seul ajoute 4 points au jest.<br>
	 * Un joker et 1,2 ou 3 coeurs soustraient la valeur des coeurs au jest.<br>
	 * Un joker et 4 coeurs ajoutent la valeur des coeurs au jest.
	 * 
	 * @param carte
	 */
	public void visitCarte(Carte carte) {
		if(carte.getCouleur()==Couleur.JOKER) {
			int nombreCoeur=0;
			int valeurCoeur=0; //Stock les valeurs combinés des cartes coeur trouvées
			
			Valeur lastHeartPicked=Valeur.AS;//Initialisé à As pour éviter les problèmes de compilateur
			Carte currentCarte;
			Iterator<Carte> it1 = this.jest.iterator();
			while(it1.hasNext()) {
				currentCarte=it1.next();
				if(currentCarte.getCouleur()==Couleur.COEUR) {
					nombreCoeur++;
					valeurCoeur+=currentCarte.getValeur().ordinal();
					lastHeartPicked=currentCarte.getValeur();
				}
			}
			switch(nombreCoeur) {
				case 0:
					this.totalPoint+=4;
					break;
				case 1:
					if(lastHeartPicked==Valeur.AS) {
						valeurCoeur+=4;
					}
					this.totalPoint-=valeurCoeur;
					break;
				case 2:
					this.totalPoint-=valeurCoeur;
					break;
				case 3:
					this.totalPoint-=valeurCoeur;
					break;
				case 4:
					this.totalPoint+=valeurCoeur;
					break;
				default:
					break;
			}
			
		}
		
	}
	
	/**
	 * Permet de test la classe avec différents cas qui sont :<br>
	 * Aucun joker : expected 0<br>
	 * Un joker, aucun coeur : expected 4<br>
	 * Un joker, un as : expected -5<br>
	 * Un joker, 1,2 ou 3 coeur : expected -valeursAjoutesDesCartes<br>
	 * Un joker, les 4 coeurs : expected 10
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
		jest1.add(coeur3);
		jest1.add(coeur2);
		jest1.add(coeurAs);
		
		RegleJockerCoeurVisitor regle = new RegleJockerCoeurVisitor(jest1);
		
		Iterator<Carte> it = jest1.iterator();
		while(it.hasNext()) {
			it.next().acceptVisitor(regle);
		}
		System.out.println(regle.getTotalPoint());
		

		
		
	}
}