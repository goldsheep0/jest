package fr.lo02.jest.regle.attributionTrophees;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import fr.lo02.jest.Carte;
import fr.lo02.jest.ConteneurCarte;
import fr.lo02.jest.Joueur;
import fr.lo02.jest.Partie;
import fr.lo02.jest.bots.StrategyJoueurPhysique;
import fr.lo02.jest.enums.Couleur;
import fr.lo02.jest.enums.Valeur;

public class StrategyTropheeBestJestNoJoke implements StrategyTrophee{
	
	public StrategyTropheeBestJestNoJoke() {}

	public String toString() {
		StringBuffer str = new StringBuffer();
		
		str.append(", Trophee : ");
		str.append("Best Jest No Joke");
		
		return str.toString();
		
	}
	
	
	/**
	 * Départage le cas ou des joueurs se retrouve avec le même nombre de point. Le joueur ayant
	 * la carte de plus haute valeur parmi les joueurs ex aequo l'emporte.
	 * @param joueurExAequo
	 * @return
	 */
	private Joueur departagerJoueurExAequo(ArrayList<Joueur> joueurExAequo) {
		
		Joueur joueurGagnant=new Joueur("Dummy Player",new StrategyJoueurPhysique());
		
		Carte carteMax = new Carte(Valeur.JOKER,Couleur.JOKER,new StrategyTropheeBestJest());
		
		Iterator<Joueur> itJoueur = joueurExAequo.iterator();
		Iterator<Carte> itJest;
		
		LinkedList<Carte> currentJest;
		Joueur currentJoueur;
		Carte currentCarte;
		
		while(itJoueur.hasNext()) {
			currentJoueur=itJoueur.next();
			currentJest=currentJoueur.getJest().getCartes();
			itJest=currentJest.iterator();
			while(itJest.hasNext()) {
				currentCarte=itJest.next();
				if(currentCarte.equals(currentCarte.compareTo(carteMax))) {
					carteMax=currentCarte;
					joueurGagnant=currentJoueur;
					
				}
			}
		}
		
		return joueurGagnant;		
	}
	
