package fr.lo02.jest;

import java.util.*;

import fr.lo02.jest.enums.*;

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
	
	public boolean hasCarte (Couleur couleur) {
		for (Iterator<Carte> it = cartes.iterator(); it.hasNext(); ) {
			if (it.next().getCouleur() == couleur) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasCarte (Valeur valeur) {
		for (Iterator<Carte> it = cartes.iterator(); it.hasNext(); ) {
			if (it.next().getValeur() == valeur) {
				return true;
			}
		}
		return false;
	}
	
	public void attribuerTropheesAleatoire() {
		//TODO
	}

}
