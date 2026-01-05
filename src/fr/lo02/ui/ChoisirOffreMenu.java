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
import fr.lo02.jest.bots.StrategyJoueurPhysique;
import fr.lo02.jest.enums.Couleur;
import fr.lo02.jest.enums.Valeur;
import fr.lo02.jest.regle.attributionTrophees.StrategyTropheeHighest;

public class ChoisirOffreMenu extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Joueur joueur;
	private LinkedList<ButtonCarte> userChoice = new LinkedList<ButtonCarte>();
	private LinkedList<JPanel> listePanel = new LinkedList<JPanel>();
	
	public LinkedList<ButtonCarte> getUserChoice() {return userChoice;}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					Carte carteFaceVisible1 = new Carte(Valeur.AS, Couleur.COEUR, new StrategyTropheeHighest(Couleur.PIQUE));
					carteFaceVisible1.setFaceVisible(true);
					Carte carteFaceVisible2 = new Carte(Valeur.AS, Couleur.PIQUE, new StrategyTropheeHighest(Couleur.PIQUE));
					carteFaceVisible2.setFaceVisible(true);
					Carte carteFaceVisible3 = new Carte(Valeur.DEUX, Couleur.PIQUE, new StrategyTropheeHighest(Couleur.PIQUE));
					carteFaceVisible3.setFaceVisible(true);
					
					Joueur jTest = new Joueur("Joueur Test", new StrategyJoueurPhysique());
					
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
					
					
					Partie.getPartie().getTrophees().addCarte(new Carte(Valeur.AS, Couleur.PIQUE, new StrategyTropheeHighest(Couleur.PIQUE)));
					
					
					ChoisirOffreMenu frame = new ChoisirOffreMenu(jTest,listeJoueursTest);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Créer la fenêtre pour choisir une carte parmis les offres disponibles. Passe à la suite dès que le joueur clique sur une carte.
	 * @param joueur - Le joueur qui doit choisir une carte
	 * @param listeJoueursPrenables - Les joueurs parmis lesquels le joueur doit choisir une offre, peut contenir un seul joueur ou le joueur lui même
	 */
	public ChoisirOffreMenu(Joueur joueur, LinkedList<Joueur> listeJoueursPrenables) {
		this.joueur=joueur;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		
		ButtonGroup group = new ButtonGroup();
		
		//AffichageOffresDesOffres
		
		LinkedList<JPanel> listeFlowPanel = new LinkedList<JPanel>();
		
		Iterator<Joueur> itJ = listeJoueursPrenables.iterator();
		Joueur currentJoueur;
		Iterator<Carte> itC;
		while(itJ.hasNext()) {
			currentJoueur=itJ.next();
			itC= currentJoueur.getOffre().getCartes().iterator();

			this.listePanel.add(new JPanel());
			panel.add(this.listePanel.getLast(), BorderLayout.CENTER);
			
			
			this.listePanel.getLast().setLayout(new BorderLayout());
			this.listePanel.getLast().add(new JLabel(currentJoueur.getNom()), BorderLayout.NORTH);
			
			listeFlowPanel.add(new JPanel());
			this.listePanel.getLast().add(listeFlowPanel.getLast(), BorderLayout.CENTER);
			listeFlowPanel.getLast().setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
			
			while(itC.hasNext()) {
				this.userChoice.add(new ButtonCarte(currentJoueur,itC.next()));
				group.add(this.userChoice.getLast());
				listeFlowPanel.getLast().add(this.userChoice.getLast());
			}
		}
		
		
		
		
		JLabel lblTrophes = new JLabel("<html><body><div style=\"text-align:center\">"+joueur.getNom()+"</div>Choissez une carte dans les offres disponibles :</body></html>");
		lblTrophes.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTrophes, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("Voir Trophées");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeeTropheeDisplay.afficherTrophee(Partie.getPartie().getTrophees().getCartes());
			}
		});
		panel_1.add(btnNewButton);
		
		JButton btnVoirJest = new JButton("Voir Jest");
		btnVoirJest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeeJestDisplay.afficherJest(joueur.getJest().getCartes());
			}
		});
		panel_1.add(btnVoirJest);
		
		JButton btnVoirOffre = new JButton("Voir Offre");
		btnVoirOffre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeeOfferDisplay.afficherOffre(joueur.getOffre().getCartes());
			}
		});
		panel_1.add(btnVoirOffre);

	}

}
