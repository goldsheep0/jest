package fr.lo02.texte;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import fr.lo02.jest.Carte;
import fr.lo02.jest.Joueur;
import fr.lo02.jest.Partie;
import fr.lo02.jest.Round;
import fr.lo02.jest.Terminal;
import fr.lo02.jest.enums.PartieState;

public class VueTexte extends Thread implements Observer, Serializable {
	
	private Partie partie;
	private Terminal terminal;
	private boolean next;
	
	public VueTexte() {
		partie = Partie.getPartie();
		terminal = Partie.getPartie().getTerminal();
		next = false;
	}
	
	public void start_menu() {
		next = false;
		terminal.afficherChaine("Bienvenue au jeu de Jest !");
		terminal.afficherChaine("Entrez N pour nouvelle partie et C pour charger une partie.");
		String s = terminal.lireChaine();
		while (!(s.equals("C") || s.equals("N")) && !next) {
			s = terminal.lireChaine();
		}
		if (s.equals("C")) {
			partie.changeState(PartieState.LOAD);
		} else if (s.equals("N")) {
			partie.changeState(PartieState.C_VARIANTE);
		}
	}
	
	public void load_menu() {
		
	}
	
	public void caracteristics_menu() {
		next = false;
		terminal.afficherDivision();
		terminal.afficherChaine("Entrez une variante (0, 1, 2) : ");
		int i = terminal.lireEntier();
		while (!(i >= 0 && i <= 2) && !next) {
			i = terminal.lireEntier();
		}
		if (i >= 0 && i <= 2) {
			partie.setVariante(i);
		}
		
		terminal.afficherChaine("Entrez le nombre de joueurs (3, 4) : ");
		i = terminal.lireEntier();
		while (!(i == 3 || i == 4) && !next) {
			i = terminal.lireEntier();
		}
		if (i == 3 || i == 4) {
			partie.setNombreJoueursTotal(i);
			partie.changeState(PartieState.C_BOTS);
		}
	}
	
	public void bots_menu() {
		next = false;
		terminal.afficherDivision();
		terminal.afficherChaine("Entrez le nombre de bots \"bourrins\" : ");
		int i = terminal.lireEntier();
		while (!(i >= 0 || i <= partie.getNombreJoueursTotal()) && !next) {
			i = terminal.lireEntier();
		}
		if (i >= 0 || i <= partie.getNombreJoueursTotal()) {
			partie.addBots(i, 0);
		}
		
		terminal.afficherChaine("Entrez le nombre de bots \"trophees\" : ");
		int bourrins = partie.getJoueurs().size();
		i = terminal.lireEntier();
		while (!(i >= 0 || i <= partie.getNombreJoueursTotal() - bourrins) && !next) {
			i = terminal.lireEntier();
		}
		if (i >= 0 || i <= partie.getNombreJoueursTotal() - bourrins) {
			partie.addBots(i, 1);
			if (partie.getNombreJoueursTotal() - partie.getJoueurs().size() == 0) {
				partie.nouveauRound();
			} else {
				partie.changeState(PartieState.C_PLAYERS);
			}
		}
	}
	
	public void players_menu() {
		next = false;
		terminal.afficherDivision();
		int i = 0;
		while ((i < partie.getNombreJoueursTotal() - partie.getJoueurs().size()) && !next) {
			i++;
			terminal.afficherChaine("Entrez le nom du joueur "+i+" : ");
			String nom = terminal.lireChaine();
			partie.addJoueur(nom);
		}
		if (i == partie.getNombreJoueursTotal() - partie.getJoueurs().size()) {
			partie.nouveauRound();
		}
	}
	
	public void new_round_menu() {
		next = false;
		terminal.afficherDivision();
		terminal.afficherChaine("Nouveau round !");
		terminal.afficherChaine("Entrez S pour sauvegarder ou C pour continuer");
		String s = terminal.lireChaine();
		while (!(s.equals("S") || s.equals("C")) && !next) {
			s = terminal.lireChaine();
		}
		if (s.equals("C")) {
			partie.getRound().faireOffres();
		} else if (s.equals("S")) {
			partie.changeState(PartieState.SAVE);
		}
	}
	
	public void faire_offre_menu() {
		next = false;
		terminal.afficherDivision();
		terminal.afficherChaine("Au tour de "+partie.getJoueurFocus().getNom()+" !");
		terminal.afficherChaine("Vous avez dans votre main : " + partie.getJoueurFocus().getOffre().toString());
		terminal.afficherChaine("Quelle carte souhaitez-vous retourner ? (1, 2)");
		int i = terminal.lireEntier();
		while (!(i == 1 || i == 2) && !next) {
			i = terminal.lireEntier();
		}
		if (i == 1 || i == 2) {
			partie.getJoueurFocus().getOffre().getCartes().get(i-1).setFaceVisible(false);
			partie.getRound().faireOffres();
		}
	}
	
