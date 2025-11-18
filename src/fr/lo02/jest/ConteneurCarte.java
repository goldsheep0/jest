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
	
	public boolean isEmpty() {
		return cartes.isEmpty();
	}
	
	public String toString() {
		return cartes.toString();
	}
	
	public LinkedList<Carte> getCartes() {
		return cartes;
	}
	
	public void addCarte(Carte c) {
		cartes.add(c);
	}

}
