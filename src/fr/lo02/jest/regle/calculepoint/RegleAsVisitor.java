package fr.lo02.jest.regle.calculepoint;

import fr.lo02.jest.Carte;
import java.util.*;
import fr.lo02.jest.enums.*;

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
}