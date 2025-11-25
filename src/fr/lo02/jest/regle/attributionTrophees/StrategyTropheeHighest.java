package fr.lo02.jest.regle.attributionTrophees;
import java.util.*;

import fr.lo02.jest.enums.*;
import fr.lo02.jest.*;
import fr.lo02.jest.bots.*;

public class StrategyTropheeHighest implements StrategyTrophee {
	private Couleur couleur;
	
	public StrategyTropheeHighest(Couleur couleur) {
		this.couleur=couleur;		}

	/**
	 * Renvoie le joueur ayant la carte de plus haute valeur de la couleur indiquée dans son jest.
	 */
	public Joueur execute(ArrayList<Joueur> joueurs) {
		Joueur joueurGagnant=joueurs.getFirst();
		
		Valeur valeurMax=Valeur.JOKER;
		
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
				if(currentCarte.getCouleur()==this.couleur) {
					if(currentCarte.getValeur().ordinal()>valeurMax.ordinal()) {
						valeurMax=currentCarte.getValeur();
						joueurGagnant=currentJoueur;
					}
				}
			}
		}
		
		
		
		return joueurGagnant;
	}
	
	
	/**
	 * Test unitaire pour la règle Trophee highest, simule un jest pour 3 joueurs et affiche le jest du joueur gagnant.
	 * @param args
	 */
	public static void main(String[] args) {
		ConteneurCarte cont = new ConteneurCarte();
		
		//cont.addCarte(new Carte(Valeur.AS, Couleur.CARREAU, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.AS, Couleur.COEUR, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.AS, Couleur.TREFLE, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.AS, Couleur.PIQUE, new StrategyTropheeHighest(Couleur.PIQUE)));
		cont.addCarte(new Carte(Valeur.DEUX, Couleur.CARREAU, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.DEUX, Couleur.COEUR, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.DEUX, Couleur.TREFLE, new StrategyTropheeNull()));
		//cont.addCarte(new Carte(Valeur.DEUX, Couleur.PIQUE, new StrategyTropheeHighest(Couleur.PIQUE)));
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
		
		Carte trophee = new Carte(Valeur.DEUX, Couleur.PIQUE, new StrategyTropheeHighest(Couleur.PIQUE));
		
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
