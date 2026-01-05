package fr.lo02.controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JTextField;

import fr.lo02.jest.Partie;
import fr.lo02.jest.enums.PartieState;
import fr.lo02.ui.EnterPlayerNamesMenu;

public class SelectPlayerController {
	
	public SelectPlayerController(EnterPlayerNamesMenu menu, Partie partie) {
		
		LinkedList<JTextField> listeJoueurs = menu.getListe();
		
		menu.getBtn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int joueursPhysiques = partie.getNombreJoueursTotal() - partie.getJoueurs().size();
				
				for (int i = 0; i < joueursPhysiques; i++) {
					
					partie.addJoueur(listeJoueurs.get(i).getText());
					
				}
				
				partie.nouveauRound();
				
			}
			
		});
		
	}

}
