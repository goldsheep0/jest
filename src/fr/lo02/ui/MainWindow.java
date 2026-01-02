package fr.lo02.ui;

import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.JFrame;

import fr.lo02.jest.Carte;
import fr.lo02.jest.enums.Couleur;
import fr.lo02.jest.enums.Valeur;
import fr.lo02.jest.regle.attributionTrophees.StrategyTropheeBestJest;
import fr.lo02.jest.regle.attributionTrophees.StrategyTropheeHighest;
import fr.lo02.jest.regle.attributionTrophees.StrategyTropheeLowest;

public class MainWindow {

	private StartMenu startMenu;
	private LoadSaveMenu loadSaveMenu;
	private SelectCaracteristicsMenu selectCaracteristicsMenu;
	private SelectBotMenu selectBotMenu;
	private EnterPlayerNamesMenu enterPlayerNamesMenu;

	public void afficherTrophee(LinkedList<Carte> trophees) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					SeeTropheeDisplay popupJestDisplay = new SeeTropheeDisplay(trophees);
					popupJestDisplay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					//To enable a window.
					popupJestDisplay.setVisible(true);
					popupJestDisplay.setEnabled(true);
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void afficherJest(LinkedList<Carte> jest) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					SeeJestDisplay popupJestDisplay = new SeeJestDisplay(jest);
					popupJestDisplay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					//To enable a window.
					popupJestDisplay.setVisible(true);
					popupJestDisplay.setEnabled(true);
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void afficherOffre(LinkedList<Carte> offre) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					SeeOfferDisplay popupOfferDisplay = new SeeOfferDisplay(offre);
					popupOfferDisplay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					//To enable a window.
					popupOfferDisplay.setVisible(true);
					popupOfferDisplay.setEnabled(true);
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Launch the application.
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
					window.enterPlayerNamesMenu.setVisible(true);
					window.enterPlayerNamesMenu.setEnabled(true);
					
					//Pour faire apparaître la fenêtre popup pour l'affichage des trophees. Identique pour l'affichage d'un jest ou d'une offre.
					LinkedList<Carte> trophees = new LinkedList<Carte>();
					trophees.add(new Carte(Valeur.TROIS, Couleur.PIQUE, new StrategyTropheeHighest(Couleur.PIQUE)));
					trophees.add(new Carte(Valeur.JOKER, Couleur.JOKER, new StrategyTropheeBestJest()));
					trophees.add(new Carte(Valeur.AS, Couleur.PIQUE, new StrategyTropheeLowest(Couleur.PIQUE)));
					
					
					window.afficherTrophee(trophees);
					
					
					
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
		
		selectBotMenu = new SelectBotMenu(3); //ATTENTION, créer cette fenêtre apres avoir selectionner les caractéristiques de la partie.
		selectBotMenu.setBounds(100, 100, 450, 300);
		selectBotMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		selectBotMenu.setVisible(false);
		selectBotMenu.setEnabled(false);
		
		enterPlayerNamesMenu = new EnterPlayerNamesMenu(3); //ATTENTION, créer cette fenêtre apres avoir selectionner les bots de la partie.
		enterPlayerNamesMenu.setBounds(100, 100, 450, 300);
		enterPlayerNamesMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		enterPlayerNamesMenu.setVisible(false);
		enterPlayerNamesMenu.setEnabled(false);
		
		
	}

}
