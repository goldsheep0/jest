package fr.lo02.jest.regle.attributionTrophees;

import java.util.ArrayList;

import fr.lo02.jest.Joueur;

public interface StrategyTrophee {
	
	/**
	 * Permet de calculer quel joueur remporte le trophée.
	 * 
	 * @param joueurs
	 * @return
	 */
	public Joueur execute(ArrayList<Joueur> joueurs);


}
