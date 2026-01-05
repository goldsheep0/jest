package fr.lo02.ui;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.Random;

import javax.swing.*;

import fr.lo02.jest.Carte;
import fr.lo02.jest.Joueur;
import fr.lo02.jest.enums.*;

public class ButtonCarte extends JButton implements Serializable{
	public Joueur joueur;
	public Carte carte;
	public ImageIcon texture;
	
	public Carte getCarte() {
		return this.carte;
	}
	
	public Joueur getJoueur() {
		return this.joueur;
	}
	
	public ButtonCarte(Joueur joueur, Carte carte) {
		super();
		this.joueur=joueur;
		this.carte=carte;
		
		Random random = new Random();
		
		if(carte.estFaceVisible()) {
			switch(carte.getCouleur()) {
			case Couleur.PIQUE :
				switch(carte.getValeur()) {
				case Valeur.AS:
					this.texture=new ImageIcon("./cardSprites/piqueAs.png");
					break;
				case Valeur.DEUX:
					this.texture=new ImageIcon("./cardSprites/piqueDeux.png");
					break;
				case Valeur.TROIS:
					this.texture=new ImageIcon("./cardSprites/piqueTrois.png");
					break;
				case Valeur.QUATRE:
					this.texture=new ImageIcon("./cardSprites/piqueQuatre.png");
					break;
				default :
					break;
				}
				break;
			case Couleur.TREFLE :
				switch(carte.getValeur()) {
				case Valeur.AS:
					this.texture=new ImageIcon("./cardSprites/trefleAs.png");
					break;
				case Valeur.DEUX:
					this.texture=new ImageIcon("./cardSprites/trefleDeux.png");
					break;
				case Valeur.TROIS:
					this.texture=new ImageIcon("./cardSprites/trefleTrois.png");
					break;
				case Valeur.QUATRE:
					this.texture=new ImageIcon("./cardSprites/trefleQuatre.png");
					break;
				default :
					break;
				}
				break;
			case Couleur.CARREAU :
				switch(carte.getValeur()) {
				case Valeur.AS:
					this.texture=new ImageIcon("./cardSprites/carreauAs.png");
					break;
				case Valeur.DEUX:
					this.texture=new ImageIcon("./cardSprites/carreauDeux.png");
					break;
				case Valeur.TROIS:
					this.texture=new ImageIcon("./cardSprites/carreauTrois.png");
					break;
				case Valeur.QUATRE:
					this.texture=new ImageIcon("./cardSprites/carreauQuatre.png");
					break;
				default :
					break;
				}
				break;
			case Couleur.COEUR :
				switch(carte.getValeur()) {
				case Valeur.AS:
					this.texture=new ImageIcon("./cardSprites/coeurAs.png");
					break;
				case Valeur.DEUX:
					this.texture=new ImageIcon("./cardSprites/coeurDeux.png");
					break;
				case Valeur.TROIS:
					this.texture=new ImageIcon("./cardSprites/coeurTrois.png");
					break;
				case Valeur.QUATRE:
					this.texture=new ImageIcon("./cardSprites/coeurQuatre.png");
					break;
				default :
					break;
				}
				break;
			case Couleur.JOKER :
				this.texture=new ImageIcon("./cardSprites/joker.png");
				
				break;
			}
		}else {
			if(random.nextBoolean()) {
				this.texture=new ImageIcon("./cardSprites/cardBackRed.png");
			}else {
				this.texture=new ImageIcon("./cardSprites/cardBackBlue.png");
			}
			
		}
		
		
		
		super.setIcon(this.texture);
		super.setSelectedIcon(new ImageIcon("./cardSprites/joker.png"));
		super.setBorderPainted(false);
		super.setPreferredSize(new Dimension(90, 126));
		
	}
}
