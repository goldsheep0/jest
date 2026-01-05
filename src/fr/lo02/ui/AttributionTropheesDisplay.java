package fr.lo02.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import fr.lo02.jest.Carte;
import fr.lo02.jest.Joueur;
import fr.lo02.jest.bots.StrategyJoueurPhysique;
import fr.lo02.jest.enums.Couleur;
import fr.lo02.jest.enums.Valeur;
import fr.lo02.jest.regle.attributionTrophees.StrategyTropheeBestJest;
import fr.lo02.jest.regle.attributionTrophees.StrategyTropheeHighest;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

public class AttributionTropheesDisplay extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnVoirScores;
	
	public JButton getBtnScores() {return btnVoirScores;}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					HashMap<Carte, Joueur> tropheeAvecJoueur = new HashMap<Carte, Joueur>();
					tropheeAvecJoueur.put(new Carte(Valeur.TROIS, Couleur.PIQUE, new StrategyTropheeHighest(Couleur.PIQUE)), new Joueur("Joueur Test1",new StrategyJoueurPhysique()));
					tropheeAvecJoueur.put(new Carte(Valeur.JOKER, Couleur.JOKER, new StrategyTropheeBestJest()), new Joueur("Joueur Test2",new StrategyJoueurPhysique()));
					
					AttributionTropheesDisplay frame = new AttributionTropheesDisplay(tropheeAvecJoueur);
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
	public AttributionTropheesDisplay(HashMap<Carte, Joueur> tropheeAvecJoueur) {
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
		Iterator<Carte> it= tropheeAvecJoueur.keySet().iterator();
		Carte currentCarte;
		while(it.hasNext()) {
			currentCarte=it.next();
			panel.add(new AffichageCarteVisible(currentCarte, tropheeAvecJoueur.get(currentCarte)));
		}
		
		
		JLabel lblTrophes = new JLabel("Attribution des trophées");
		lblTrophes.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTrophes, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnVoirScores = new JButton("Voir scores");
		panel_1.add(btnVoirScores);

	}

}
