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
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.Serializable;

public class ScoreBoard extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnSauvegarderLaPartie;
	
	public JButton getBtnSave() {return btnSauvegarderLaPartie;}

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
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblScores = new JLabel("Scores");
		lblScores.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblScores, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(130)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addGap(130))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(84)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
					.addGap(50))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnSauvegarderLaPartie = new JButton("Sauvegarder la partie");
		panel_2.add(btnSauvegarderLaPartie);
		
		Iterator<Joueur> it = sortScores.keySet().iterator();
		Joueur currentJoueur;
		while(it.hasNext()) {
			currentJoueur=it.next();
			panel_1.add(new JLabel(currentJoueur.getNom()+" : "+sortScores.get(currentJoueur)+" pts"));
		}
	}

}
