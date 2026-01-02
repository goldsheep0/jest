package fr.lo02.ui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import fr.lo02.jest.Carte;
import fr.lo02.jest.Joueur;
import fr.lo02.jest.enums.*;

public class AffichageCarteVisible extends JLabel{
	public Carte carte;
	public ImageIcon texture;
	
	public Carte getCarte() {
		return this.carte;
	}
	
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
	
	public AffichageCarteVisible(Carte carte, boolean choisie) {
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
		
		if(choisie) {
			super.setText("Carte choisie par le bot");
			super.setHorizontalTextPosition(JLabel.CENTER);
			super.setVerticalTextPosition(JLabel.BOTTOM);
		}
		

		
		super.setIcon(this.texture);
	}
}
