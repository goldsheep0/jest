package fr.lo02.jest;

import java.util.*;

public class Partie {
	
	private static Partie partie = null;
	private ArrayList<Joueur> joueurs;
	private ConteneurCarte deck;
	private ConteneurCarte stack;
	private Round round;
	
	private Partie() {}
	public static Partie getPartie() {
		if (partie != null) {
			return partie;
		} else {
			partie = new Partie();
			return partie;
		}
	}
	
	/**
	 * Crée les joueurs dans l'array joueurs
	 */
	public void creerJoueurs() {
		
	}
	
	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}
	
	/**
	 * Crée les cartes et les mets dans le deck
	 */
	public void creerCartes() {
		
	}
	
	public ConteneurCarte getDeck() {
		return deck;
	}
	
	public ConteneurCarte getStack() {
		return stack;
	}
	
	/**
	 * Crée le prochain round
	 */
	public void creerRound() {
		
	}
	
	public static void main(String[] args) {
		partie = getPartie();
		
		partie.creerJoueurs();
		partie.creerCartes();
		
		while (!partie.deck.isEmpty()) {
			partie.creerRound();
			
		}
	}

}
