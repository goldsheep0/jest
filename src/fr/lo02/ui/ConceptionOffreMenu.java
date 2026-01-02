package fr.lo02.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.lo02.jest.Carte;
import fr.lo02.jest.Joueur;
import fr.lo02.jest.Partie;
import fr.lo02.jest.bots.StrategyJoueur;
import fr.lo02.jest.bots.StrategyJoueurPhysique;
import fr.lo02.jest.enums.Couleur;
import fr.lo02.jest.enums.Valeur;
import fr.lo02.jest.regle.attributionTrophees.StrategyTropheeHighest;

import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConceptionOffreMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LinkedList<ToggleButtonCarte> userChoice = new LinkedList<ToggleButtonCarte>();
	private Joueur joueur;
	
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
					jTest.getJest().addCarte(new Carte(Valeur.AS, Couleur.CARREAU, new StrategyTropheeHighest(Couleur.PIQUE)));
					
					Partie.getPartie().getTrophees().addCarte(new Carte(Valeur.AS, Couleur.PIQUE, new StrategyTropheeHighest(Couleur.PIQUE)));
					
					
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
		
		//AffichageCarte
		Iterator<Carte> it= joueur.getOffre().getCartes().iterator();
		while(it.hasNext()) {
			this.userChoice.add(new ToggleButtonCarte(it.next()));
			group.add(this.userChoice.getLast());
			panel.add(this.userChoice.getLast());
		}
		
		
		
		JLabel lblTrophes = new JLabel("<html><body><div style=\"text-align:center\">"+joueur.getNom()+"</div>Choissez la carte à mettre face cachée</body></html>");
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
		
		JButton btnValiderChoix = new JButton("Valider choix");
		panel_1.add(btnValiderChoix);
	}
}
