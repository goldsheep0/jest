package fr.lo02.controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import fr.lo02.ui.MainWindow;
import fr.lo02.ui.SelectBotMenu;
import fr.lo02.jest.Partie;

public class SelectBotController {
	
	public SelectBotController(SelectBotMenu botMenu, JFrame playersNameMenu, Partie partie, MainWindow main) {
		
		int botBourrins = (Integer)botMenu.getBourrins().getValue();
		int botTrophees = (Integer)botMenu.getTrophees().getValue();
		
		botMenu.getBouton().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int nombreJoueurs = partie.getNombreJoueursTotal() - botBourrins - botTrophees;
				partie.addJoueurs(nombreJoueurs);
				partie.addBots(botBourrins, 0);
				partie.addBots(botTrophees, 1);
				
				botMenu.setVisible(false);
				botMenu.setEnabled(false);
				main.next();
			}
		});
		
	}

}
