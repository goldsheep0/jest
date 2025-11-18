package fr.lo02.jest.regle.calculepoint;

import fr.lo02.jest.Carte;
import java.util.*;
import fr.lo02.jest.enums.*;

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
}