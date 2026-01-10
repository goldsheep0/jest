package fr.lo02.ui;

import java.awt.EventQueue;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JButton;

/**
 * Fenêtre où l'utilisateur choisi le nombre et le type de bot qu'il souhaite inclure dans la partie.
 */
public class SelectBotMenu extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int nbJoueurs;
	private JSpinner spinner;
	private JSpinner spinner_1;
	private JButton btnValider;
	
	public JSpinner getBourrins() {return spinner;}
	public JSpinner getTrophees() {return spinner_1;}
	public JButton getBouton() {return btnValider;}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectBotMenu frame = new SelectBotMenu(3);
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
	public SelectBotMenu(int nbJoueurs) {
		
		this.nbJoueurs=nbJoueurs;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblChoisirLesBots = new JLabel("Choisir les bots");
		lblChoisirLesBots.setHorizontalAlignment(SwingConstants.CENTER);
		
		GroupSpinner group = new GroupSpinner(this.nbJoueurs);
		spinner = new JSpinner( group.createGroupModel(0, 0, this.nbJoueurs, 1) );		
		spinner_1 = new JSpinner( group.createGroupModel(0, 0, this.nbJoueurs, 1) );
	
		JLabel lblBotsBourrins = new JLabel("Bots bourrins");
		lblBotsBourrins.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel = new JLabel("Bots trophées");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnValider = new JButton("Suivant");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(80)
					.addComponent(spinner, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
					.addGap(210)
					.addComponent(spinner_1, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
					.addGap(80))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(50)
					.addComponent(lblBotsBourrins, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(150)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(50))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(163)
					.addComponent(lblChoisirLesBots, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
					.addGap(153))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(175)
					.addComponent(btnValider, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
					.addGap(175))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(50)
					.addComponent(lblChoisirLesBots, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
					.addGap(50)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBotsBourrins, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addComponent(btnValider, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(20))
		);
		contentPane.setLayout(gl_contentPane);

	}
}
