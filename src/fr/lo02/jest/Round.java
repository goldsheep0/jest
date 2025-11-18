package fr.lo02.jest;

import java.util.*;

public class Round {
	
	private Partie partie;
	private boolean firstRound;
	
	public Round(boolean firstRound) {
		this.partie = Partie.getPartie();
		this.firstRound = firstRound;
	}
	
	/**
	 * Distribue les cartes à chaque joueur
	 */
	public void distribuerCartes() {
		if (firstRound) {
			partie.getDeck().melanger();
			
			for (Iterator<Joueur> it = partie.getJoueurs().iterator(); it.hasNext(); ) {
				Joueur joueur = it.next();
				for (int i=0; i<2; i++) {
					Carte c = partie.getDeck().distribuerCarte();
					joueur.getOffre().addCarte(c);
				}
			}
		} else {
			for (Iterator<Joueur> it = partie.getJoueurs().iterator(); it.hasNext(); ) {
				Joueur joueur = it.next();
				Carte c = joueur.getOffre().distribuerCarte();
				partie.getStack().addCarte(c);
				
				c = partie.getDeck().distribuerCarte();
				partie.getStack().addCarte(c);
			}
			partie.getStack().melanger();
			for (Iterator<Joueur> it = partie.getJoueurs().iterator(); it.hasNext(); ) {
				Joueur joueur = it.next();
				for (int i=0; i<2; i++) {
					Carte c = partie.getStack().distribuerCarte();
					joueur.getOffre().addCarte(c);
				}
			}
		}
	}
	
	public void faireOffres() {
		for (Iterator<Joueur> it = partie.getJoueurs().iterator(); it.hasNext(); ) {
			Joueur joueur = it.next();
			
		}
	}

}
