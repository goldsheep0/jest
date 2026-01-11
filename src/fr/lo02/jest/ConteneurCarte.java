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

/**
 * Un conteneur de carte est toute liste de cartes avec les méthodes associées pour les manipuler.<br>
 * Supporte la sérialisation.
 */
public class ConteneurCarte implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Liste des cartes du conteneur.
	 */
	private LinkedList<Carte> cartes;
	
	public ConteneurCarte() {
		cartes = new LinkedList<Carte>();
	}
	
	/**
	 * Permet de retirer la dernière carte du conteneur et de la retourner.
	 * @return - la dernière carte du conteneur.
	 */
	public Carte distribuerCarte() {
		Carte c;
		c = cartes.removeLast();
		return c;
	}
	
	/**
	 * Permet de retirer une carte précise du conteneur et de la retourner.
	 * 
	 * @param c - la carte à retirer.
	 * @return - la carte retirée.
	 */
	public Carte distribuerCarte(Carte c) {
		cartes.remove(c);
		return c;
	}
	
	/**
	 * Permet de mélanger la liste de carte.
	 */
	public void melanger() {
		Collections.shuffle(cartes);
	}
	
	public boolean isEmpty() {
		return cartes.isEmpty();
	}
	
	/**
	 * Renvoie le conteneur de carte sous forme d'une chaîne de caractères affichable.
	 */
	public String toString() {
			
		return this.cartes.toString();
	}
	
	public LinkedList<Carte> getCartes() {
		return cartes;
	}
	
	/**
	 * Permet de rajouter une carte dans le conteneur.
	 * @param c - la carte à rajouter dans le paquet.
	 */
	public void addCarte(Carte c) {
		cartes.add(c);
	}
	
	/**
	 * Permet de retirer la première carte, dont la face est comme celle passée en argument, présente dans le conteneur.<br>
	 * Utile lorsque le conteneur est utilisée comme offre.
	 * 
	 * @param faceVisible - true pour la première carte face visible, false pour la première carte face cachée
	 * @return - null si aucune carte face visible est présente. La carte face visible sinon.
	 */
	public Carte retirerCarteFaceVisible(boolean faceVisible) {
		for (int carteIndex = 0; carteIndex < cartes.size(); carteIndex++) {
			if (cartes.get(carteIndex).estFaceVisible() == faceVisible) {
				return cartes.remove(carteIndex);
			}
		}
		return null;
	}
	
	/**
	 * Permet de renvoyer la première carte face visible présente dans le conteneur.<br>
	 * Utile lorsque le conteneur est utilisée comme offre.
	 * 
	 * @return - null si aucune carte face visible est présente. La carte face visible sinon.
	 */
	public Carte getCarteVisible() {
		for (int carteIndex = 0; carteIndex < cartes.size(); carteIndex++) {
			if (cartes.get(carteIndex).estFaceVisible()) {
				return cartes.get(carteIndex);
			}
		}
		return null;
	}
	
	/**
	 * Renvoie le conteneur de carte sous forme d'une chaîne de caractères affichable.<br>
	 * Utilisée lorsque le conteneur est utilisé comme liste de trophées.<br>
     * Rajoute une ligne pour afficher le trophée associé à chaque carte.
	 */
	public String toStringTrophee() {
		
		StringBuffer str = new StringBuffer();
		
		Iterator<Carte> it = this.cartes.iterator();
		
		
		while(it.hasNext()) {
			str.append(it.next().toStringTrophee());
			str.append("\n");
		}
	
		return str.toString();
		
		
	}
	
	/**
	 * Renvoie la première carte présente dans le conteneur de la couleur passée en paramètre.
	 * 
	 * @param couleur - couleur à rechercher dans le conteneur.
	 * @return - null si aucune carte de la couleur est présente. La carte sinon.
	 */
	public Carte hasCarte (Couleur couleur) {
		for (Iterator<Carte> it = cartes.iterator(); it.hasNext(); ) {
			Carte c = it.next();
			if (c.getCouleur() == couleur) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Renvoie la première carte présente dans le conteneur de la valeur passée en paramètre.
	 * 
	 * @param valeur - valeur à rechercher dans le conteneur.
	 * @return - null si aucune carte de la valeur est présente. La carte sinon.
	 */
	public Carte hasCarte (Valeur valeur) {
		for (Iterator<Carte> it = cartes.iterator(); it.hasNext(); ) {
			Carte c = it.next();
			if (c.getValeur() == valeur) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Renvoie la première carte présente dans le conteneur avec la stratégie passée en paramètre.
	 * 
	 * @param strat - stratégie à rechercher dans le conteneur.
	 * @return - null si aucune carte avec la stratégie est présente. La carte sinon.
	 */
	public Carte hasCarte (Class strat) {
		for (Iterator<Carte> it = cartes.iterator(); it.hasNext(); ) {
			Carte c = it.next();
			if (c.getStrategyTrophee().getClass().equals(strat)) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Permet d'attribuer une stratégie trophée aléatoire à chaque carte du conteneur.
	 */
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
