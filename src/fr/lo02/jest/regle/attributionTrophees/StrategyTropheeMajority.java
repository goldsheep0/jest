package fr.lo02.jest.regle.attributionTrophees;
import java.io.Serializable;
import java.util.*;

import fr.lo02.jest.enums.*;
import fr.lo02.jest.*;
import fr.lo02.jest.bots.*;

/**
 * Stratégie trophée majority.<br>
 * Supporte la sérialisation.
 */
public class StrategyTropheeMajority implements StrategyTrophee,Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Valeur où chercher le plus grand nombre de carte.
	 */
	private Valeur valeur;
	
	public StrategyTropheeMajority(Valeur valeur) {
		this.valeur=valeur;		}
	
	public Valeur getValeur() {
		return valeur;
	}
	
	public String toString() {
		StringBuffer str = new StringBuffer();
		
		str.append("Trophee : ");
		str.append("Majority ");
		str.append(this.valeur.toString());
		
		return str.toString();
		
	}
	

	/**
	 * Renvoie le joueur qui possède le plus de carte de la valeur indiquée.<br>
	 * Les égalités sont départagées en prenant la carte qui a la couleur de plus grande valeur.
	 */
	public Joueur execute(ArrayList<Joueur> joueurs) {
		Joueur joueurGagnant=joueurs.getFirst();
		
		int maxAmount=0;
		
		Iterator<Joueur> itJoueur = joueurs.iterator();
		Iterator<Carte> itJest;
		
		LinkedList<Carte> currentJest;
		Joueur currentJoueur;
		Carte currentCarte;
		
		Couleur currentCouleurMax=Couleur.JOKER;
		Couleur couleurMax=Couleur.JOKER;
		
		int currentAmount=0;
		
		while(itJoueur.hasNext()) {
			
			currentJoueur=itJoueur.next();
			currentJest=currentJoueur.getJest().getCartes();
			itJest=currentJest.iterator();
			while(itJest.hasNext()) {
				currentCarte=itJest.next();
				if(currentCarte.getValeur()==this.valeur) {
					currentAmount++;
					if(currentCarte.getCouleur().ordinal()<currentCouleurMax.ordinal()) {
						currentCouleurMax=currentCarte.getCouleur();
					}
				}
			}
			if(currentAmount>maxAmount) {
				maxAmount=currentAmount;
				joueurGagnant=currentJoueur;
			}else if(currentAmount==maxAmount) {
				if(couleurMax.ordinal()>currentCouleurMax.ordinal()) {
					maxAmount=currentAmount;
					couleurMax=currentCouleurMax;
					joueurGagnant=currentJoueur;
				}
			}
			currentAmount=0;
			currentCouleurMax=Couleur.JOKER;
			
		}
		
		
		
		return joueurGagnant;
	}
	
	
	/**
	 * Test unitaire pour la règle Trophee majority, simule un jest pour 3 joueurs et affiche le jest du joueur gagnant.
	 * @param args
	 */
	public static void main(String[] args) {
		ConteneurCarte cont = new ConteneurCarte();
		
		//cont.addCarte(new Carte(Valeur.AS, Couleur.CARREAU, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.AS, Couleur.COEUR, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.AS, Couleur.TREFLE, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.AS, Couleur.PIQUE, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.DEUX, Couleur.CARREAU, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.DEUX, Couleur.COEUR, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.DEUX, Couleur.TREFLE, new StrategyTropheeNull()));
		//cont.addCarte(new Carte(Valeur.DEUX, Couleur.PIQUE, new StrategyTropheeHighest(Couleur.PIQUE)));
		cont.addCarte(new Carte(Valeur.TROIS, Couleur.CARREAU, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.TROIS, Couleur.COEUR, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.TROIS, Couleur.TREFLE, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.TROIS, Couleur.PIQUE, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.QUATRE, Couleur.CARREAU, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.QUATRE, Couleur.COEUR, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.QUATRE, Couleur.TREFLE, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.QUATRE, Couleur.PIQUE, new StrategyTropheeNull()));
		cont.addCarte(new Carte(Valeur.JOKER, Couleur.JOKER, new StrategyTropheeNull()));

		cont.melanger();
		
		Carte trophee = new Carte(Valeur.DEUX, Couleur.PIQUE, new StrategyTropheeMajority(Valeur.DEUX));
		
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
