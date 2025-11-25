package fr.lo02.jest;

import java.util.*;

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
	
	public void attribuerTropheesAleatoire() {
		//TODO
	}

}
