package fr.lo02.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;

public class StartMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartMenu window = new StartMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblJest = new JLabel("Jest");
		lblJest.setFont(new Font("Dialog", Font.BOLD, 16));
		lblJest.setHorizontalAlignment(SwingConstants.CENTER);
		lblJest.setBounds(190, 54, 70, 15);
		frame.getContentPane().add(lblJest);
		
		JButton btnNouvellePartie = new JButton("Nouvelle partie");
		btnNouvellePartie.setBounds(154, 111, 142, 25);
		frame.getContentPane().add(btnNouvellePartie);
		
		JButton btnChargerPartie = new JButton("Charger partie");
		btnChargerPartie.setBounds(154, 175, 142, 25);
		frame.getContentPane().add(btnChargerPartie);
	}

}
