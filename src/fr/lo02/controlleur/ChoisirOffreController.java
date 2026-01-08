package fr.lo02.controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.LinkedList;

import fr.lo02.jest.Partie;
import fr.lo02.ui.ButtonCarte;
import fr.lo02.ui.ChoisirOffreMenu;

public class ChoisirOffreController {
	
	public ChoisirOffreController(ChoisirOffreMenu menu, Partie partie) {
		
		LinkedList<ButtonCarte> userChoice = menu.getUserChoice();
		
		userChoice.forEach((buttonCarte) -> {
			buttonCarte.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					partie.getRound().prendreCarte(buttonCarte.getCarte());
					partie.getRound().prendreCarteSuivante();
					
				}
				
			});
		});
		
	}

}
