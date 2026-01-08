package fr.lo02.controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.*;

import fr.lo02.jest.Partie;
import fr.lo02.jest.enums.PartieState;
import fr.lo02.ui.MainWindow;
import fr.lo02.ui.SelectCaracteristicsMenu;

public class CaracteristicsController {
	
	public CaracteristicsController(SelectCaracteristicsMenu menu, Partie partie) {
		
		JScrollPane scrollPane = menu.getScrollPane();
		ButtonGroup radioButtons = menu.getGroup();
		JRadioButton j3 = menu.getJ3();
		JRadioButton j4 = menu.getJ4();
		JButton btn = menu.getBtnSuivant();
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (((JList)scrollPane.getViewport().getView()).getSelectedIndex() >= 0) {
					
					if (radioButtons.getSelection() != null) {
						
						if (j3.isSelected()) {
							partie.setNombreJoueursTotal(3);
						} else if (j4.isSelected()) {
							partie.setNombreJoueursTotal(4);
						}
						
						partie.setVariante(((JList)scrollPane.getViewport().getView()).getSelectedIndex());
						
						partie.changeState(PartieState.C_BOTS);
						
					}
					
				};
				
			}
		});
		
	}

}
