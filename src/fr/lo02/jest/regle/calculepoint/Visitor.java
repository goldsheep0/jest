package fr.lo02.jest.regle.calculepoint;

import fr.lo02.jest.*;

/**
 * Interface visitor pour employer le patron visiteur pour le calcul des points.<br>
 * Une règle peut être visité par chaque carte d'un jest afin d'obtenir le total des points associés à cette règle pour un joueur.
 */
public interface Visitor {
	
	/**
	 * Permet d'exécuter une méthode pour une carte. Utilisé pour calculer les points associé à une carte.
	 * @param carte
	 */
	public void visitCarte(Carte carte);
	
	public int getTotalPoint();
}
