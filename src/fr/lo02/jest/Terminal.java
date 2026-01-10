package fr.lo02.jest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Serializable;

/**
 * Gère les entrées et sorties en vue texte.<br>
 * Supporte la sérialisation.
 */
public class Terminal implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Entrée d'information, utilisée pour lire les entrées clavier dans le terminal.
	 */
	private InputStream entree;
	
	/**
	 * Sortie d'information, utilisée pour renvoyer des informations dans le terminal.
	 */
	private PrintStream sortie;
	
	public Terminal(){
		entree = System.in;
		sortie = System.out;		
	}
	
	/**
	 * Permet de lire un nombre entier dans le terminal.<br>
	 * Ne s'arrête pas tant qu'un entier n'a pas été entré par l'utilisateur.
	 * @return - l'entier lu.
	 */
	public int lireEntier(){
		int value = 0;
		boolean valide = false;
		while(!valide) {
			try {
				value = Integer.parseInt(lireChaine());
				valide = true;
			} catch (NumberFormatException e) {
				sortie.println("Mauvaise saisie. Veuillez écrire un entier :");
			}
		}
		return value;		
	}
	
	/**
	 * Permet de lire une entrée utilisateur sous forme de chaîne de caractères.
	 * @return - la chaîne de caractères lue.
	 */
	public String lireChaine(){
		String laChaine = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(entree));
		try {
			laChaine = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return laChaine;
	}
	
	/**
	 * Permet d'écrire dans la console une chaîne de caractères passée en argument. 
	 * @param laChaine
	 */
	public void afficherChaine(String laChaine){
		sortie.println(laChaine);
	}
	
	/**
	 * Permet d'afficher une séparation dans le terminal.
	 */
	public void afficherDivision() {
		sortie.println("\n-------------------------------------------------\n");
	}
	
}