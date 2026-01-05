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
public class Partie extends Observable implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static Partie partie = null;
	private ArrayList<Joueur> joueurs;
	private ConteneurCarte deck;
	private ConteneurCarte stack;
	private static Terminal terminal = new Terminal();
	private int variante;
	private ConteneurCarte trophees;
	private int roundCounter;
	private Boolean partieFinie;
	private LinkedHashMap<Joueur, Integer> scores;
	private int nombreJoueursTotal;
	private PartieState state;
	private Joueur joueurFocus;
	private Round round;
	private HashMap<Carte, Joueur> tropheeAvecJoueur;
	
	private Partie() { 
		partieFinie=false;
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
		this.joueurs=joueurs;
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
	
	public void addJoueur(String nom) {
		/*for (int i = 0; i < nombreJoueur; i++) {
			terminal.afficherChaine("Entrer un nom pour le joueur "+Integer.toString(i+1)+" : ");
			String nom = terminal.lireChaine();
			Joueur j;
			j = new Joueur(nom, new StrategyJoueurPhysique());
			joueurs.add(j);
		}*/
		joueurs.add(new Joueur(nom, new StrategyJoueurPhysique()));
	}
	
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
	
	/**
	 * Crée les joueurs dans l'array joueurs
	 */
	public void creerJoueurs() {
		terminal.afficherChaine("Combien de joueurs y a-t-il ? (3 ou 4)");
		nombreJoueursTotal = terminal.lireEntier();
		while (nombreJoueursTotal != 3 && nombreJoueursTotal != 4) {
			terminal.afficherChaine("Mauvaise saisie, tapez (3 ou 4)");
			nombreJoueursTotal = terminal.lireEntier();
		}
		
		terminal.afficherChaine("Combien de bots y a-t-il ?");
		int nombreBot = terminal.lireEntier();
		while (nombreBot < 0 || nombreBot > nombreJoueursTotal) {
			terminal.afficherChaine("Mauvaise saisie, tapez (0 - " + String.valueOf(nombreJoueursTotal) + ")");
			nombreBot = terminal.lireEntier();
		}
		
		//addJoueurs(nombreJoueursTotal - nombreBot);
		//addBots(nombreBot);
		
	}
	
	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}
	
	/**
	 * Crée les cartes et les mets dans le deck
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
		
		//System.out.println(cont.toStringTrophee());
				
		
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
	
	public void distribuerTrophees() {
		
		LinkedList<Carte> tropheeADistribuer = this.trophees.getCartes();
		tropheeAvecJoueur = new HashMap<Carte, Joueur>();
		
		Iterator<Carte> itTrophee = tropheeADistribuer.iterator();
		Carte c;
		while(itTrophee.hasNext()) {
			c = itTrophee.next();
			tropheeAvecJoueur.put(c, c.executeStrategyTrophee(this.joueurs));
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
		for (Iterator<Joueur> it = partie.joueurs.iterator(); it.hasNext(); ) {
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
					//System.out.println("Ajout de la regle Saving Diamonds With Love.");
				}
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
	
	public void setVariante(int newVariante) {
		variante = newVariante;
	}
	
	public int choisirVariante() {
		terminal.afficherChaine("Variantes :\nVariante 1 : Un joker supplémentaire dans les trophées avec le trophée Suit Majority.\nVariante 2 : Ajout de la règle Save Diamonds With Love et Trophées aléatoires sur les cartes\n\nChoisir une variantes entre :\n0 : original\n1 :Variante 1\n2 : Variante 2\n");
		int variante = terminal.lireEntier();
		while (variante != 0 && variante != 1 && variante != 2) {
			terminal.afficherChaine("Mauvaise saisie, tapez (0, 1 ou 2)");
			variante = terminal.lireEntier();
		}
		return variante;
	}
	
	public String sauvegarderPartie(Partie partie) {
		
		File savesDirectory = new File("./saves");		
		
		File[] existingSaveFiles = savesDirectory.listFiles();
		
		HashSet<String> existingSaveNames = new HashSet<String>();
		
		for( File f : existingSaveFiles) {
			existingSaveNames.add(f.getName());
		}
		
			
		terminal.afficherChaine("Entrer le nom du fichier à sauvergarder : ");
		String fileName = terminal.lireChaine();
		
		while(existingSaveNames.contains(fileName) && fileName.equals("keep_this_directory.gitignore")) {
			if(!fileName.equals("keep_this_directory.gitignore")) {
				terminal.afficherChaine("Le nom de fichier donné est déjà existant, voulez vous écraser cette sauvegarde ? (O/N)");
				String choix = terminal.lireChaine();
				while (!choix.equals("O") && !choix.equals("N")) {
					terminal.afficherChaine("Mauvaise saisie, tapez (O ou N)");
					choix = terminal.lireChaine();
				}
				if(choix.equals("O")) {
					File file = new File("./saves/"+fileName);
					try {
						Files.deleteIfExists(file.toPath());
						existingSaveNames.remove(fileName);
						
						terminal.afficherChaine("Sauvegarde écrasée.");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else if(choix.equals("N")) {
					terminal.afficherChaine("Tapez un nouveau nom de fichier");
					fileName = terminal.lireChaine();
				}
			}else {
				terminal.afficherChaine("Tapez un nouveau nom de fichier");
				fileName = terminal.lireChaine();
			}
			
		}
		
		File file = new File("./saves/"+fileName);
		
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(partie);
			
			oos.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		terminal.afficherChaine("Partie Sauvegardée !");
		
		return fileName;
	}
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Partie chargerPartie() {
		partie = null;
		
		File savesDirectory = new File("./saves");		
		
		File[] existingSaveFiles = savesDirectory.listFiles();
		
		if (existingSaveFiles.length==1) {
			terminal.afficherChaine("Aucun fichier de sauvegarde existant, création d'une nouvelle partie.");
			partie = getPartie();
			//partie.lancerPartie();
			return partie;
		}
		
		HashSet<String> existingSaveNames = new HashSet<String>();
		
		
		terminal.afficherChaine("Fichiers sauvegardes existants : ");
		for( File f : existingSaveFiles) {
			if(!f.getName().equals("keep_this_directory.gitignore")) {
				existingSaveNames.add(f.getName());
				System.out.println(f.getName());
			}
		}
		
			
		terminal.afficherChaine("Entrer le nom du fichier à charger : ");
		String fileName = terminal.lireChaine();
		
		while(!existingSaveNames.contains(fileName)) {
			terminal.afficherChaine("Le nom de fichier donné n'est pas dans la liste, veuillez en entrer un existant : ");
			fileName = terminal.lireChaine();
		}
		
		
		File file = new File("./saves/"+fileName);
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);	
					
			try {
				partie= (Partie) ois.readObject();
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			fis.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//partie.analyserPartie();
		
		return partie;
	}
	
	public static int menuDepart() {
		terminal.afficherChaine("Nouvelle Partie (1)\nCharger partie (2)");
		int choix = terminal.lireEntier();
		while (choix != 1 && choix != 2) {
			terminal.afficherChaine("Mauvaise saisie, tapez (1 ou 2)");
			choix = terminal.lireEntier();
		}
		return choix;
	}
	
	public static void menuSauvegarder(Partie partie) {
		terminal.afficherChaine("Sauvegarder la partie ? (O/N)");
		String choix = terminal.lireChaine();
		while (!choix.equals("O") && !choix.equals("N")) {
			terminal.afficherChaine("Mauvaise saisie, tapez (O ou N)");
			choix = terminal.lireChaine();
		}
		
		if(choix.equals("O")) {
			partie.sauvegarderPartie(partie);
		}
		
	}
	
	/*public void lancerPartie() {
		partie = getPartie();
		
		//partie.variante = partie.choisirVariante();
		
		Partie.terminal.afficherChaine("Bienvenue au jeu de Jest !");
		Partie.terminal.afficherDivision();
		
		//partie.creerJoueurs();
		
		partie.creerTrophees();
		
		partie.roundCounter = 0;
		
		partie.analyserPartie();
		
	}*/
	
	/*public void analyserPartie() {
		if (!partie.partieFinie) {
			partie.jouer();
			
		}
		
		partie.afficherScores(partie.scores);
		
		Partie.menuSauvegarder(partie);
	}*/
	
	public void nouveauRound() {
		if (deck.isEmpty()) {
			joueursPrennentDerniereCarte();
			distribuerTrophees();
			scores = compterScores();
			partieFinie=true;
			changeState(PartieState.TROPHEES);
		} else {
			roundCounter ++;
			round = new Round(roundCounter == 1);
			round.distribuerCartes();
			partie.changeState(PartieState.NEW_ROUND);
		}
	}
	
	/*public void jouer() {
		while (!partie.deck.isEmpty()) {
			partie.roundCounter ++;
			Round round = new Round(partie.roundCounter == 1);
			
			round.distribuerCartes();
			
			round.faireOffres();
			round.prendreCartes();
			
			Partie.menuSauvegarder(partie);
		}
		
		partie.joueursPrennentDerniereCarte();
		partie.distribuerTrophees();
		partie.scores = partie.compterScores();
		partie.partieFinie=true;
	}*/
	
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
		
		/*switch(Partie.menuDepart()) {
			case 1 :
				getPartie().lancerPartie();
				break;
			case 2 :
				getPartie().chargerPartie();
				break;
			default :
				break;
		}*/
		
	}

}
