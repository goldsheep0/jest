package fr.lo02.jest.bots;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

import fr.lo02.jest.Carte;
import fr.lo02.jest.Joueur;
import fr.lo02.jest.Partie;
import fr.lo02.jest.Round;

public class StrategyJoueurBotBourrin implements StrategyJoueur,Serializable{
	
	private Joueur joueur;
	private Partie partie;
	
	public StrategyJoueurBotBourrin() {
		this.partie = Partie.getPartie();
	}
	
	public void setJoueur(Joueur j) {
		this.joueur = j;
	}

	/**
	 * Le Bot Bourrin choisit toujours de retourner la carte avec la valeur la moins grande
	 * @return L'index de la carte avec la valeur la moins grande
	 */
	public int executeRealiserOffre() {
		if (joueur.getOffre().getCartes().get(0).compareTo(joueur.getOffre().getCartes().get(1)).equals(joueur.getOffre().getCartes().get(1))) {
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * Le Bot Bourrin choisit toujours le joueur avec la meilleure carte
	 * @return L'index du joueur avec la carte avec la valeur la plus grande
	 */
	public int executeChoisirJoueur() {
		// Boucle à travers les joueurs restants
		LinkedList<Joueur> autresJoueurs = Round.getAutresJoueurs(joueur);
		Joueur bestJoueur = null;
		for (Iterator<Joueur> it = autresJoueurs.iterator(); it.hasNext(); ) {
			// On vérifie que l'offre a bien 2 éléments
			Joueur autre = it.next();
			if (autre.getOffre().getCartes().size() == 2) {
				Carte carteVisibleAutre = autre.getOffre().getCarteVisible();
				if (bestJoueur == null || bestJoueur.getOffre().getCarteVisible().compareTo(carteVisibleAutre).equals(carteVisibleAutre)) {
					bestJoueur = autre;
				}
			}
		}
		return partie.getJoueurs().indexOf(bestJoueur);
	}

	/**
	 * Le Bot bourrin choisit toujours la carte visible
	 * @return 2 car la carte choisie est celle face visible
	 */
	public int executeChoisirCarte() {
		return 2; 
	}

}
