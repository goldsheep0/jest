package fr.lo02.jest.bots;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import fr.lo02.jest.Carte;
import fr.lo02.jest.Joueur;
import fr.lo02.jest.Partie;
import fr.lo02.jest.Round;
import fr.lo02.jest.Terminal;

public class StrategyJoueurPhysique implements StrategyJoueur,Serializable {
	
	private Joueur joueur;
	private Partie partie;
	private Terminal terminal;
	
	public StrategyJoueurPhysique() {
		this.partie = Partie.getPartie();
		this.terminal = partie.getTerminal();
	}
	
	public void setJoueur(Joueur j) {
		this.joueur = j;
	}

	public int executeRealiserOffre() {
		Terminal terminal = Partie.getPartie().getTerminal();
		terminal.afficherDivision();
		terminal.afficherChaine(joueur.getNom()+", vous avez dans votre main : "+joueur.getOffre().toString());
		terminal.afficherChaine("Quelle carte voulez-vous placer face visible ? (1 ou 2)");
		int saisie = terminal.lireEntier();
		while (saisie != 1 && saisie != 2) {
			terminal.afficherChaine("Mauvaise saisie, tapez (1 ou 2)");
			saisie = terminal.lireEntier();
		}
		return saisie - 1;
	}

	public int executeChoisirJoueur() {
		// Boucle à travers les joueurs restants
		LinkedList<Joueur> autresJoueurs = Round.getAutresJoueurs(joueur);
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for (Iterator<Joueur> it = autresJoueurs.iterator(); it.hasNext(); ) {
			// On vérifie que l'offre a bien 2 éléments
			Joueur autre = it.next();
			if (autre.getOffre().getCartes().size() == 2) {
				indexes.add(partie.getJoueurs().indexOf(autre));
			}
		}
		
		//Le joueur choisit un des joueurs
		terminal.afficherChaine("Choisissez un joueur : (1 - "+String.valueOf(indexes.size())+")");
		int saisie = terminal.lireEntier();
		while (saisie < 1 || saisie > indexes.size()) {
			terminal.afficherChaine("Mauvaise saisie, tapez un entier entre (1 - "+String.valueOf(indexes.size())+")");
			saisie = terminal.lireEntier();
		}
		return indexes.get(saisie - 1);
	}

	/**
	 * Le joueur choisit une des 2 cartes de l'offre
	 * @return 1 si la carte choisie est la carte cachée, 2 si c'est la carte visible
	 */
	public int executeChoisirCarte() {
		terminal.afficherChaine("Choisissez entre la carte cachée (1) et la carte montrée (2) : ");
		int saisie = terminal.lireEntier();
		while (saisie != 1 && saisie != 2) {
			terminal.afficherChaine("Mauvaise saisie, tapez (1 ou 2)");
			saisie = terminal.lireEntier();
		}
		return saisie;
	}

}
