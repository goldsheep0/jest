package fr.lo02.controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

import fr.lo02.jest.Partie;
import fr.lo02.jest.enums.PartieState;
import fr.lo02.ui.SauvegarderPartieMenu;

public class SaveController {
	
	public SaveController (SauvegarderPartieMenu menu) {
		
		menu.getBtnNouvelleSauvegarde().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Partie.getPartie().changeState(PartieState.NEW_SAVE);
				
			}
		});
		
		menu.getBtnEcraserSauvegarde().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JList<String> liste = menu.getListe();
				
				if (liste != null && liste.getSelectedValue() != null) {
					Partie.getPartie().sauvegarderPartie(Partie.getPartie(), liste.getSelectedValue());
					Partie.getPartie().changeState(PartieState.NEW_ROUND);
				} else {
					return;
				}
				
			}
		});
		
	}

}
