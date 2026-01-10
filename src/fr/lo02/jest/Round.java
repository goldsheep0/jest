package fr.lo02.jest;

import java.io.Serializable;
import java.util.*;

import fr.lo02.jest.bots.StrategyJoueurPhysique;
import fr.lo02.jest.enums.PartieState;

/**
 * La classe Round contient les méthodes effectuées systématiquement à chaque Round.<br>
 * Elle supporte la sérialisation.
 */
public class Round implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Référence vers le singleton Partie
	 */
	private Partie partie;
	
	/**
	 * Si le round est le premier de la partie, initialisé à true, sinon false
	 */
	private boolean firstRound;
	
	/**
	 * Référence vers le terminal de la partie.
	 */
	private static Terminal terminal;
	
	/**
	 * Iterateur à travers les joueurs de la partie. Permet de parcourir la liste des joueurs indépendemment de
	 */
	private transient Iterator<Joueur> itJ;
	private Carte carteChoisieBot;
	
	public Carte getCarteChoisieBot() {return carteChoisieBot;}
	
	public Round(boolean firstRound) {
		this.partie = Partie.getPartie();
		this.firstRound = firstRound;
		this.terminal = partie.getTerminal();
		this.itJ = partie.getJoueurs().iterator();
		terminal.afficherDivision();
		terminal.afficherChaine("Nouveau round !");
	}
	
	/**
	 * Distribue 2 cartes à l'offre de chaque joueurs.<br>
	 * Si le round n'est pas le premier. Récupère aussi la carte qui n'a pas été prise de l'offre des joueurs au round d'avant.
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
				Carte cJ = joueur.getOffre().distribuerCarte();
				cJ.setFaceVisible(false);
				partie.getStack().addCarte(cJ);
				
				Carte cD = partie.getDeck().distribuerCarte();
				partie.getStack().addCarte(cD);
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
	 * Chaque joueur retourne une des cartes de son offre.
	 */
	public void faireOffres() {
		if (itJ.hasNext()) {
			Joueur joueur = itJ.next();
			if(joueur.getStrategyJoueur() instanceof StrategyJoueurPhysique) {
				partie.setJoueurFocus(joueur);
				partie.changeState(PartieState.FAIRE_OFFRE);
			} else {
				int carteIndex = joueur.realiserOffre();
				for (Iterator<Carte> itCartes = joueur.getOffre().getCartes().iterator(); itCartes.hasNext(); ) {
					itCartes.next().setFaceVisible(true);
				}
				joueur.getOffre().getCartes().get(carteIndex).setFaceVisible(false);
				faireOffres();
			}
		} else {
			calculerOrdrePassage();
			itJ = partie.getJoueurs().iterator();
			prendreCarteSuivante();
		}		
	}
	
	/**
	 * Calcule l'ordre de passage des joueurs, et sort une liste des joueurs ordonnée
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
	 * Retourne la liste de joueurs parmi lesquels le joueur, dont c'est le tour, peut choisir une carte.
	 * @param joueurSelectionne Le joueur sélectionné
	 * @return La liste de joueurs sans le joueur
	 */
	public static LinkedList<Joueur> getAutresJoueurs(Joueur joueurSelectionne) {
		LinkedList<Joueur> list = new LinkedList<Joueur>();
		for (Iterator<Joueur> it = Partie.getPartie().getJoueurs().iterator(); it.hasNext(); ) {
			Joueur j = it.next();
			if (!j.equals(joueurSelectionne) && j.getOffre().getCartes().size() == 2) {
				list.add(j);
			}
		}
		if (list.size() == 0 && joueurSelectionne.getOffre().getCartes().size() == 2) {
			list.add(joueurSelectionne);
		}
		return list;
	}
	
	/**
	 * Permet de transférer la carte choisie par le joueur de l'offre du joueur où elle était pour la mettre dans le jest du joueur l'ayant prise.
	 */
	public void prendreCarte(Carte c) {
		for (Iterator<Joueur> it = partie.getJoueurs().iterator(); it.hasNext(); ) {
			Joueur joueur = it.next();
			if (joueur.getOffre().getCartes().contains(c)) {
				joueur.getOffre().distribuerCarte(c);
				partie.getJoueurFocus().getJest().addCarte(c);
				break;
			}
		}
	}
	
	/**
	 * A chaque appel, permet d'avancer dans la liste des joueurs et de les faire prendre un par un une carte parmis les offres qui leur sont disponibles.
	 */
	public void prendreCarteSuivante() {
		if (itJ.hasNext()) {
			Joueur j = itJ.next();
			partie.setJoueurFocus(j);
			if (j.getStrategyJoueur() instanceof StrategyJoueurPhysique) {
				partie.changeState(PartieState.CHOISIR_OFFRE);
			} else {
				
				Joueur joueurChoisiBot = partie.getJoueurs().get(j.choisirJoueur());
				int carteChoisieVisible = j.choisirCarte();
				
				if (carteChoisieVisible == 1) {
					carteChoisieBot = joueurChoisiBot.getOffre().retirerCarteFaceVisible(false);
				} else {
					carteChoisieBot = joueurChoisiBot.getOffre().retirerCarteFaceVisible(true);
				}
				joueurChoisiBot.getOffre().addCarte(carteChoisieBot);
				
				partie.changeState(PartieState.CHOISIR_OFFRE_BOT);
			}
		} else {
			partie.nouveauRound();
		}
	}
}
