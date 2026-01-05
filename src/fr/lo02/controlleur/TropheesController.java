package fr.lo02.controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.lo02.jest.Partie;
import fr.lo02.jest.enums.PartieState;
import fr.lo02.ui.AttributionTropheesDisplay;

public class TropheesController {
	
	public TropheesController (AttributionTropheesDisplay menu) {
		
		menu.getBtnScores().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Partie.getPartie().changeState(PartieState.SCOREBOARD);
				
			}
		});
		
	}

}
