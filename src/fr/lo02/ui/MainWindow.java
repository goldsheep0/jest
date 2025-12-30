package fr.lo02.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class MainWindow {

	private StartMenu startMenu;
	private LoadSaveMenu loadSaveMenu;
	private SelectCaracteristicsMenu selectCaracteristicsMenu;
	private SelectBotMenu selectBotMenu;

	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.startMenu.setVisible(true);
					
					
					//To disable a window.
					window.startMenu.setEnabled(false);
					window.startMenu.setVisible(false);
					
					//To enable a window.
					window.selectBotMenu.setVisible(true);
					window.selectBotMenu.setEnabled(true);
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		startMenu = new StartMenu();
		startMenu.setBounds(100, 100, 450, 300);
		startMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startMenu.setVisible(true);
		startMenu.setEnabled(true);
		
		loadSaveMenu = new LoadSaveMenu();
		loadSaveMenu.setBounds(100, 100, 450, 300);
		loadSaveMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loadSaveMenu.setVisible(false);
		loadSaveMenu.setEnabled(false);
		
		selectCaracteristicsMenu = new SelectCaracteristicsMenu();
		selectCaracteristicsMenu.setBounds(100, 100, 450, 300);
		selectCaracteristicsMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		selectCaracteristicsMenu.setVisible(false);
		selectCaracteristicsMenu.setEnabled(false);
		
		selectBotMenu = new SelectBotMenu();
		selectBotMenu.setBounds(100, 100, 450, 300);
		selectBotMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		selectBotMenu.setVisible(false);
		selectBotMenu.setEnabled(false);
	}

}
