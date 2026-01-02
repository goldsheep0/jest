package fr.lo02.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.lo02.jest.Carte;
import fr.lo02.jest.enums.*;
import fr.lo02.jest.regle.attributionTrophees.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class SeeTropheeDisplay extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LinkedList<Carte> trophees = new LinkedList<Carte>();
					trophees.add(new Carte(Valeur.TROIS, Couleur.PIQUE, new StrategyTropheeHighest(Couleur.PIQUE)));
					trophees.add(new Carte(Valeur.JOKER, Couleur.JOKER, new StrategyTropheeBestJest()));
					SeeTropheeDisplay frame = new SeeTropheeDisplay(trophees);
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
	public SeeTropheeDisplay(LinkedList<Carte> trophees) {
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
		Iterator<Carte> it= trophees.iterator();
		while(it.hasNext()) {
			panel.add(new AffichageCarteVisible(it.next(), true));
		}
		
		
		JLabel lblTrophes = new JLabel("Trophées");
		lblTrophes.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTrophes, BorderLayout.NORTH);

	}

}
