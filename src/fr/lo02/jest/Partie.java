package fr.lo02.jest;

import java.util.*;

import fr.lo02.jest.bots.StrategyJoueurBotBourrin;
import fr.lo02.jest.bots.StrategyJoueurPhysique;
import fr.lo02.jest.enums.*;
import fr.lo02.jest.regle.attributionTrophees.*;
import fr.lo02.jest.regle.calculepoint.*;

public class Partie {
	
	private static Partie partie = null;
	private ArrayList<Joueur> joueurs;
	private ConteneurCarte deck;
	private ConteneurCarte stack;
	private Terminal terminal;
	private int variante;
	
	private Partie() {
		terminal = new Terminal();
		joueurs = new ArrayList<Joueur>();
		deck = creerDeck();
		stack = new ConteneurCarte();
	}
	/**
	 * Méthode à utiliser pour récupérer le singleton Partie
	 * @return le singleton Partie
	 */
	public static Partie getPartie() {
		if (partie != null) {
			return partie;
		} else {
			partie = new Partie();
			return partie;
		}
	}
	
	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs=joueurs;
	}
	
	public Terminal getTerminal() {
		return terminal;
	}
	
	/**
	 * Crée les joueurs dans l'array joueurs
	 */
	public void creerJoueurs() {
		terminal.afficherChaine("Combien de joueurs y a-t-il ? (3 ou 4)");
		int nombreJoueur = terminal.lireEntier();
		while (nombreJoueur != 3 && nombreJoueur != 4) {
			terminal.afficherChaine("Mauvaise saisie, tapez (3 ou 4)");
			nombreJoueur = terminal.lireEntier();
		}
		
		terminal.afficherChaine("Combien de bots y a-t-il ?");
		int nombreBot = terminal.lireEntier();
		while (nombreBot < 0 || nombreBot > nombreJoueur) {
			terminal.afficherChaine("Mauvaise saisie, tapez (0 - " + String.valueOf(nombreJoueur) + ")");
			nombreBot = terminal.lireEntier();
		}
		
		for (int i = 0; i < nombreJoueur - nombreBot; i++) {
			terminal.afficherChaine("Entrer un nom pour le joueur "+Integer.toString(i+1)+" : ");
			String nom = terminal.lireChaine();
			Joueur j;
			j = new Joueur(nom, new StrategyJoueurPhysique());
			joueurs.add(j);
		}
		for (int i = 0; i < nombreBot; i++) {
			String nomBot = "Bot " + String.valueOf(i+1);
			joueurs.add(new Joueur(nomBot, new StrategyJoueurBotBourrin()));
		}
	}
	
	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}
	
	/**
	 * Crée les cartes et les mets dans le deck
	 */
	public ConteneurCarte creerDeck() {
		ConteneurCarte cont = new ConteneurCarte();
		cont.addCarte(new Carte(Valeur.AS, Couleur.CARREAU, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.AS, Couleur.COEUR, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.AS, Couleur.TREFLE, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.AS, Couleur.PIQUE, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.DEUX, Couleur.CARREAU, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.DEUX, Couleur.COEUR, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.DEUX, Couleur.TREFLE, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.DEUX, Couleur.PIQUE, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.TROIS, Couleur.CARREAU, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.TROIS, Couleur.COEUR, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.TROIS, Couleur.TREFLE, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.TROIS, Couleur.PIQUE, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.QUATRE, Couleur.CARREAU, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.QUATRE, Couleur.COEUR, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.QUATRE, Couleur.TREFLE, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.QUATRE, Couleur.PIQUE, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.JOKER, Couleur.JOKER, new StrategyTropheeNull()));
		if (variante == 1) {
			cont.addCarte(new Carte(Valeur.JOKER, Couleur.JOKER, new StrategyTropheeNull())); //TODO lui mettre le trophée Suit Majority
		} else if (variante == 2) {
			cont.attribuerTropheesAleatoire();
		}
		cont.melanger();
		
		int nombreTrophees;
		switch (variante) {
		case 1:
			nombreTrophees = 3;
		default:
			nombreTrophees = 2;
		}
		for (int i=0; i<nombreTrophees; i++) {
			cont.distribuerCarte(); //TODO implémenter trophées + retirer la classe quand les trophées sont implémentés
		}		
		
		return cont;
	}
	
	public ConteneurCarte getDeck() {
		return deck;
	}
	
	public ConteneurCarte getStack() {
		return stack;
	}
	
	/**
	 * Fait en sorte que chaque joueur prenne la derniere carte de son jest.
	 */
	public void joueursPrennentDerniereCarte() {
		for (Iterator<Joueur> it = joueurs.iterator(); it.hasNext(); ) {
			Joueur joueur = it.next();
			Carte c = joueur.getOffre().distribuerCarte();
			joueur.getJest().addCarte(c);
		}
	}
	
	public void distribuerTrophees() {
		
	}
	
	/**
	 * Calcule le score de chaque joueur
	 * @return Une HashMap associant les scores (entier) aux joueurs (Joueur)
	 */
	public LinkedHashMap<Joueur, Integer> compterScores() {
		LinkedHashMap<Joueur, Integer> scores = new LinkedHashMap<Joueur, Integer>();
		for (Iterator<Joueur> it = partie.joueurs.iterator(); it.hasNext(); ) {
			Joueur joueur = it.next();
			LinkedList<Carte> jest = joueur.getJest().getCartes();
			Visitor regle1 = new RegleAsVisitor(jest);
			Visitor regle2 = new RegleCouleurVisitor();
			Visitor regle3 = new RegleJockerCoeurVisitor(jest);
			Visitor regle4 = new ReglePairesNoiresVisitor();
			Visitor regle5 = new RegleSavingDiamondsWithLove();
			Iterator<Carte> itCarte = jest.iterator();
			while(itCarte.hasNext()) {
				Carte carte = itCarte.next();
				carte.acceptVisitor(regle1);
				carte.acceptVisitor(regle2);
				carte.acceptVisitor(regle3);
				carte.acceptVisitor(regle4);
				if (variante == 2) { carte.acceptVisitor(regle5); }
			}
			int joueurScore = regle1.getTotalPoint() + regle2.getTotalPoint() + regle3.getTotalPoint() + regle4.getTotalPoint();
			if (variante == 2) { joueurScore += regle5.getTotalPoint(); }
			scores.put(joueur, joueurScore);
		}
		return sortScores(scores);
	}
	
	/**
	 * Sort la HashMap de scores en fonction du score du joueur.
	 * @param scores la HashMap de scores.
	 * @return La HashMap sorted.
	 */
	public LinkedHashMap<Joueur, Integer> sortScores(LinkedHashMap<Joueur, Integer> scores) {
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>(scores.keySet());
		for (int j1 = 0; j1 < joueurs.size(); j1++) {
			int max = j1;
			for (int j2 = j1; j2 < joueurs.size(); j2++) {
				if (scores.get(joueurs.get(j2)) > scores.get(joueurs.get(max))) {
					max = j2;
				}
			}
			joueurs.add(j1, joueurs.remove(max));
		}
		LinkedHashMap<Joueur, Integer> temp = new LinkedHashMap<Joueur, Integer>();
		for (Iterator<Joueur> it = joueurs.iterator(); it.hasNext(); ) {
			Joueur joueur = it.next();
			temp.put(joueur, scores.get(joueur));
		}
		return temp;
	}
	
	/**
	 * Affiche le score de chaque joueur dans le terminal.
	 * @param scores Une HashMap associant les scores (entier) aux joueurs (Joueur)
	 */
	public void afficherScores(LinkedHashMap<Joueur, Integer> scores) {
		terminal.afficherDivision();
		terminal.afficherChaine("Scores : ");
		int i = 0;
		for (Iterator<Joueur> it = scores.keySet().iterator(); it.hasNext(); ) {
			i++;
			Joueur j = it.next();
			terminal.afficherChaine(String.valueOf(i)+" - " + j.getNom() +" avec un score de "+Integer.toString(scores.get(j))+" !");
		}
	}
	
	public static void main(String[] args) {
		partie = getPartie();
		partie.variante = 0;
		
		partie.terminal.afficherChaine("Bienvenue au jeu de Jest !");
		partie.terminal.afficherDivision();
		
		partie.creerJoueurs();
		
		int round_counter = 0;
		while (!partie.deck.isEmpty()) {
			round_counter ++;
			Round round = new Round(round_counter == 1);
			
			round.distribuerCartes();
			round.faireOffres();
			round.prendreCartes();
		}
		
		partie.joueursPrennentDerniereCarte();
		partie.distribuerTrophees();
		LinkedHashMap<Joueur, Integer> scores = partie.compterScores();
		partie.afficherScores(scores);
	}

}
