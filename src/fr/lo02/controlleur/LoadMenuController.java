package fr.lo02.controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.lo02.jest.Partie;
import fr.lo02.jest.enums.PartieState;
import fr.lo02.ui.LoadSaveMenu;

/**
 * Récupère l'input utilisateur et charge le fichier choisi.<br>
 * Si aucun fichier n'est présent dans la liste, change la partie à l'état de choix des caractéristiques.
 */
public class LoadMenuController {
	
	public LoadMenuController (LoadSaveMenu menu) {
		
		menu.getBtnCharger().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (menu.getListe() == null) {
					Partie.getPartie().changeState(PartieState.C_VARIANTE);
				} else {
					String nom_fichier = menu.getListe().getSelectedValue();
					Partie.getPartie().chargerFichier(nom_fichier);
				}
				
			}
		});
		
	}

}
