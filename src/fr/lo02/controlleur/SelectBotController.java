package fr.lo02.controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import fr.lo02.ui.SelectBotMenu;
import fr.lo02.jest.Partie;
import fr.lo02.jest.enums.PartieState;

/**
 * Crée des bots en fonction du nombre et du type choisi par l'utilisateur.
 */
public class SelectBotController {
	
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
