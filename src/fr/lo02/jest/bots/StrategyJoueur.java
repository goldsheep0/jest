package fr.lo02.jest.bots;

import fr.lo02.jest.Joueur;

/**
 * Patron Strategy utilisé pour l'implémentation des bots et des joueurs physiques.
 */
public interface StrategyJoueur{
	
	/**
	 * Donne les informations du joueur/bot à la stratégie.
	 * @param j le joueur.
	 */
	public void setJoueur(Joueur j);
	
	/**
	 * Choisit la carte à retourner de l'offre.
	 * @return l'index de la carte à retourner.
	 */
	public int executeRealiserOffre();
	
	/**
	 * Choisit le joueur qui a l'offre qui nous intéresse.
	 * @return l'index du joueur dans la liste de partie.
	 */
	public int executeChoisirJoueur();
	
	/**
	 * Choisit la carte de l'offre du joueur qui nous intéresse.
	 * @return 1 si la carte choisie est la carte cachée, 2 si c'est la carte visible
	 */
	public int executeChoisirCarte();

}
