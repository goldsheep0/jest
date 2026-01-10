package fr.lo02.ui;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import fr.lo02.jest.Carte;
import fr.lo02.jest.Joueur;
import fr.lo02.jest.enums.*;

/**
 * Classe pour afficher une carte dans l'UI.<br>
 * Plusieurs constructeur permettent de l'afficher en temps que :<br>
 * trophée;<br>
 * trophée attribué à un joueur;<br>
 * jest d'un joueur;<br>
 * offre construite d'un joueur;<br>
 * ou carte choisie par un joueur.
 */
public class AffichageCarteVisible extends JLabel{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Référence vers la carte affichée
	 */
	public Carte carte;
	
	/**
	 * Texture à utiliser pour l'affichage de la carte.
	 */
	public ImageIcon texture;
	
	public Carte getCarte() {
		return this.carte;
	}
	
	/**
	 * Contructeur lorsque l'affichage est utilisé pour :<br>
	 * Afficher les trophées de la partie : mettre trophee à true et offer à false,<br>
	 * Afficher le jest d'un joueur : mettre trophee et offer à false,<br>
	 * Afficher l'offre construite d'un joueur : mettre trophee à false et offer à true.
	 * 
	 * @param carte - la carte à afficher.
	 * @param trophee
	 * @param offer
	 */
	public AffichageCarteVisible(Carte carte, boolean trophee, boolean offer) {
		super();
		this.carte=carte;
		
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
		
		if(trophee) {
			super.setText(this.carte.getStrategyTrophee().toString());
			super.setHorizontalTextPosition(JLabel.CENTER);
			super.setVerticalTextPosition(JLabel.BOTTOM);

		}
		
		if(offer) {
			if(this.carte.estFaceVisible()) {
				super.setText("Face visible");
				super.setHorizontalTextPosition(JLabel.CENTER);
				super.setVerticalTextPosition(JLabel.BOTTOM);
			}else {
				super.setText("Face caché");
				super.setHorizontalTextPosition(JLabel.CENTER);
				super.setVerticalTextPosition(JLabel.BOTTOM);
			}

		}
		
		super.setIcon(this.texture);
	}
	
	/**
	 * Contructeur lorsque l'affichage est utilisé pour afficher un trophée attribué à un joueur passé en argument.
	 * 
	 * @param carte - la carte à afficher.
	 * @param joueur - le joueur ayant gagné le trophée.
	 */
	public AffichageCarteVisible(Carte carte, Joueur joueur) {
		super();
		this.carte=carte;
		
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
		
		
		super.setText("<html><body style=\"text-align:center\"><div>"+this.carte.getStrategyTrophee().toString()+"</div><div>Revient à :</div>"+joueur.getNom()+"</body></html>");
		super.setHorizontalTextPosition(JLabel.CENTER);
		super.setVerticalTextPosition(JLabel.BOTTOM);

		
		super.setIcon(this.texture);
	}
	
	/**
	 * Contructeur lorsque l'affichage est utilisé pour afficher les cartes choisies ou non par un bot.
	 * 
	 * @param carte - la carte à afficher.
	 * @param choisie - true si la carte est la carte sélectionnée par le bot, false sinon.
	 */
	public AffichageCarteVisible(Carte carte, boolean choisie) {
		super();
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
		
		
		if(choisie) {
			super.setText("Carte choisie par le bot");
			super.setHorizontalTextPosition(JLabel.CENTER);
			super.setVerticalTextPosition(JLabel.BOTTOM);
		}
		

		
		super.setIcon(this.texture);
	}
}
