package fr.lo02.controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.lo02.jest.Partie;
import fr.lo02.jest.enums.PartieState;
import fr.lo02.ui.NouveauRoundMenu;

public class NewRoundController {
	
	public NewRoundController (NouveauRoundMenu menu, Partie partie) {
		
		menu.getBtnSauvegarder().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				partie.changeState(PartieState.SAVE);
			}
		});
		
		menu.getBtnSuivant().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				partie.getRound().faireOffres();
			}
		});
		
	}

}