	public void choisir_offre_menu() {
		next = false;
		Joueur joueur = partie.getJoueurFocus();
		Joueur joueurChoisi = null;
		
		terminal.afficherDivision();
		terminal.afficherChaine("A "+joueur.getNom()+" de jouer !");
		
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
		partie.getRound().prendreCarteSuivante();
	}
	
	public void choisir_offre_bot_menu() {
		next = false;
		LinkedList<Joueur> listeJoueur = Round.getAutresJoueurs(partie.getJoueurFocus());
		Joueur target = null;
		for (Iterator<Joueur> it = partie.getJoueurs().iterator(); it.hasNext(); ) {
			Joueur j = it.next();
			if (j.getOffre().getCartes().contains(partie.getRound().getCarteChoisieBot())) {
				target = j;
				break;
			}
		}
		String carteStr;
		if (partie.getRound().getCarteChoisieBot().estFaceVisible()) {
			carteStr = partie.getRound().getCarteChoisieBot().toString();
		} else {
			carteStr = "[Carte cachée]";
		}
		terminal.afficherChaine("Le bot "+partie.getJoueurFocus()+" a pris "+carteStr+" de "+target.getNom());
		terminal.afficherChaine("Appuyez sur entrer pour continuer...");
		terminal.lireChaine();
		partie.getRound().prendreCarteSuivante();
	}
	
	public void trophees_menu() {
		next = false;
		terminal.afficherDivision();
		terminal.afficherChaine("La partie est finie ! Remise des trophées...");
		terminal.afficherChaine("Trophée(s) à attribuer : \n"+partie.getTrophees().toStringTrophee());
		for (Iterator<Joueur> it = partie.getJoueurs().iterator(); it.hasNext(); ) {
			for (Iterator<Carte> itC = partie.getTrophees().getCartes().iterator(); itC.hasNext(); ) {
				Joueur j = it.next();
				Carte c = itC.next();
				if (j.getJest().getCartes().contains(c)) {
					terminal.afficherChaine("Le joueur "+j.getNom()+" a obtenu la carte : "+c.toString());
				}
			}
		}
		terminal.afficherChaine("Appuyez sur entrer pour continuer...");
		terminal.lireChaine();
		partie.getRound().prendreCarteSuivante();
	}
	
	/**
	 * Affiche le score de chaque joueur dans le terminal.
	 */
	public void scoreboard_menu() {
		terminal.afficherDivision();
		terminal.afficherChaine("Affichage des scores");
		LinkedHashMap<Joueur, Integer> scores = partie.compterScores();
		int i = 0;
		for (Iterator<Joueur> it = scores.keySet().iterator(); it.hasNext(); ) {
			i++;
			Joueur j = it.next();
			terminal.afficherChaine(String.valueOf(i)+" - " + j.getNom() +" avec un score de "+Integer.toString(scores.get(j))+" !");
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
		switch ((PartieState)arg) {
		
		case START:
			next = true;
			new Thread(this::start_menu).start();
			break;
		case LOAD:
			next = true;
			new Thread(this::load_menu).start();
			break;
		case C_VARIANTE:
			next = true;
			new Thread(this::caracteristics_menu).start();
			break;
		case C_BOTS:
			next = true;
			new Thread(this::bots_menu).start();
			break;
		case C_PLAYERS:
			next = true;
			new Thread(this::players_menu).start();
			break;
		case NEW_ROUND:
			next = true;
			new Thread(this::new_round_menu).start();
			break;
		case FAIRE_OFFRE:
			next = true;
			new Thread(this::faire_offre_menu).start();
			break;
		case CHOISIR_OFFRE:
			next = true;
			new Thread(this::choisir_offre_menu).start();
			break;
		case CHOISIR_OFFRE_BOT:
			next = true;
			new Thread(this::choisir_offre_bot_menu).start();
			break;
		case TROPHEES:
			next = true;
			new Thread(this::trophees_menu).start();
			break;
		case SCOREBOARD:
			next = true;
			new Thread(this::scoreboard_menu).start();
			break;
		/*case SAVE:
			next = true;
			save_menu();
			break;
		case NEW_SAVE:
			next = true;
			new_save_menu();
			break;*/
		default:
			break;
		
		}
		
	}

}
