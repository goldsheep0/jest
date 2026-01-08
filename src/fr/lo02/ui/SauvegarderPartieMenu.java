package fr.lo02.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.io.Serializable;

import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class SauvegarderPartieMenu extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNouvelleSauvegarde;
	private JButton btnEcraserSauvegarde;
	private JList<String> list;
	
	public JButton getBtnNouvelleSauvegarde() {return btnNouvelleSauvegarde;}
	public JButton getBtnEcraserSauvegarde() {return btnEcraserSauvegarde;}
	public JList<String> getListe() {return list;}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SauvegarderPartieMenu frame = new SauvegarderPartieMenu();
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
	public SauvegarderPartieMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnNouvelleSauvegarde = new JButton("Nouvelle sauvegarde");
		panel_1.add(btnNouvelleSauvegarde);
		
		btnEcraserSauvegarde = new JButton("Ecraser sauvegarde");
		panel_1.add(btnEcraserSauvegarde);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.NORTH);
		
		JLabel lblSauvag = new JLabel("Sauvegarder la partie");
		lblSauvag.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(143)
					.addComponent(lblSauvag, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(142))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSauvag, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblSlectionnerUneSauvegarde = new JLabel("Sélectionner une sauvegarde :");
		panel.add(lblSlectionnerUneSauvegarde, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		String[] listOfFiles = LoadSaveMenu.listAvailableSaves();
		
		if(listOfFiles!=null) {
			list = new JList<String>(listOfFiles);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(list);
			
		}else {
			JLabel aucuneSauvegardeDisponible = new JLabel("<html><body>Aucun sauvegarde disponible.<br>Appuyer sur nouvelle sauvegarde pour sauvegarder la partie.</body></html>");
			scrollPane.setViewportView(aucuneSauvegardeDisponible);
		}
		

	}
}
