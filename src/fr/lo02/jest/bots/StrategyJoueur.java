package fr.lo02.jest.bots;

import fr.lo02.jest.Joueur;

public interface StrategyJoueur{
	
	public void setJoueur(Joueur j);
	
	public int executeRealiserOffre();
	
	public int executeChoisirJoueur();
	
	public int executeChoisirCarte();

}
