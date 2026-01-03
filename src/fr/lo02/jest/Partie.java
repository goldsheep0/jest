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

public class Partie implements Serializable{
	
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
	
	
	private Partie() { 
		partieFinie=false;
		roundCounter=0;
		joueurs = new ArrayList<Joueur>();
		deck = creerDeck();
		stack = new ConteneurCarte();
		trophees = new ConteneurCarte();
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
	
	public void setNombreJoueursTotal(int nombreJ) {
		nombreJoueursTotal = nombreJ;
	}
	
	public int getNombreJoueursTotal() {return nombreJoueursTotal;}
	
	public void addJoueurs(int nombreJoueur) {
		for (int i = 0; i < nombreJoueur; i++) {
			terminal.afficherChaine("Entrer un nom pour le joueur "+Integer.toString(i+1)+" : ");
			String nom = terminal.lireChaine();
			Joueur j;
			j = new Joueur(nom, new StrategyJoueurPhysique());
			joueurs.add(j);
		}
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
		
		addJoueurs(nombreJoueursTotal - nombreBot);
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
		
		switch(this.joueurs.size()) {
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
		HashMap<Carte, Joueur> tropheeAvecJoueur = new HashMap<Carte, Joueur>();
		
		Iterator<Carte> itTrophee = tropheeADistribuer.iterator();
		Carte c;
		while(itTrophee.hasNext()) {
			c = itTrophee.next();
			tropheeAvecJoueur.put(c, c.executeStrategyTrophee(this.joueurs));
		}
		
		itTrophee=tropheeADistribuer.iterator();
		
		terminal.afficherDivision();
		terminal.afficherChaine("Trophée(s) à attribuer : \n"+this.trophees.toStringTrophee());

		while(itTrophee.hasNext()) {
			c = itTrophee.next();
			tropheeAvecJoueur.get(c).getJest().addCarte(c);	
			
			if(tropheeAvecJoueur.get(c).getNom().contentEquals("Dummy Player")){
				terminal.afficherChaine("Aucune joueur n'a obtenu le trophée : "+c.toString());
			}else {
				terminal.afficherChaine("Le joueur "+tropheeAvecJoueur.get(c).getNom()+" a obtenu la carte : "+c.toString());
			}
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
			
			oos.writeObject(partie);;
			
			oos.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		terminal.afficherChaine("Partie Sauvegardée !");
		
		return fileName;
	}
	
	
	public Partie chargerPartie() {
		partie = null;
		
		File savesDirectory = new File("./saves");		
		
		File[] existingSaveFiles = savesDirectory.listFiles();
		
		if (existingSaveFiles.length==1) {
			terminal.afficherChaine("Aucun fichier de sauvegarde existant, création d'une nouvelle partie.");
			partie = getPartie();
			partie.lancerPartie();
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
		
		partie.analyserPartie();
		
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
	
	public void lancerPartie() {
		partie = getPartie();
		
		//partie.variante = partie.choisirVariante();
		
		Partie.terminal.afficherChaine("Bienvenue au jeu de Jest !");
		Partie.terminal.afficherDivision();
		
		//partie.creerJoueurs();
		
		partie.creerTrophees();
		
		partie.roundCounter = 0;
		
		partie.analyserPartie();
		
	}
	
	public void analyserPartie() {
		if (!partie.partieFinie) {
			partie.jouer();
			
		}
		
		partie.afficherScores(partie.scores);
		
		Partie.menuSauvegarder(partie);
	}
	
	public void jouer() {
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
	}
	
	public static void main(String[] args) {
		
		switch(Partie.menuDepart()) {
			case 1 :
				getPartie().lancerPartie();
				break;
			case 2 :
				getPartie().chargerPartie();
				break;
			default :
				break;
		}
		
	}

}
