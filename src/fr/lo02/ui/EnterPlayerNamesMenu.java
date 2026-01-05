package fr.lo02.ui;

import java.awt.EventQueue;
import java.io.Serializable;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;

public class EnterPlayerNamesMenu extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int nbJoueurPhysiques;
	private LinkedList<JTextField> listeEntrees = new LinkedList<JTextField>();
	private JButton btnCrerPartie;
	
	public JButton getBtn() {return btnCrerPartie;}
	public LinkedList<JTextField> getListe() {return listeEntrees;}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnterPlayerNamesMenu frame = new EnterPlayerNamesMenu(3);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * Puts the player name entries in the attribute listeEntrees.
	 */
	public EnterPlayerNamesMenu(int nbJoueurPhysique) {
		this.nbJoueurPhysiques=nbJoueurPhysique;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblEntrerLeNom = new JLabel("Entrer le nom des joueurs");
		lblEntrerLeNom.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel = new JPanel();
		
		
		
		btnCrerPartie = new JButton("Créer partie");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(134)
					.addComponent(lblEntrerLeNom, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(123))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(165)
					.addComponent(btnCrerPartie, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(155))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(150, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(150, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(50)
					.addComponent(lblEntrerLeNom, GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
					.addGap(30)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
					.addGap(20)
					.addComponent(btnCrerPartie, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(20))
		);
		
		
		
		for(int i=0; i<this.nbJoueurPhysiques;i++) {
			this.listeEntrees.add(new JTextField());
			this.listeEntrees.getLast().setText("Joueur "+(i+1));
			panel.add(this.listeEntrees.getLast());
			this.listeEntrees.getLast().setColumns(10);
		}
		contentPane.setLayout(gl_contentPane);

	}
}
