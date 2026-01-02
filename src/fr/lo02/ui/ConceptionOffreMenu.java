package fr.lo02.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.lo02.jest.Carte;
import fr.lo02.jest.Joueur;
import fr.lo02.jest.bots.StrategyJoueur;
import fr.lo02.jest.bots.StrategyJoueurPhysique;
import fr.lo02.jest.enums.Couleur;
import fr.lo02.jest.enums.Valeur;
import fr.lo02.jest.regle.attributionTrophees.StrategyTropheeHighest;

import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

public class ConceptionOffreMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//private LinkedList<> userChoice = new LinkedList<>;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Joueur jTest = new Joueur("Joueur Test", new StrategyJoueurPhysique());
					
					jTest.getOffre().addCarte(new Carte(Valeur.TROIS, Couleur.PIQUE, new StrategyTropheeHighest(Couleur.PIQUE)));
					jTest.getOffre().addCarte(new Carte(Valeur.TROIS, Couleur.CARREAU, new StrategyTropheeHighest(Couleur.PIQUE)));
					
					
					ConceptionOffreMenu frame = new ConceptionOffreMenu(jTest);
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
	public ConceptionOffreMenu(Joueur joueur) {
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
		Iterator<Carte> it= joueur.getOffre().getCartes().iterator();
		while(it.hasNext()) {
			panel.add(new AffichageCarteVisible(it.next(), false, false));
		}
		
		
		JLabel lblTrophes = new JLabel("Choissez la carte à mettre face visible");
		lblTrophes.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTrophes, BorderLayout.NORTH);
	}

}
