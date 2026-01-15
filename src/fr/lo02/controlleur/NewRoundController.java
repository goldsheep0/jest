package fr.lo02.controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.lo02.jest.Partie;
import fr.lo02.jest.enums.PartieState;
import fr.lo02.ui.NouveauRoundMenu;

/**
 * Récupère l'input utilisateur pour soit :<br>
 * - Changer l'état de la partie à celui de sauvegarde.<br>
 * - Changer l'état de la partie à celui de conception des offres.
 */
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
