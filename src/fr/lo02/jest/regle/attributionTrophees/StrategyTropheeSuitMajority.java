package fr.lo02.jest.regle.attributionTrophees;

import java.io.Serializable;
import java.util.*;

import fr.lo02.jest.Carte;
import fr.lo02.jest.ConteneurCarte;
import fr.lo02.jest.Joueur;
import fr.lo02.jest.Partie;
import fr.lo02.jest.bots.StrategyJoueurPhysique;
import fr.lo02.jest.enums.Couleur;
import fr.lo02.jest.enums.Valeur;

public class StrategyTropheeSuitMajority implements StrategyTrophee,Serializable{
	
	public StrategyTropheeSuitMajority() {}
	
	
	
	/**
	 * Renvoie le joueur qui possède le plus grand nombre de carte d'une même couleur. Les égalités sont départagées par celui qui a la couleur de plus haute valeur.
	 */
	public Joueur execute(ArrayList<Joueur> joueurs) {
		
		Joueur joueurGagnant;
		
		LinkedHashMap<Joueur, Integer> suitMax = new LinkedHashMap<Joueur, Integer>();
		LinkedHashMap<Joueur, Couleur> suitMaxCouleur = new LinkedHashMap<Joueur, Couleur>();
		
		Iterator<Joueur> itJoueur = joueurs.iterator();
		Iterator<Carte> itCarte;
		Iterator<Couleur> itCouleur;
		
		Joueur currJoueur;
		
		HashMap<Couleur, Integer> currNbCouleur = new HashMap<Couleur, Integer>();
		
		currNbCouleur.put(Couleur.PIQUE, 0);
		currNbCouleur.put(Couleur.TREFLE, 0);
		currNbCouleur.put(Couleur.COEUR, 0);
		currNbCouleur.put(Couleur.CARREAU, 0);
		
		Couleur currCouleur;
		Couleur maxCouleur;
		
		while(itJoueur.hasNext()) {
			currJoueur=itJoueur.next();
			
			currNbCouleur.put(Couleur.PIQUE, 0);
			currNbCouleur.put(Couleur.TREFLE, 0);
			currNbCouleur.put(Couleur.COEUR, 0);
			currNbCouleur.put(Couleur.CARREAU, 0);
			
			
			itCarte=currJoueur.getJest().getCartes().iterator();
			while(itCarte.hasNext()){
				switch(itCarte.next().getCouleur()) {
					case Couleur.PIQUE:
						currNbCouleur.put(Couleur.PIQUE, currNbCouleur.get(Couleur.PIQUE)+1);
						break;
					case Couleur.TREFLE:
						currNbCouleur.put(Couleur.TREFLE, currNbCouleur.get(Couleur.TREFLE)+1);
						break;
					case Couleur.COEUR:
						currNbCouleur.put(Couleur.COEUR, currNbCouleur.get(Couleur.COEUR)+1);
						break;
					case Couleur.CARREAU:
						currNbCouleur.put(Couleur.CARREAU, currNbCouleur.get(Couleur.CARREAU)+1);
						break;
					default:
						break;
				}
			}
			
			itCouleur = currNbCouleur.keySet().iterator();
			maxCouleur=itCouleur.next();
			
			while(itCouleur.hasNext()) {
				currCouleur=itCouleur.next();
				if(currNbCouleur.get(maxCouleur)<currNbCouleur.get(currCouleur)) {
					maxCouleur=currCouleur;
				}else if(currNbCouleur.get(maxCouleur)==currNbCouleur.get(currCouleur)) {
					if(maxCouleur.ordinal()>currCouleur.ordinal())
						maxCouleur=currCouleur;
				}
			}
			
			suitMax.put(currJoueur,currNbCouleur.get(maxCouleur));
			suitMaxCouleur.put(currJoueur,maxCouleur);	
		}
		
		itJoueur = joueurs.iterator();
		
		joueurGagnant=itJoueur.next();
		
		while(itJoueur.hasNext()) {
			currJoueur=itJoueur.next();
			if(suitMax.get(joueurGagnant)<suitMax.get(currJoueur)) {
				joueurGagnant=currJoueur;
			}else if(suitMax.get(joueurGagnant)==suitMax.get(currJoueur)) {
				if(suitMaxCouleur.get(joueurGagnant).ordinal()>suitMaxCouleur.get(currJoueur).ordinal()){
					joueurGagnant=currJoueur;
				}
			}

		}
		
		return joueurGagnant;
	}
	
	public String toString() {
		StringBuffer str = new StringBuffer();
		
		str.append("Trophee : ");
		str.append("Suit Majority");
		
		return str.toString();
		
	}
	
	
	
	/**
	 * Test unitaire pour la règle Trophee best jest, simule un jest pour 3 joueurs et affiche le jest du joueur gagnant. 
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
		
		
		//cont.addCarte(new Carte(Valeur.QUATRE, Couleur.CARREAU, new StrategyTropheeNull()));
		
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
		cont.addCarte(new Carte(Valeur.JOKER, Couleur.JOKER, new StrategyTropheeNull()));

		cont.melanger();
		
		Carte trophee = new Carte(Valeur.JOKER, Couleur.JOKER, new StrategyTropheeSuitMajority());
		
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
