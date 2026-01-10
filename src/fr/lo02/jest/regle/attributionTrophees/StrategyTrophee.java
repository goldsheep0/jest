package fr.lo02.jest.regle.attributionTrophees;

import java.util.ArrayList;

import fr.lo02.jest.Joueur;

/**
 * Interface implémentée par les stratégies de calcul d'attribution de trophées.
 */
public interface StrategyTrophee {
	
	/**
	 * Permet de calculer quel joueur remporte le trophée.
	 * 
	 * @param joueurs
	 * @return
	 */
	public Joueur execute(ArrayList<Joueur> joueurs);
	
	public String toString();


}
