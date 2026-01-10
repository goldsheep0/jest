package fr.lo02.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import fr.lo02.jest.Carte;
import fr.lo02.jest.Joueur;
import fr.lo02.jest.Partie;
import fr.lo02.jest.bots.StrategyJoueur;
import fr.lo02.jest.bots.StrategyJoueurBotBourrin;
import fr.lo02.jest.bots.StrategyJoueurPhysique;
import fr.lo02.jest.enums.Couleur;
import fr.lo02.jest.enums.Valeur;
import fr.lo02.jest.regle.attributionTrophees.StrategyTropheeHighest;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

/**
 * Fenêtre qui permet d'afficher le choix d'un bot parmi les offres à sa disposition.
 */
public class ChoixOffreBotMenu extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnSuivant;
	
	public JButton getBtnSuivant() {return btnSuivant;}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Carte carteFaceVisible1 = new Carte(Valeur.AS, Couleur.COEUR, new StrategyTropheeHighest(Couleur.PIQUE));
					carteFaceVisible1.setFaceVisible(false);
					Carte carteFaceVisible2 = new Carte(Valeur.AS, Couleur.PIQUE, new StrategyTropheeHighest(Couleur.PIQUE));
					carteFaceVisible2.setFaceVisible(false);
					Carte carteFaceVisible3 = new Carte(Valeur.DEUX, Couleur.PIQUE, new StrategyTropheeHighest(Couleur.PIQUE));
					carteFaceVisible3.setFaceVisible(false);
					
					Joueur jTest = new Joueur("Bot Test", new StrategyJoueurBotBourrin());
					
					jTest.getOffre().addCarte(carteFaceVisible1);
					jTest.getOffre().addCarte(new Carte(Valeur.TROIS, Couleur.CARREAU, new StrategyTropheeHighest(Couleur.PIQUE)));
					jTest.getJest().addCarte(new Carte(Valeur.AS, Couleur.CARREAU, new StrategyTropheeHighest(Couleur.PIQUE)));
					
					LinkedList<Joueur> listeJoueursTest = new LinkedList<Joueur>();
					
					listeJoueursTest.add(new Joueur("Joueur Test 2", new StrategyJoueurPhysique()));
					listeJoueursTest.getLast().getOffre().addCarte(carteFaceVisible2);
					listeJoueursTest.getLast().getOffre().addCarte(new Carte(Valeur.JOKER, Couleur.JOKER, new StrategyTropheeHighest(Couleur.PIQUE)));
					
					listeJoueursTest.add(new Joueur("Joueur Test 3", new StrategyJoueurPhysique()));
					listeJoueursTest.getLast().getOffre().addCarte(carteFaceVisible3);
					listeJoueursTest.getLast().getOffre().addCarte(new Carte(Valeur.DEUX, Couleur.CARREAU, new StrategyTropheeHighest(Couleur.PIQUE)));
					
					
					ChoixOffreBotMenu frame = new ChoixOffreBotMenu(jTest,listeJoueursTest,carteFaceVisible2);
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
	public ChoixOffreBotMenu(Joueur joueur, LinkedList<Joueur> listeJoueursPrenables, Carte carteChoisie) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		
		
		//AffichageOffresDesOffres
		
		LinkedList<JPanel> listePanel = new LinkedList<JPanel>();
		LinkedList<JPanel> listeFlowPanel = new LinkedList<JPanel>();
		
		Iterator<Joueur> itJ = listeJoueursPrenables.iterator();
		Joueur currentJoueur;
		Carte currentCarte;
		Iterator<Carte> itC;
		while(itJ.hasNext()) {
			currentJoueur=itJ.next();
			itC= currentJoueur.getOffre().getCartes().iterator();
			
			
			
			listePanel.add(new JPanel());
			panel.add(listePanel.getLast(), BorderLayout.CENTER);
			
			
			listePanel.getLast().setLayout(new BorderLayout());
			listePanel.getLast().add(new JLabel(currentJoueur.getNom()), BorderLayout.NORTH);
			
			listeFlowPanel.add(new JPanel());
			listePanel.getLast().add(listeFlowPanel.getLast(), BorderLayout.CENTER);
			listeFlowPanel.getLast().setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
			
			while(itC.hasNext()) {
				currentCarte=itC.next();
				if(currentCarte.equals(carteChoisie)) {	
					listeFlowPanel.getLast().add(new AffichageCarteVisible(currentCarte, true));
				}else {
					listeFlowPanel.getLast().add(new AffichageCarteVisible(currentCarte, false));
				}
			}
		}
		
		
		
		
		JLabel lblTrophes = new JLabel("Choix du bot : "+joueur.getNom());
		lblTrophes.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTrophes, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnSuivant = new JButton("Suivant");
		panel_1.add(btnSuivant);

	}
	

}
