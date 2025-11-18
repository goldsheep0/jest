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
	
	/**
	 * Chaque joueur retourne une de ses cartes pour faire son offre.
	 */
	public void faireOffres() {
		for (Iterator<Joueur> it = partie.getJoueurs().iterator(); it.hasNext(); ) {
			Joueur joueur = it.next();
			Terminal terminal = partie.getTerminal();
			terminal.afficherChaine("Vous avez dans votre main : "+joueur.getOffre().toString());
			terminal.afficherChaine("Quelle carte voulez-vous retourner ? (1 ou 2)");
			int saisie = terminal.lireEntier();
			while (saisie != 1 && saisie != 2) {
				terminal.afficherChaine("Mauvaise saisie, tapez (1 ou 2)");
				saisie = terminal.lireEntier();
			}
			joueur.getOffre().getCartes().get(saisie - 1).setFaceVisible(true);
		}
	}
	
	/**
	 * Calcule l'ordre de passage des joueurs, et sort la liste de joueurs dans le bon ordre
	 */
	public void calculerOrdrePassage() {
		Comparator<Joueur> meilleureCarte = new Comparator<Joueur>() {
			@Override
			public int compare(Joueur j1, Joueur j2) {
				Carte carteJ1 = j1.getOffre().getCarteVisible();
				Carte carteJ2 = j2.getOffre().getCarteVisible();
				if (carteJ1.compareTo(carteJ2).equals(carteJ1)) {
					return -1;
				} else {
					return 1;
				}
			}
		};
		partie.getJoueurs().sort(meilleureCarte);
	}
	
	/**
	 * Chaque joueur prend une carte pour son jest.
	 */
	public void prendreCartes() {
		calculerOrdrePassage();
		for (Iterator<Joueur> it = partie.getJoueurs().iterator(); it.hasNext(); ) {
			Joueur joueur = it.next();
			// TODO Continuer
		}
	}

}
