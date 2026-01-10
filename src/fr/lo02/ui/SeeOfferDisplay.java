package fr.lo02.ui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.lo02.jest.Carte;
import fr.lo02.jest.enums.Couleur;
import fr.lo02.jest.enums.Valeur;
import fr.lo02.jest.regle.attributionTrophees.StrategyTropheeBestJest;
import fr.lo02.jest.regle.attributionTrophees.StrategyTropheeHighest;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Fenêtre où l'utilisateur peut voir l'offre qu'il a conçu.
 */
public class SeeOfferDisplay extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public static void afficherOffre(LinkedList<Carte> offre) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					SeeOfferDisplay popupOfferDisplay = new SeeOfferDisplay(offre);
					popupOfferDisplay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					//To enable a window.
					popupOfferDisplay.setVisible(true);
					popupOfferDisplay.setEnabled(true);
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LinkedList<Carte> offer = new LinkedList<Carte>();
					offer.add(new Carte(Valeur.TROIS, Couleur.PIQUE, new StrategyTropheeHighest(Couleur.PIQUE)));
					offer.getLast().setFaceVisible(true);
					
					offer.add(new Carte(Valeur.JOKER, Couleur.JOKER, new StrategyTropheeBestJest()));
					SeeOfferDisplay frame = new SeeOfferDisplay(offer);
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
	public SeeOfferDisplay(LinkedList<Carte> offer) {
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
		Iterator<Carte> it= offer.iterator();
		while(it.hasNext()) {
			panel.add(new AffichageCarteVisible(it.next(), false, true));
		}
		
		
		JLabel lblTrophes = new JLabel("Votre Offre");
		lblTrophes.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTrophes, BorderLayout.NORTH);

	}

}
