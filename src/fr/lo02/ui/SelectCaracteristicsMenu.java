package fr.lo02.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.SwingConstants;

public class SelectCaracteristicsMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectCaracteristicsMenu frame = new SelectCaracteristicsMenu();
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
	public SelectCaracteristicsMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblSlectionnerUneVariante = new JLabel("Caractéristiques de la partie");
		lblSlectionnerUneVariante.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnSuivant = new JButton("Suivant");
		
		JLabel lblNombreJoueurs = new JLabel("Nombre joueurs");
		
		JPanel panel = new JPanel();
		
		 
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(123)
					.addComponent(lblSlectionnerUneVariante, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(133))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(30)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNombreJoueurs, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)))
					.addGap(110))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(182)
					.addComponent(btnSuivant, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(191))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(50)
					.addComponent(lblSlectionnerUneVariante, GroupLayout.PREFERRED_SIZE, 16, Short.MAX_VALUE)
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNombreJoueurs, GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
							.addGap(20)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)))
					.addGap(20)
					.addComponent(btnSuivant, GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
					.addGap(20))
		);
		
		JRadioButton radioButton = new JRadioButton("3");
		panel.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("4");
		panel.add(radioButton_1);
		
		ButtonGroup group = new ButtonGroup();
		group.add(radioButton);
		group.add(radioButton_1);
		
		JLabel lblVariante = new JLabel("Variante");
		scrollPane.setColumnHeaderView(lblVariante);
		
		String[] variantes = {"Original","Variante 1 : Un joker supplémentaire dans les trophées avec le trophée Suit Majority.","Variante 2 : Ajout de la règle Save Diamonds With Love et Trophées aléatoires sur les cartes"};
		
		JList<String> list = new JList<String>(variantes);
		
		scrollPane.setViewportView(list);
		contentPane.setLayout(gl_contentPane);

	}
}
