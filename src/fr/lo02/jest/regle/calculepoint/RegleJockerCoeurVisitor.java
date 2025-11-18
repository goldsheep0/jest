package fr.lo02.jest.regle.calculepoint;

import fr.lo02.jest.Carte;
import fr.lo02.jest.enums.*;

public class RegleJockerCoeurVisitor implements Visitor{
	/*
	 * l'attribut totalPoint n'a pas besoin de setter car sa valeur
	 * n'est modifié que par les méthode de la règle.
	 */
	private int totalPoint;
	
	public int getTotalPoint() {
		return totalPoint;
	}
	
	public void visitCarte(Carte carte) {
		
		
	}
}