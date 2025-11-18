package fr.lo02.jest.regle.attributionTrophees;

import fr.lo02.jest.Joueur;

/**
 * Trophee sans effet, classe vide utilisée pour faire des tests unitaires.
 */
public class StrategyTropheeNull implements StrategyTrophee{

	public StrategyTropheeNull() {
		
	}
	@Override
	public Joueur execute(Joueur[] joueurs) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
