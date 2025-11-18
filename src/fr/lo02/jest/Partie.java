package fr.lo02.jest;

import java.util.*;

public class Partie {
	
	private static Partie partie = null;
	private ArrayList<Joueur> joueurs;
	private ConteneurCarte deck;
	private ConteneurCarte stack;
	private Terminal terminal;
	
	private Partie() {
		terminal = new Terminal();
	}
	/**
	 * Méthode à utiliser pour récupérer le singleton Partie
	 * @return le singleton Partie
	 */
	public static Partie getPartie() {
		if (partie != null) {
			return partie;
		} else {
			partie = new Partie();
			return partie;
		}
	}
	
	public Terminal getTerminal() {
		return terminal;
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
	
	public static void main(String[] args) {
		partie = getPartie();
		
		partie.creerJoueurs();
		partie.creerCartes();
		
		int round_counter = 0;
		while (!partie.deck.isEmpty()) {
			round_counter ++;
			Round round = new Round(round_counter == 1);
			
			round.distribuerCartes();
			round.faireOffres();
			round.prendreCartes();
		}
		
		//TODO finirPartie()
	}

}
