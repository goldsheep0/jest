package fr.lo02.jest.regle.attributionTrophees;

import java.io.Serializable;
import java.util.ArrayList;

import fr.lo02.jest.Joueur;

/**
 * Trophee sans effet, classe vide utilisée pour faire des tests unitaires.
 */
public class StrategyTropheeNull implements StrategyTrophee,Serializable{

	private static final long serialVersionUID = 1L;

	public StrategyTropheeNull() {
		
	}
	public String toString() {
		return null;
		
	}

	public Joueur execute(ArrayList<Joueur> joueurs) {
		return null;
	}
	

}
