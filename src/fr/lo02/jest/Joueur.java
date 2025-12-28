package fr.lo02.jest;

import java.io.Serializable;

import fr.lo02.jest.bots.StrategyJoueur;

public class Joueur implements Serializable{
	
	private ConteneurCarte jest;
	private ConteneurCarte offre;
	private String nom;
	private StrategyJoueur strategy;
	
	public Joueur(String nom, StrategyJoueur strategy) {
		this.nom = nom;
		this.offre = new ConteneurCarte();
		this.jest = new ConteneurCarte();
		this.strategy = strategy;
		strategy.setJoueur(this);
	}
	
	public ConteneurCarte getJest() {
		return jest;
	}
	
	public ConteneurCarte getOffre() {
		return offre;
	}
	
	public String getNom() {
		return nom;
	}
	
	public int realiserOffre() {
		return strategy.executeRealiserOffre();
	}
	
	public int choisirJoueur() {
		return strategy.executeChoisirJoueur();
	}
	
	public int choisirCarte()
	{
		return strategy.executeChoisirCarte();
	}
}
