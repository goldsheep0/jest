package fr.lo02.controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import fr.lo02.jest.Partie;
import fr.lo02.jest.enums.PartieState;
import fr.lo02.ui.ScoreBoard;

public class ScoreBoardController implements Serializable {
	
	public ScoreBoardController (ScoreBoard scoreboard) {
		
		scoreboard.getBtnSave().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Partie.getPartie().changeState(PartieState.SAVE);
				
			}
		});
		
	}

}
