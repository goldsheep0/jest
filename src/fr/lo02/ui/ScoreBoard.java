package fr.lo02.ui;

import java.awt.EventQueue;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import fr.lo02.jest.Joueur;
import fr.lo02.jest.bots.StrategyJoueurPhysique;

public class ScoreBoard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					LinkedHashMap<Joueur, Integer> sortScores = new LinkedHashMap<Joueur, Integer>();
					sortScores.put(new Joueur("Joueur test1 ", new StrategyJoueurPhysique()), 20);
					sortScores.put(new Joueur("Joueur test2", new StrategyJoueurPhysique()), 15);
					sortScores.put(new Joueur("Joueur test3", new StrategyJoueurPhysique()), -5);
					
					ScoreBoard frame = new ScoreBoard(sortScores);
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
	public ScoreBoard(LinkedHashMap<Joueur, Integer> sortScores) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblEntrerLeNom = new JLabel("Scores");
		lblEntrerLeNom.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(125, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(125, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(150)
					.addComponent(lblEntrerLeNom, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
					.addGap(150))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(50)
					.addComponent(lblEntrerLeNom, GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
					.addGap(30)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
					.addGap(65))
		);
		
		Iterator<Joueur> it = sortScores.keySet().iterator();
		Joueur currentJoueur;
		while(it.hasNext()) {
			currentJoueur=it.next();
			panel.add(new JLabel(currentJoueur.getNom()+" : "+sortScores.get(currentJoueur)+" pts"));
		}
		contentPane.setLayout(gl_contentPane);
	}

}
