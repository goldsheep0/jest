package fr.lo02.jest;

import java.util.*;

public class ConteneurCarte {
	
	private LinkedList<Carte> cartes;
	
	public ConteneurCarte() {}
	
	public Carte distribuerCarte() {
		Carte c;
		c = cartes.removeLast();
		return c;
	}
	
	public void melanger() {
		Collections.shuffle(cartes);
	}
	
	public int getQuantite() {
		return cartes.size();
	}
	
	public String toString() {
		return cartes.toString();
	}
	
	public LinkedList<Carte> getCartes() {
		return cartes;
	}

}
