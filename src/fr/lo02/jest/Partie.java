package fr.lo02.jest;

import java.util.*;

public class Partie {
	
	private Partie partie = null;
	private ArrayList<Joueur> joueurs;
	private ConteneurCarte deck;
	
	private Partie() {}
	public Partie getPartie() {
		if (partie != null) {
			return partie;
		} else {
			partie = new Partie();
			return partie;
		}
	}
	
	

}
