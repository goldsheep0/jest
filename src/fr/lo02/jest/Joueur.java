package fr.lo02.jest;

import java.io.Serializable;

import fr.lo02.jest.bots.StrategyJoueur;

/**
 * Représente un joueur qui peut être un joueur physique ou un bot en fonction de la stratégie sélectionnée.<br>
 * Implémente le patron stratégie pour le comportement du joueur/bot.<br>
 * Supporte la sérialisation.
 */
public class Joueur implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Jest du joueur.
	 */
	private ConteneurCarte jest;
	
	/**
	 * Offre du joueur, change à chaque Round.
	 */
	private ConteneurCarte offre;
	
	/**
	 * Nom du joueur.
	 */
	private String nom;
	
	/**
	 * Stratégie utilisée pour le comportement du joueur.
	 */
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
	
	public StrategyJoueur getStrategyJoueur() {
		return strategy;
	}
	
	
	/**
	 * Renvoie le choix fait par le joueur/bot concernant la carte à retourner dans son offre.
	 * @return 0 ou 1 en fonction de l'index de la carte choisie.
	 */
	public int realiserOffre() {
		return strategy.executeRealiserOffre();
	}
	
	/**
	 * Renvoie l'index, dans la liste des joueurs de Partie, du joueur, choisie par le joueur/bot, à qui prendre une carte dans son offre
	 * @return
	 */
	public int choisirJoueur() {
		return strategy.executeChoisirJoueur();
	}
	
	/**
	 * Renvoie le choix de carte, dans l'offre du joueur choisie par le joueur/bot.
	 * @return 0 si la carte face cachée est choisie, 1 sinon.
	 */
	public int choisirCarte()
	{
		return strategy.executeChoisirCarte();
	}
}
