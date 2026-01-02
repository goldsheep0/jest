package fr.lo02.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import fr.lo02.jest.Carte;
import fr.lo02.jest.enums.Couleur;
import fr.lo02.jest.enums.Valeur;
import fr.lo02.jest.regle.attributionTrophees.StrategyTropheeBestJest;
import fr.lo02.jest.regle.attributionTrophees.StrategyTropheeHighest;

public class SeeJestDisplay extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LinkedList<Carte> jest = new LinkedList<Carte>();
					jest.add(new Carte(Valeur.TROIS, Couleur.PIQUE, new StrategyTropheeHighest(Couleur.PIQUE)));
					jest.add(new Carte(Valeur.JOKER, Couleur.JOKER, new StrategyTropheeBestJest()));
					SeeJestDisplay frame = new SeeJestDisplay(jest);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SeeJestDisplay(LinkedList<Carte> jest) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		
		
		//AffichageCarte
		Iterator<Carte> it= jest.iterator();
		while(it.hasNext()) {
			panel.add(new AffichageCarteVisible(it.next(), false, false));
		}
		
		
		JLabel lblTrophes = new JLabel("Votre Jest");
		lblTrophes.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTrophes, BorderLayout.NORTH);

	}

}
