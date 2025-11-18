package fr.lo02.jest;

public interface StrategyTrophee {
	
	/**
	 * Permet de calculer quel joueur remporte le trophée.
	 * 
	 * @param joueurs
	 * @return
	 */
	public Joueur execute(Joueur[] joueurs);


}
