package fr.lo02.controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import fr.lo02.jest.Carte;
import fr.lo02.jest.Partie;
import fr.lo02.ui.ChoixOffreBotMenu;

public class ChoisirOffreBotController{
	
	public ChoisirOffreBotController(ChoixOffreBotMenu menu, Partie partie) {
	
		menu.getBtnSuivant().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				partie.getRound().prendreCarte(partie.getRound().getCarteChoisieBot());
				partie.getRound().prendreCarteSuivante();
			}
			
		});
		
	}

}
