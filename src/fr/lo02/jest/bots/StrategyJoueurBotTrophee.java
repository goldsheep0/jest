package fr.lo02.jest.bots;

import java.util.Iterator;
import java.util.LinkedList;

import fr.lo02.jest.Carte;
import fr.lo02.jest.Joueur;
import fr.lo02.jest.Partie;
import fr.lo02.jest.Round;
import fr.lo02.jest.enums.Couleur;
import fr.lo02.jest.regle.attributionTrophees.*;

public class StrategyJoueurBotTrophee implements StrategyJoueur {
	
	private Joueur joueur;
	private Partie partie;
	private StrategyTrophee targetTrophee;
	
	public StrategyJoueurBotTrophee() {
		this.partie = Partie.getPartie();
		this.targetTrophee = null;
	}
	
	public void setJoueur(Joueur j) {
		this.joueur = j;
	}

	/**
	 * Le Bot Trophee montre la carte ayant de la valeur pour le trophée choisi
	 * @return L'index de la carte montrée
	 */
	public int executeRealiserOffre() {
		
		if (targetTrophee == null) {
			targetTrophee = partie.getTrophees().getCartes().get(0).getStrategyTrophee();
		}
		
		if (targetTrophee instanceof StrategyTropheeHighest || targetTrophee instanceof StrategyTropheeBestJest || targetTrophee instanceof StrategyTropheeBestJestNoJoke) {
			return 1 - getHighestCard();
			
		} else if (targetTrophee instanceof StrategyTropheeLowest) {
			return getHighestCard();
			
		} else if (targetTrophee instanceof StrategyTropheeJoker) {
			if (joueur.getOffre().hasCarte(Couleur.JOKER) != null) {
				return 1 - joueur.getOffre().getCartes().indexOf(joueur.getOffre().hasCarte(Couleur.JOKER));
			} else {
				return 1 - getHighestCard();
			}
			
		} else { //targetTrophee instanceof StrategyTropheeMajority
			Carte carteTrophee = partie.getTrophees().hasCarte(StrategyTropheeMajority.class);
			if (joueur.getOffre().hasCarte(carteTrophee.getValeur()) != null) {
				return 1 - joueur.getOffre().getCartes().indexOf(joueur.getOffre().hasCarte(Couleur.JOKER));
			} else {
				return getHighestCard();
			}
		}
		
		
	}
	
	private int getHighestCard() {
		if (joueur.getOffre().getCartes().get(0).compareTo(joueur.getOffre().getCartes().get(1)).equals(joueur.getOffre().getCartes().get(1))) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Le Bot Trophee choisit un joueur avec une carte lui permettant de gagner un trophee, la meilleure carte si aucun
	 * @return L'index du joueur avec la carte voulue
	 */
	public int executeChoisirJoueur() {
		
		// Boucle à travers les joueurs restants
		LinkedList<Joueur> autresJoueurs = Round.getAutresJoueurs(joueur);
		Joueur targetJoueur = null;
		Carte carteTarget = null;
		for (Iterator<Joueur> it = autresJoueurs.iterator(); it.hasNext(); ) {
			// On vérifie que l'offre a bien 2 éléments
			Joueur autre = it.next();
			if (autre.getOffre().getCartes().size() == 2) {
				
				Carte carteVisibleAutre = autre.getOffre().getCarteVisible();
				
				if (targetJoueur == null) {
					targetJoueur = autre;
					continue;
				}
				
				carteTarget = targetJoueur.getOffre().getCarteVisible();
				
				if (targetTrophee instanceof StrategyTropheeHighest || targetTrophee instanceof StrategyTropheeBestJest || targetTrophee instanceof StrategyTropheeBestJestNoJoke) {
					if (carteTarget.compareTo(carteVisibleAutre).equals(carteVisibleAutre)) {
						targetJoueur = autre;
					}
				}
				
				else if (targetTrophee instanceof StrategyTropheeLowest) {
					if (carteTarget.compareTo(carteVisibleAutre).equals(carteTarget)) {
						targetJoueur = autre;
					}
				}
				
				else if (targetTrophee instanceof StrategyTropheeJoker) {
					if (carteVisibleAutre.getCouleur() == Couleur.JOKER) {
						targetJoueur = autre;
					}
				}
				
				else { // StrategyTropheeMajority
					if (carteVisibleAutre.getValeur() == partie.getTrophees().hasCarte(StrategyTropheeMajority.class).getValeur()) {
						targetJoueur = autre;
					}
				}
				
				
			}
		}
		return partie.getJoueurs().indexOf(targetJoueur);
	}

	/**
	 * Le Bot Trophee choisit toujours la carte visible
	 * @return 2 car la carte choisie est celle face visible
	 */
	public int executeChoisirCarte() {
		return 2; 
	}

}
