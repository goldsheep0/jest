package fr.lo02.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;

public class LoadSaveMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadSaveMenu window = new LoadSaveMenu();
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
	public LoadSaveMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblSauvegardesDisponibles = new JLabel("Sauvegardes disponibles");
		
		JList list = new JList();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(136)
					.addComponent(lblSauvegardesDisponibles)
					.addContainerGap(135, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(74)
					.addComponent(list, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
					.addGap(74))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(52)
					.addComponent(lblSauvegardesDisponibles)
					.addGap(26)
					.addComponent(list, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
					.addGap(172))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
