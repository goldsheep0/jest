package fr.lo02.jest;

import java.io.Serializable;
import java.util.*;

import fr.lo02.jest.enums.*;
import fr.lo02.jest.regle.attributionTrophees.StrategyTrophee;
import fr.lo02.jest.regle.attributionTrophees.StrategyTropheeBestJest;
import fr.lo02.jest.regle.attributionTrophees.StrategyTropheeBestJestNoJoke;
import fr.lo02.jest.regle.attributionTrophees.StrategyTropheeHighest;
import fr.lo02.jest.regle.attributionTrophees.StrategyTropheeJoker;
import fr.lo02.jest.regle.attributionTrophees.StrategyTropheeLowest;
import fr.lo02.jest.regle.attributionTrophees.StrategyTropheeMajority;
import fr.lo02.jest.regle.attributionTrophees.StrategyTropheeSuitMajority;

public class ConteneurCarte implements Serializable{
	
	private LinkedList<Carte> cartes;
	
	public ConteneurCarte() {
		cartes = new LinkedList<Carte>();
	}
	
	public Carte distribuerCarte() {
		Carte c;
		c = cartes.removeLast();
		return c;
	}
	
	public Carte distribuerCarte(Carte c) {
		cartes.remove(c);
		return c;
	}
	
	public void melanger() {
		Collections.shuffle(cartes);
	}
	
	public boolean isEmpty() {
		return cartes.isEmpty();
	}
	
	public String toString() {
			
		return this.cartes.toString();
	}
	
	public LinkedList<Carte> getCartes() {
		return cartes;
	}
	
	public void addCarte(Carte c) {
		cartes.add(c);
	}
	
	public Carte retirerCarteFaceVisible(boolean faceVisible) {
		for (int carteIndex = 0; carteIndex < cartes.size(); carteIndex++) {
			if (cartes.get(carteIndex).estFaceVisible() == faceVisible) {
				return cartes.remove(carteIndex);
			}
		}
		return null;
	}
	
	public Carte getCarteVisible() {
		for (int carteIndex = 0; carteIndex < cartes.size(); carteIndex++) {
			if (cartes.get(carteIndex).estFaceVisible()) {
				return cartes.get(carteIndex);
			}
		}
		return null;
	}
	
	public String toStringTrophee() {
		
		StringBuffer str = new StringBuffer();
		
		Iterator<Carte> it = this.cartes.iterator();
		
		
		while(it.hasNext()) {
			str.append(it.next().toStringTrophee());
			str.append("\n");
		}
	
		return str.toString();
		
		
	}
	
	public Carte hasCarte (Couleur couleur) {
		for (Iterator<Carte> it = cartes.iterator(); it.hasNext(); ) {
			Carte c = it.next();
			if (c.getCouleur() == couleur) {
				return c;
			}
		}
		return null;
	}
	
	public Carte hasCarte (Valeur valeur) {
		for (Iterator<Carte> it = cartes.iterator(); it.hasNext(); ) {
			Carte c = it.next();
			if (c.getValeur() == valeur) {
				return c;
			}
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public Carte hasCarte (Class strat) {
		for (Iterator<Carte> it = cartes.iterator(); it.hasNext(); ) {
			Carte c = it.next();
			if (c.getStrategyTrophee().getClass().equals(strat)) {
				return c;
			}
		}
		return null;
	}
	
	public void attribuerTropheesAleatoire() {
		Iterator<Carte> it = this.cartes.iterator();
		
		Random pickTrophee = new Random();
		Random pickCouleur = new Random();
		Random pickValeur = new Random();
		
		while(it.hasNext()) {
			switch(pickTrophee.nextInt(6)) {
				case 0:
					it.next().setStrategyTrophee(new StrategyTropheeBestJest());
					break;
				case 1:
					it.next().setStrategyTrophee(new StrategyTropheeBestJestNoJoke());
					break;
				case 2:
					it.next().setStrategyTrophee(new StrategyTropheeJoker());
					break;
				case 3:
					switch(pickCouleur.nextInt(4)) {
						case 0:
							it.next().setStrategyTrophee(new StrategyTropheeHighest(Couleur.PIQUE));
							break;
						case 1:
							it.next().setStrategyTrophee(new StrategyTropheeHighest(Couleur.TREFLE));
							break;
						case 2:
							it.next().setStrategyTrophee(new StrategyTropheeHighest(Couleur.CARREAU));
							break;
						case 3:
							it.next().setStrategyTrophee(new StrategyTropheeHighest(Couleur.COEUR));
							break;
					}
					
					break;
				case 4:
					switch(pickCouleur.nextInt(4)) {
						case 0:
							it.next().setStrategyTrophee(new StrategyTropheeLowest(Couleur.PIQUE));
							break;
						case 1:
							it.next().setStrategyTrophee(new StrategyTropheeHighest(Couleur.TREFLE));
							break;
						case 2:
							it.next().setStrategyTrophee(new StrategyTropheeHighest(Couleur.CARREAU));
							break;
						case 3:
							it.next().setStrategyTrophee(new StrategyTropheeHighest(Couleur.COEUR));
							break;
						default:
							break;
					}
					
					break;
				case 5:
					switch(pickValeur.nextInt(4)) {
					case 0:
						it.next().setStrategyTrophee(new StrategyTropheeMajority(Valeur.AS));
						break;
					case 1:
						it.next().setStrategyTrophee(new StrategyTropheeMajority(Valeur.DEUX));
						break;
					case 2:
						it.next().setStrategyTrophee(new StrategyTropheeMajority(Valeur.TROIS));
						break;
					case 3:
						it.next().setStrategyTrophee(new StrategyTropheeMajority(Valeur.QUATRE));
						break;
					default:
						break;
				}
					break;
				default:
					break;
				
			}
		}
	}

}
