package fr.lo02.jest;

import java.util.*;

public class Round {
	
	private Partie partie;
	private boolean firstRound;
	private Terminal terminal;
	
	public Round(boolean firstRound) {
		this.partie = Partie.getPartie();
		this.firstRound = firstRound;
		this.terminal = partie.getTerminal();
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
		
		for (int joueurIndex = 0; joueurIndex < partie.getJoueurs().size(); joueurIndex++) {
			
			Joueur joueur = partie.getJoueurs().get(joueurIndex);
			Joueur joueurChoisi = null;
			
			if (joueurIndex < partie.getJoueurs().size() - 1) {
				terminal.afficherChaine("Affichage des offres des joueurs : ");
				ArrayList<Integer> indexes = new ArrayList<Integer>();
				for (int joueurIndex2 = joueurIndex + 1; joueurIndex2 < partie.getJoueurs().size(); joueurIndex2++) {
					if (partie.getJoueurs().get(joueurIndex2).getOffre().getCartes().size() == 2) {
						indexes.add(joueurIndex2);
						Carte carteVisible = partie.getJoueurs().get(joueurIndex2).getOffre().getCarteVisible();
						String nomJoueur = partie.getJoueurs().get(joueurIndex2).getNom();
						terminal.afficherChaine("Offre de "+nomJoueur+" : [Carte cachée], "+carteVisible.toString());
					}
				}
				
				terminal.afficherChaine("Choisissez un joueur : (1 - "+String.valueOf(indexes.size())+")");
				int saisie = terminal.lireEntier();
				while (saisie < 0 || saisie >= indexes.size()) {
					terminal.afficherChaine("Mauvaise saisie, tapez un entier entre (1 - "+String.valueOf(indexes.size())+")");
					saisie = terminal.lireEntier();
				}
				
				
				joueurChoisi = partie.getJoueurs().get(indexes.get(saisie - 1));
				Carte carteVisible = joueurChoisi.getOffre().getCarteVisible();
				String nomJoueur = joueurChoisi.getNom();
				terminal.afficherChaine("Vous avez choisi l'offre de "+nomJoueur+" : [Carte cachée], "+carteVisible.toString());
				
			}
			
			else {
				
				for (int joueurIndex2 = joueurIndex + 1; joueurIndex2 < partie.getJoueurs().size(); joueurIndex2++) {
					if (partie.getJoueurs().get(joueurIndex2).getOffre().getCartes().size() == 2) {
						Carte carteVisible = partie.getJoueurs().get(joueurIndex2).getOffre().getCarteVisible();
						String nomJoueur = partie.getJoueurs().get(joueurIndex2).getNom();
						terminal.afficherChaine("Il ne reste que l'offre de "+nomJoueur+" : [Carte cachée], "+carteVisible.toString());
						joueurChoisi = partie.getJoueurs().get(joueurIndex2);
						break;
					}
				}
			}
			
			//Le joueur a été choisi
			terminal.afficherChaine("Choisissez entre la carte cachée (1) et la carte montrée (2) : ");
			int saisie = terminal.lireEntier();
			while (saisie != 1 && saisie != 2) {
				terminal.afficherChaine("Mauvaise saisie, tapez (1 ou 2)");
				saisie = terminal.lireEntier();
			}
			Carte carteChoisie = null;
			if (saisie == 1) {
				carteChoisie = joueurChoisi.getOffre().retirerCarteFaceVisible(false);
			} else {
				carteChoisie = joueurChoisi.getOffre().retirerCarteFaceVisible(true);
			}
			joueur.getJest().addCarte(carteChoisie);
			terminal.afficherChaine("Vous avez ajouté "+carteChoisie.toString()+" à votre jest.");
		}
	}

}
