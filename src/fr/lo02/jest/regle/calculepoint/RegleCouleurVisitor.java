package fr.lo02.jest.regle.calculepoint;

import fr.lo02.jest.Carte;
import fr.lo02.jest.enums.*;

public class RegleCouleurVisitor implements Visitor{
	/*
	 * l'attribut totalPoint n'a pas besoin de setter car sa valeur
	 * n'est modifié que par les méthode de la règle.
	 */
	private int totalPoint;
	
	public RegleCouleurVisitor() {
		this.totalPoint=0;
	}
	
	public int getTotalPoint() {
		return totalPoint;
	}
	
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
	
}
