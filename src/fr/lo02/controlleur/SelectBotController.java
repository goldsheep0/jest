package fr.lo02.controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.*;

import fr.lo02.ui.MainWindow;
import fr.lo02.ui.SelectBotMenu;
import fr.lo02.jest.Partie;
import fr.lo02.jest.enums.PartieState;

public class SelectBotController implements Serializable {
	
	public SelectBotController(SelectBotMenu botMenu, Partie partie) {
		
		botMenu.getBouton().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				int botBourrins = (Integer)botMenu.getBourrins().getValue();
				int botTrophees = (Integer)botMenu.getTrophees().getValue();
				
				int nombreJoueurs = partie.getNombreJoueursTotal() - botBourrins - botTrophees;
				partie.addBots(botBourrins, 0);
				partie.addBots(botTrophees, 1);
				
				if (nombreJoueurs == 0) {
					partie.nouveauRound();
				} else {
					partie.changeState(PartieState.C_PLAYERS);
				}
				
			}
		});
		
	}

}
