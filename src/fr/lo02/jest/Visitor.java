package fr.lo02.jest;

public interface Visitor {
	
	/**
	 * Permet d'exécuter une méthode pour une carte. Utilisé pour calculer les points associé à une carte.
	 * @param carte
	 */
	public void visitCarte(Carte carte);
}
