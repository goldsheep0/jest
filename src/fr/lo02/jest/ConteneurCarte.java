package fr.lo02.jest;

import java.util.*;

import fr.lo02.jest.enums.*;
import fr.lo02.jest.regle.attributionTrophees.StrategyTrophee;

public class ConteneurCarte {
	
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
		//TODO
	}

}
