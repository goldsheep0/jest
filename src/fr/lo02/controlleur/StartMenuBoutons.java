package fr.lo02.controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import fr.lo02.jest.Partie;
import fr.lo02.ui.MainWindow;
import fr.lo02.ui.StartMenu;

public class StartMenuBoutons {
	
	public StartMenuBoutons(StartMenu startMenu, JFrame loadSaveMenu, JFrame caracteristicsMenu, Partie partie, MainWindow main) {
		
		JButton btnNouvellePartie = startMenu.getNouvellePartie();
		JButton btnChargerPartie = startMenu.getChargerPartie();
		
		btnNouvellePartie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//partie.lancerPartie();
				main.next(2);
			}
		});
		
		btnChargerPartie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//partie.chargerPartie();
				main.next();
			}
		});
		
	}

}