	/**
	 * Renvoie le joueur qui possède le meilleur jest en excluant les joueurs ayant un ou plusieurs joker dans leur jest.
	 * Les égalités sont gérées dans departagerJoueurExAequo().
	 */
	public Joueur execute(ArrayList<Joueur> joueurs) {
		
		Joueur joueurGagnant=new Joueur("Dummy Player",new StrategyJoueurPhysique());
		
		LinkedHashMap<Joueur, Integer> scoresAvecJoker = Partie.getPartie().compterScores();
		
	
		
		LinkedHashMap<Joueur, Integer> scores = new LinkedHashMap<Joueur, Integer>();
		
		Iterator<Joueur> itRmJok = scoresAvecJoker.keySet().iterator();
		Joueur j1;
		Iterator<Carte> itRmJok2;
		boolean hasNoJoke;
		while(itRmJok.hasNext()) {
			hasNoJoke=true;
			j1=itRmJok.next();
			itRmJok2 = j1.getJest().getCartes().iterator();
			while(itRmJok2.hasNext()) {
				if(itRmJok2.next().getCouleur()==Couleur.JOKER) {
					hasNoJoke=false;
				}
			}
			if(hasNoJoke) {
				System.out.println(j1.getNom());
				scores.put(j1, scoresAvecJoker.get(j1));
			}
			
		}
		
		ArrayList<Integer> scoreValues = new ArrayList<Integer>(scores.values());
		
		int score1 = scoreValues.get(0);
		int score2 = scoreValues.get(1);
		if(score1==score2) {
			ArrayList<Joueur> joueurExAequo = new ArrayList<Joueur>();
			for (Iterator<Joueur> it = scores.keySet().iterator(); it.hasNext(); ) {
				Joueur j = it.next();
				if (scores.get(j) == score1) {
					joueurExAequo.add(j);
				}
			}
			joueurGagnant=departagerJoueurExAequo(joueurExAequo);
			
		}else {
			for (Iterator<Joueur> it = scores.keySet().iterator(); it.hasNext(); ) {
				Joueur j = it.next();
				if (scores.get(j) == score1) {
					joueurGagnant = j;
					break;
				}
			};
		}
		return joueurGagnant;
	}



/**
 * Test unitaire pour la règle Trophee best jest no joke, simule un jest pour 3 joueurs et affiche le jest du joueur gagnant. 
 * On peut retirer la ligne cont.melanger() pour obtenir des résultats prévisibles en fonction de l'ordre des cartes dans le paquet.
 * @param args
 */
public static void main(String[] args) {
	ConteneurCarte cont = new ConteneurCarte();
	
	
	
	
	//Si on ne melange pas le deck, jest du joueur 3 :
	cont.addCarte(new Carte(Valeur.AS, Couleur.CARREAU, new StrategyTropheeNull()));
	cont.addCarte(new Carte(Valeur.AS, Couleur.COEUR, new StrategyTropheeNull()));
	cont.addCarte(new Carte(Valeur.DEUX, Couleur.CARREAU, new StrategyTropheeNull()));
	cont.addCarte(new Carte(Valeur.TROIS, Couleur.CARREAU, new StrategyTropheeNull()));
	cont.addCarte(new Carte(Valeur.TROIS, Couleur.COEUR, new StrategyTropheeNull()));
	
	
	cont.addCarte(new Carte(Valeur.JOKER, Couleur.JOKER, new StrategyTropheeBestJest()));
	
	//Si on ne melange pas le deck, jest du joueur 2 :
	cont.addCarte(new Carte(Valeur.TROIS, Couleur.PIQUE, new StrategyTropheeHighest(Couleur.PIQUE)));
	cont.addCarte(new Carte(Valeur.DEUX, Couleur.TREFLE, new StrategyTropheeNull()));
	cont.addCarte(new Carte(Valeur.QUATRE, Couleur.COEUR, new StrategyTropheeNull()));
	cont.addCarte(new Carte(Valeur.AS, Couleur.PIQUE, new StrategyTropheeHighest(Couleur.PIQUE)));
	cont.addCarte(new Carte(Valeur.QUATRE, Couleur.PIQUE, new StrategyTropheeNull()));
	
	
	//Si on ne melange pas le deck, jest du joueur 1 :
	cont.addCarte(new Carte(Valeur.TROIS, Couleur.TREFLE, new StrategyTropheeNull()));
	cont.addCarte(new Carte(Valeur.DEUX, Couleur.PIQUE, new StrategyTropheeHighest(Couleur.PIQUE)));
	cont.addCarte(new Carte(Valeur.DEUX, Couleur.COEUR, new StrategyTropheeNull()));
	cont.addCarte(new Carte(Valeur.AS, Couleur.TREFLE, new StrategyTropheeNull()));
	cont.addCarte(new Carte(Valeur.QUATRE, Couleur.TREFLE, new StrategyTropheeHighest(Couleur.PIQUE)));
	
	
	//cont.addCarte(new Carte(Valeur.JOKER, Couleur.JOKER, new StrategyTropheeNull()));

	cont.melanger();
	
	Carte trophee = new Carte(Valeur.QUATRE, Couleur.CARREAU, new StrategyTropheeBestJestNoJoke());
	
	ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	
	Joueur joueur1 = new Joueur("J1",new StrategyJoueurPhysique());
	joueurs.add(joueur1);
	Joueur joueur2 = new Joueur("J2",new StrategyJoueurPhysique());
	joueurs.add(joueur2);
	Joueur joueur3 = new Joueur("J3",new StrategyJoueurPhysique());
	joueurs.add(joueur3);
	
	
	
	Iterator<Joueur> itJ = joueurs.iterator();
	while(itJ.hasNext()) {
		Joueur currJ=itJ.next();
		for(int i=0; i<5;i++) {
			currJ.getJest().addCarte(cont.distribuerCarte());
		}
		System.out.println("Jest du joueur "+currJ.getNom());
		System.out.println(currJ.getJest().getCartes().toString());
		
	}
	
	Partie.getPartie().setJoueurs(joueurs);

	System.out.println(trophee.executeStrategyTrophee(joueurs).getNom());
	
	
}



}
