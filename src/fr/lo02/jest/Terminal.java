package fr.lo02.jest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Serializable;

public class Terminal implements Serializable{
	
	private InputStream entree;
	private PrintStream sortie;
	
	public Terminal(){
		entree = System.in;
		sortie = System.out;		
	}
	
	public int lireEntier(){
		/*
		DataInputStream dos = new DataInputStream(entree);
		int value=0;
		try {
			value =  dos.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
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
	
	public void afficherChaine(String laChaine){
		sortie.println(laChaine);
	}
	
	public void afficherDivision() {
		sortie.println("\n-------------------------------------------------\n");
	}
	
	public void echo(){
		String saisieClavier = new String();
		while(saisieClavier.compareTo("exit") != 0){
			saisieClavier=lireChaine();
			afficherChaine(saisieClavier);			
		}		
	}
	
}