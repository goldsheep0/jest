package fr.lo02.controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;

import fr.lo02.jest.*;
import fr.lo02.jest.enums.PartieState;
import fr.lo02.ui.ConceptionOffreMenu;
import fr.lo02.ui.ToggleButtonCarte;

public class ConceptionOffreController {
	
	public ConceptionOffreController(ConceptionOffreMenu menu, Partie partie) {
		
		menu.getBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Joueur joueur = partie.getJoueurFocus();
				Carte carteRetournee = null;
				LinkedList<ToggleButtonCarte> userChoice = menu.getUserChoice();
				for (Iterator<ToggleButtonCarte> it = userChoice.iterator(); it.hasNext(); ) {
					ToggleButtonCarte tbc = it.next();
					if (!tbc.isSelected()) {
						if (carteRetournee != null) {return;}
						carteRetournee = tbc.getCarte();
					}
				}
				carteRetournee.setFaceVisible(true);
				partie.getRound().faireOffres();
			}
		});
		
	}

}
