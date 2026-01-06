package fr.lo02.jest;

import java.io.Serializable;
import java.util.*;

import fr.lo02.jest.bots.StrategyJoueurPhysique;
import fr.lo02.jest.enums.PartieState;

public class Round implements Serializable{
	
	private Partie partie;
	private boolean firstRound;
	private Terminal terminal;
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
	 * Chaque joueur retourne une de ses cartes pour faire son offre.
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
	 * Retourne la liste de joueurs sans le joueur sélectionné
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
	 * 
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
	 * 
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
	
	/**
	 * Chaque joueur prend une carte et l'ajoute à son jest
	 */
	/*public void prendreCartes() {
		
		calculerOrdrePassage();
		
		for (int joueurIndex = 0; joueurIndex < partie.getJoueurs().size(); joueurIndex++) {
			
			Joueur joueur = partie.getJoueurs().get(joueurIndex);
			Joueur joueurChoisi = null;
			
			terminal.afficherDivision();
			terminal.afficherChaine("A "+joueur.getNom()+" de jouer !");
			
			
			// Si le joueur n'est pas le dernier, on lui propose de choisir l'offre d'un des joueurs
			if (joueurIndex < partie.getJoueurs().size() - 1) {
				terminal.afficherChaine("Affichage des offres des joueurs : ");
				
				// Boucle à travers les joueurs restants
				LinkedList<Joueur> autresJoueurs = Round.getAutresJoueurs(joueur);
				for (Iterator<Joueur> it = autresJoueurs.iterator(); it.hasNext(); ) {
					// On vérifie que l'offre a bien 2 éléments
					Joueur autre = it.next();
					if (autre.getOffre().getCartes().size() == 2) {
						Carte carteVisibleAutre = autre.getOffre().getCarteVisible();
						String nomAutre = autre.getNom();
						terminal.afficherChaine("Offre de "+nomAutre+" : [Carte cachée], "+carteVisibleAutre.toString());
					}
				}
				
				int joueurChoisiIndex = joueur.choisirJoueur();
				
				joueurChoisi = partie.getJoueurs().get(joueurChoisiIndex);
				Carte carteVisible = joueurChoisi.getOffre().getCarteVisible();
				String nomJoueur = joueurChoisi.getNom();
				terminal.afficherChaine("Vous avez choisi l'offre de "+nomJoueur+" : [Carte cachée], "+carteVisible.toString());
				
			}
			
			//Sinon, le joueur a l'offre du joueur restant avec une offre à 2 élements
			else {
				
				for (int joueurIndex2 = 0; joueurIndex2 < partie.getJoueurs().size(); joueurIndex2++) {
					if (partie.getJoueurs().get(joueurIndex2).getOffre().getCartes().size() == 2) {
						joueurChoisi = partie.getJoueurs().get(joueurIndex2);
						Carte carteVisible = joueurChoisi.getOffre().getCarteVisible();
						String nomJoueur = joueurChoisi.getNom();
						terminal.afficherChaine("Il ne reste que l'offre de "+nomJoueur+" : [Carte cachée], "+carteVisible.toString());
						break;
					}
				}
			}
			
			//Le joueur a été choisi, on choisit maintenant la carte de l'offre
			int carteChoisieVisible = joueur.choisirCarte();
			Carte carteChoisie;
			if (carteChoisieVisible == 1) {
				carteChoisie = joueurChoisi.getOffre().retirerCarteFaceVisible(false);
			} else {
				carteChoisie = joueurChoisi.getOffre().retirerCarteFaceVisible(true);
			}
			joueur.getJest().addCarte(carteChoisie);
			terminal.afficherChaine("Vous avez ajouté "+carteChoisie.toString()+" à votre jest.");
		}
	}*/

}
