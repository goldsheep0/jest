package fr.lo02.jest.regle.attributionTrophees;
import java.io.Serializable;
import java.util.*;

import fr.lo02.jest.enums.*;
import fr.lo02.jest.*;
import fr.lo02.jest.bots.*;

/**
 * Stratégie trophée joker.<br>
 * Supporte la sérialisation.
 */
public class StrategyTropheeJoker implements StrategyTrophee,Serializable {
	
	public StrategyTropheeJoker() {}
	
	public String toString() {
		StringBuffer str = new StringBuffer();
		
		str.append("Trophee : ");
		str.append("Joker");
		
		return str.toString();
		
	}
	

	/**
	 * Renvoie le joueur qui possède le Joker dans son jest.
	 */
	public Joueur execute(ArrayList<Joueur> joueurs) {
		Joueur joueurGagnant=new Joueur("Dummy Player",new StrategyJoueurPhysique());
		

		
		Iterator<Joueur> itJoueur = joueurs.iterator();
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
				if(currentCarte.getCouleur()==Couleur.JOKER) {
					joueurGagnant=currentJoueur;
				}
			}
		}
		
		
		
		return joueurGagnant;
	}
	
	
	/**
	 * Test unitaire pour la règle Trophee joker, simule un jest pour 3 joueurs et affiche le jest du joueur gagnant.
	 * @param args
	 */
	public static void main(String[] args) {
		ConteneurCarte cont = new ConteneurCarte();
		
		//cont.addCarte(new Carte(Valeur.AS, Couleur.CARREAU, new StrategyTropheeNull()));
		//cont.addCarte(new Carte(Valeur.AS, Couleur.COEUR, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.AS, Couleur.TREFLE, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.AS, Couleur.PIQUE, new StrategyTropheeHighest(Couleur.PIQUE)));
		cont.addCarte(new Carte(Valeur.DEUX, Couleur.CARREAU, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.DEUX, Couleur.COEUR, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.DEUX, Couleur.TREFLE, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.DEUX, Couleur.PIQUE, new StrategyTropheeHighest(Couleur.PIQUE)));
		cont.addCarte(new Carte(Valeur.TROIS, Couleur.CARREAU, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.TROIS, Couleur.COEUR, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.TROIS, Couleur.TREFLE, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.TROIS, Couleur.PIQUE, new StrategyTropheeHighest(Couleur.PIQUE)));
		cont.addCarte(new Carte(Valeur.QUATRE, Couleur.CARREAU, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.QUATRE, Couleur.COEUR, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.QUATRE, Couleur.TREFLE, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.QUATRE, Couleur.PIQUE, new StrategyTropheeHighest(Couleur.PIQUE)));
		cont.addCarte(new Carte(Valeur.JOKER, Couleur.JOKER, new StrategyTropheeNull()));

		cont.melanger();
		
		Carte trophee = new Carte(Valeur.AS, Couleur.COEUR, new StrategyTropheeJoker());
		
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
		
		System.out.println(trophee.executeStrategyTrophee(joueurs).getNom());

		
		
	}
	
	
	
}
