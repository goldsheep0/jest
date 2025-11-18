package fr.lo02.jest.regle.calculepoint;

import java.util.*;

import fr.lo02.jest.Carte;
import fr.lo02.jest.enums.*;

public class RegleJockerCoeurVisitor implements Visitor{
	/*
	 * l'attribut totalPoint n'a pas besoin de setter car sa valeur
	 * n'est modifié que par les méthode de la règle.
	 */
	private int totalPoint;
	
	private LinkedList<Carte> jest;

	public RegleJockerCoeurVisitor(LinkedList<Carte> jestJoueur) {
		this.totalPoint=0;
		this.jest=jestJoueur;
	}
	
	public int getTotalPoint() {
		return totalPoint;
	}
	
	public void visitCarte(Carte carte) {
		if(carte.getCouleur()==Couleur.JOKER) {
			int nombreCoeur=0;
			int valeurCoeur=0; //Stock les valeurs combinés des cartes coeur trouvées
			
			Valeur lastHeartPicked=Valeur.AS;//Initialisé à As pour éviter les problèmes de compilateur
			Carte currentCarte;
			Iterator<Carte> it = this.jest.iterator();
			while(it.hasNext()) {
				currentCarte=it.next();
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
			case 2:
				this.totalPoint-=valeurCoeur;
			case 3:
				this.totalPoint-=valeurCoeur;
			case 4:
				this.totalPoint+=valeurCoeur;
			
				
			}
			
		}
		
	}
}