package fr.lo02.controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import fr.lo02.jest.Partie;
import fr.lo02.jest.enums.PartieState;
import fr.lo02.ui.StartMenu;

/**
 * Change l'état de la partie en fonction de si l'utilisateur choisir de charger une partie ou d'en commencer une nouvelle.
 */
public class StartMenuBoutons {
	
	public StartMenuBoutons(StartMenu startMenu, Partie partie) {
		
		JButton btnNouvellePartie = startMenu.getNouvellePartie();
		JButton btnChargerPartie = startMenu.getChargerPartie();
		
		btnNouvellePartie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//partie.lancerPartie();
				partie.changeState(PartieState.C_VARIANTE);
			}
		});
		
		btnChargerPartie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//partie.chargerPartie();
				partie.changeState(PartieState.LOAD);
			}
		});
		
	}

}
