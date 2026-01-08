package fr.lo02.jest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.*;

import fr.lo02.jest.bots.StrategyJoueurBotBourrin;
import fr.lo02.jest.bots.StrategyJoueurBotTrophee;
import fr.lo02.jest.bots.StrategyJoueurPhysique;
import fr.lo02.jest.enums.*;
import fr.lo02.jest.regle.attributionTrophees.*;
import fr.lo02.jest.regle.calculepoint.*;
import fr.lo02.texte.VueTexte;
import fr.lo02.ui.MainWindow;

@SuppressWarnings("deprecation")
/**
 * Point d'entrée du programme.<br>
 * La classe partie contient des références aux éléments principaux du jeu.<br>
 * Elle permet d'initialiser l'interface graphique et l'interface texte.<br>
 * Elle contient la plupart des méthodes qui permettent à une partie de se dérouler du début à la fin.<br>
 * La classe Partie est un singleton.<br>
 * Elle supporte la sérialisation.<br>
 * Observable, observée par une VueTexte et une MainWindow 
 */
public class Partie extends Observable implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Référence à l'instance unique de Partie
	 */
	private static Partie partie = null;
	
	/**
	 * Liste des joueurs de la partie.
	 */
	private static ArrayList<Joueur> joueurs;
	
	/**
	 * Référence au deck contenant toutes les cartes.
	 */
	private ConteneurCarte deck;
	
	/**
	 * Référence au stack qui permet de stocker temporairement les cartes à redistribuer entre les rounds.
	 */
	private ConteneurCarte stack;
	
	/**
	 * Référence au terminal utilisé pour la vue texte.
	 */
	private static Terminal terminal = new Terminal();
	
	/**
	 * Variante utilisée lors de la partie :<br>
	 * 0 : règles originales.<br>
	 * 1 : variante 1 : un joker supplémentaire ajouté dans les trophées avec la règle Suit Majority.<br>
	 * 2 : variante 2 : les trophées sont attribués aléatoirement et la règle Save Diamonds with love est ajoutée.<br>
	 */
	private int variante;
	
	/**
	 * Référence au trophées qui contient la liste des trophées utilisés lors de la partie.
	 */
	private ConteneurCarte trophees;
	
	/**
	 * Nombre de rounds effectués.
	 */
	private int roundCounter;
	
	/**
	 * LinkedHashMap des scores des joueurs, triée par score.<br>
	 * Les références vers les joueurs servent de clé.
	 */
	private LinkedHashMap<Joueur, Integer> scores;
	
	/**
	 * Nombre de joueurs dans la partie.
	 */
	private int nombreJoueursTotal;
	
	/**
	 * Etat de la partie qui permet de déterminer quelles vues afficher et quelles actions effectuer.
	 */
	private PartieState state;
	
	/**
	 * Référence au joueur dont le tour est en cours.
	 */
	private Joueur joueurFocus;
	
	/**
	 * Référence au round en cours.
	 */
	private Round round;
	
	/**
	 * HashMap des Joueurs ayant reçu un trophée.<br>
	 * Les références vers les trophées servent de clé.
	 */
	private HashMap<Carte, Joueur> tropheeAvecJoueur;
	
	private Partie() {
		roundCounter=0;
		joueurs = new ArrayList<Joueur>();
		deck = creerDeck();
		stack = new ConteneurCarte();
		trophees = new ConteneurCarte();
		state = PartieState.START;
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
	
	public Round getRound () {
		return round;
	}
	
	public void setJoueurs(ArrayList<Joueur> joueurs) {
		Partie.joueurs=joueurs;
	}
	
	public Terminal getTerminal() {
		return terminal;
	}
	
	public void setNombreJoueursTotal(int nombreJ) {
		nombreJoueursTotal = nombreJ;
	}
	
	public Joueur getJoueurFocus() {
		return joueurFocus;
	}
	public void setJoueurFocus(Joueur newJoueur) {
		joueurFocus = newJoueur;
	}
	
	public HashMap<Carte, Joueur> getTropheesJoueurs() {
		return tropheeAvecJoueur;
	}
	
	public int getNombreJoueursTotal() {return nombreJoueursTotal;}
	
	/**
	 * Ajoute un nouveau joueur avec la stratégie StrategyJoueurPhysique à la liste de joueurs de Partie.
	 * @param nom - nom du joueur
	 */
	public void addJoueur(String nom) {
		joueurs.add(new Joueur(nom, new StrategyJoueurPhysique()));
	}
	
	/**
	 * Ajoute un nombre bot avec la stratégie sélectionnée à la liste de joueur de Partie.
	 * @param nombreBot - nombre de bot à ajouter
	 * @param botType - 0 : StrategyJoueurBotBourrin ; >0 : StrategyJoueurBotTrophee
	 */
	public void addBots(int nombreBot, int botType) {
		for (int i = 0; i < nombreBot; i++) {
			String nomBot = "Bot " + String.valueOf(i+1);
			if (botType == 0) {
				joueurs.add(new Joueur(nomBot + " (Bourrin)", new StrategyJoueurBotBourrin()));
			} else {
				joueurs.add(new Joueur(nomBot + " (Trophée)", new StrategyJoueurBotTrophee()));
			}
		}
	}
	

	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}
	
	/**
	 * Créer un deck de carte selon les cartes présentes dans les règles du jeu, avec les trophées correspondant.
	 * 
	 * @return type : ConteneurCarte - ConteneurCarte avec le deck complet
	 */
	public ConteneurCarte creerDeck() {
		ConteneurCarte cont = new ConteneurCarte();
		cont.addCarte(new Carte(Valeur.AS, Couleur.CARREAU, new StrategyTropheeMajority(Valeur.QUATRE)));
		cont.addCarte(new Carte(Valeur.AS, Couleur.COEUR, new StrategyTropheeJoker()));
		cont.addCarte(new Carte(Valeur.AS, Couleur.TREFLE, new StrategyTropheeHighest(Couleur.PIQUE)));
		cont.addCarte(new Carte(Valeur.AS, Couleur.PIQUE, new StrategyTropheeHighest(Couleur.TREFLE)));
		cont.addCarte(new Carte(Valeur.DEUX, Couleur.CARREAU, new StrategyTropheeHighest(Couleur.CARREAU)));
		cont.addCarte(new Carte(Valeur.DEUX, Couleur.COEUR, new StrategyTropheeJoker()));
		cont.addCarte(new Carte(Valeur.DEUX, Couleur.TREFLE, new StrategyTropheeLowest(Couleur.PIQUE)));
		cont.addCarte(new Carte(Valeur.DEUX, Couleur.PIQUE, new StrategyTropheeMajority(Valeur.TROIS)));
		cont.addCarte(new Carte(Valeur.TROIS, Couleur.CARREAU, new StrategyTropheeLowest(Couleur.CARREAU)));
		cont.addCarte(new Carte(Valeur.TROIS, Couleur.COEUR, new StrategyTropheeJoker()));
		cont.addCarte(new Carte(Valeur.TROIS, Couleur.TREFLE, new StrategyTropheeHighest(Couleur.COEUR)));
		cont.addCarte(new Carte(Valeur.TROIS, Couleur.PIQUE, new StrategyTropheeMajority(Valeur.DEUX)));
		cont.addCarte(new Carte(Valeur.QUATRE, Couleur.CARREAU, new StrategyTropheeBestJestNoJoke()));
		cont.addCarte(new Carte(Valeur.QUATRE, Couleur.COEUR, new StrategyTropheeJoker()));
		cont.addCarte(new Carte(Valeur.QUATRE, Couleur.TREFLE, new StrategyTropheeLowest(Couleur.PIQUE)));
		cont.addCarte(new Carte(Valeur.QUATRE, Couleur.PIQUE, new StrategyTropheeLowest(Couleur.TREFLE)));
		cont.addCarte(new Carte(Valeur.JOKER, Couleur.JOKER, new StrategyTropheeBestJest()));
		cont.melanger();
		
		return cont;
	}
	
	public ConteneurCarte getDeck() {
		return deck;
	}
	
	public ConteneurCarte getStack() {
		return stack;
	}
	
	/**
	 * Fait en sorte que chaque joueur prenne la derniere carte de son jest lorsque le dernier round est terminée.
	 */
	public void joueursPrennentDerniereCarte() {
		for (Iterator<Joueur> it = joueurs.iterator(); it.hasNext(); ) {
			Joueur joueur = it.next();
			Carte c = joueur.getOffre().distribuerCarte();
			joueur.getJest().addCarte(c);
		}
	}
	
	/**
	 * En fonction de la variante choisie et du nombre de joueurs :<br>
	 * Original : distribue deux des cartes du deck dans le conteneur des trophees de Partie si il y a 3 joueurs, une seule sinon<br>
	 * Variante 1 : fait comme l'original et rajoute en plus un joker avec le trophée SuitMajority<br>
	 * Variante 2 : attribue des trophées aléatoire à chaque carte du deck puis fait comme l'original
	 */
	public void creerTrophees() {
		int nbtrophees=0;
		
		switch(this.variante) {
			case 1:
				this.trophees.addCarte(new Carte(Valeur.JOKER, Couleur.JOKER, new StrategyTropheeSuitMajority()));
				break;
			case 2:
				this.deck.attribuerTropheesAleatoire();
				break;
			default:
				break;
		}
		
		switch(this.nombreJoueursTotal) {
			case 3:
				nbtrophees+=2;
				break;
			case 4 :
				nbtrophees++;
				break;
			default:
				break;
		}
		
		for(int i=0;i<nbtrophees;i++) {
			this.trophees.addCarte(this.deck.distribuerCarte());
		}
		
		Partie.terminal.afficherDivision();
		
		Partie.terminal.afficherChaine("Trophee pour cette partie : \n"+this.trophees.toStringTrophee());
	}
	
	/**
	 * A la fin de la partie. Permet de distribuer les trophées aux joueurs qui les remportent.
	 * Place les paires Carte-Joueur dans l'attribut tropheeAvecJoueur de Partie.
	 */
	public void distribuerTrophees() {
		
		LinkedList<Carte> tropheeADistribuer = this.trophees.getCartes();
		tropheeAvecJoueur = new HashMap<Carte, Joueur>();
		
		Iterator<Carte> itTrophee = tropheeADistribuer.iterator();
		Carte c;
		while(itTrophee.hasNext()) {
			c = itTrophee.next();
			tropheeAvecJoueur.put(c, c.executeStrategyTrophee(Partie.joueurs));
		}
		
		itTrophee=tropheeADistribuer.iterator();

		while(itTrophee.hasNext()) {
			c = itTrophee.next();
			tropheeAvecJoueur.get(c).getJest().addCarte(c);	
		}
		
	}
	
	public ConteneurCarte getTrophees() {
		return this.trophees;
	}
	
	/**
	 * Calcule le score de chaque joueur
	 * @return Une HashMap associant les scores (entier) aux joueurs (Joueur)
	 */
	public LinkedHashMap<Joueur, Integer> compterScores() {
		LinkedHashMap<Joueur, Integer> scores = new LinkedHashMap<Joueur, Integer>();
		for (Iterator<Joueur> it = Partie.joueurs.iterator(); it.hasNext(); ) {
			Joueur joueur = it.next();
			LinkedList<Carte> jest = joueur.getJest().getCartes();
			Visitor regle1 = new RegleAsVisitor(jest);
			Visitor regle2 = new RegleCouleurVisitor();
			Visitor regle3 = new RegleJockerCoeurVisitor(jest);
			Visitor regle4 = new ReglePairesNoiresVisitor();
			Visitor regle5 = new RegleSavingDiamondsWithLove(jest);
			Iterator<Carte> itCarte = jest.iterator();
			while(itCarte.hasNext()) {
				Carte carte = itCarte.next();
				carte.acceptVisitor(regle1);
				carte.acceptVisitor(regle2);
				carte.acceptVisitor(regle3);
				carte.acceptVisitor(regle4);
				if (variante == 2) { 
					carte.acceptVisitor(regle5);
				}
			}
			int joueurScore = regle1.getTotalPoint() + regle2.getTotalPoint() + regle3.getTotalPoint() + regle4.getTotalPoint();
			if (variante == 2) { joueurScore += regle5.getTotalPoint(); }
			scores.put(joueur, joueurScore);
		}
		return sortScores(scores);
	}
	
	/**
	 * Trie la HashMap de scores en fonction du score du joueur.
	 * @param scores la HashMap de scores.
	 * @return La HashMap triée.
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
	
	public void setVariante(int newVariante) {
		variante = newVariante;
	}
	
	/**
	 * Permet de sauvegarder la partie en cours dans un fichier en utilisant la sérialisation.
	 * @param partie - référence vers la partie à sauvegarder
	 * @param nomFichier - nom à utiliser pour le fichier
	 */
	public void sauvegarderPartie (Partie partie, String nomFichier) {
		
		File file = new File("./saves/"+nomFichier);
		try {
			Files.deleteIfExists(file.toPath());
			
			terminal.afficherChaine("Sauvegarde écrasée.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(partie);
			
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Permet de charger un fichier d'une sauvegarder précédente.<br>
	 * Non fonctionnel dans la version actuelle :<br>
	 * La fonction essaie d'attribuer l'objet Partie chargé au singleton Partie mais celui-ci est déjà créé<br>
	 * Cela créer un conflit entre les références vers Partie déjà passées en argument d'autre classe et méthode et la valeur actuelle attribuée au singleton Partie.
	 * @param nom_fichier - nom du fichier à charger, doit être un fichier existant en mémoire.
	 */
	public void chargerFichier(String nom_fichier) {
		partie = null;
		
		File file = new File("./saves/"+nom_fichier);
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);	
					
			try {
				partie= (Partie) ois.readObject();
				
				if (partie.getDeck().isEmpty()) {
					partie.changeState(PartieState.SCOREBOARD);
				} else {
					partie.changeState(PartieState.NEW_ROUND);
				}
			} catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			fis.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Permet de créer un nouveau round ou d'initialiser la séquence de fin de partie si la partie est finie.
	 */
	public void nouveauRound() {
		if (deck.isEmpty()) {
			joueursPrennentDerniereCarte();
			distribuerTrophees();
			this.scores = compterScores();
			changeState(PartieState.TROPHEES);
		} else {
			roundCounter ++;
			round = new Round(roundCounter == 1);
			round.distribuerCartes();
			partie.changeState(PartieState.NEW_ROUND);
		}
	}
	
	/**
	 * Change l'état de la partie vers l'état passé en argument. Notifie les observers de Partie du changement d'état.
	 * @param newState - état vers lequel changer l'état de la partie
	 */
	public void changeState (PartieState newState) {
		state = newState;
		if (state == PartieState.C_BOTS) {
			creerTrophees();
		}
		this.setChanged();
		this.notifyObservers(state);
	}
	
	
	public static void main(String[] args) {
		
		partie = getPartie();
		partie.roundCounter = 0;
		
		MainWindow mw = new MainWindow();
		VueTexte vt = new VueTexte();
		
		partie.addObserver(mw);
		partie.addObserver(vt);
		
		mw.start();
		vt.start();
		
		partie.changeState(PartieState.START);
		
	}

}
