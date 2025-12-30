package fr.lo02.ui;

import java.awt.EventQueue;
import java.io.File;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

public class LoadSaveMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadSaveMenu frame = new LoadSaveMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public String[] listAvailableSaves() {
		File savesDirectory = new File("./saves");		
		
		File[] existingSaveFiles = savesDirectory.listFiles();
		
		if (existingSaveFiles.length==1) {
			return null;
		}
		
		String[] existingSaveNames = new String[existingSaveFiles.length-1];
		
		int j=0;
		for(int i=0;i<existingSaveFiles.length;i++) {
			if(!existingSaveFiles[i].getName().equals("keep_this_directory.gitignore")) {
				existingSaveNames[j]=existingSaveFiles[i].getName();
				j++;
			}
		}
		
		return existingSaveNames;
	}

	/**
	 * Create the frame.
	 */
	public LoadSaveMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblSauvegardesDisponibles = new JLabel("Sauvegardes disponibles :");
		lblSauvegardesDisponibles.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnNewButton = new JButton("Charger");
		

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(131)
					.addComponent(lblSauvegardesDisponibles, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(121))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(77)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
					.addGap(66))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(180)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(169))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(50)
					.addComponent(lblSauvegardesDisponibles, GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
					.addGap(30)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(20))
		);
		
		
		String[] listOfFiles = listAvailableSaves();
		
		if(listOfFiles!=null) {
			JList<String> list = new JList<String>(listOfFiles);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(list);
			
		}else {
			JLabel aucuneSauvegardeDisponible = new JLabel("<html><body>Aucun sauvegarde disponible.<br>Appuyer sur suivant pour lancer une nouvelle partie.</body></html>");
			scrollPane.setViewportView(aucuneSauvegardeDisponible);
			btnNewButton.setText("Suivant");
		}
		
		
		
		
		
		
		contentPane.setLayout(gl_contentPane);

	}
}
