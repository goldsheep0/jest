package fr.lo02.controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.lo02.jest.Partie;
import fr.lo02.jest.enums.PartieState;
import fr.lo02.ui.NouvelleSauvegardeMenu;

/**
 * Crée une nouvelle sauvegarde à partir du nom de fichier entré par l'utilisateur.
 */
public class NewSaveController {
	
	public NewSaveController (NouvelleSauvegardeMenu menu) {
		
		menu.getBtnSauvegarder().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String nomFichier = menu.getTextField().getText();
				
				if (nomFichier != "") {
					Partie.getPartie().sauvegarderPartie(Partie.getPartie(), nomFichier);
					Partie.getPartie().changeState(PartieState.NEW_ROUND);
				}
				
			}
		});
		
	}

}
