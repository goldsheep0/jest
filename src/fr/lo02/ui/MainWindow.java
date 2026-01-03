package fr.lo02.ui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;

import fr.lo02.controlleur.CaracteristicsController;
import fr.lo02.controlleur.SelectBotController;
import fr.lo02.controlleur.StartMenuBoutons;
import fr.lo02.jest.Carte;
import fr.lo02.jest.Partie;

public class MainWindow {

	private Partie partie;
	private int stepCount;
	private List<String> steps;
	
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
					
					//Pour faire apparaître la fenêtre popup pour l'affichage des trophees. Identique pour l'affichage d'un jest ou d'une offre.
					//LinkedList<Carte> trophees = new LinkedList<Carte>();
					//trophees.add(new Carte(Valeur.TROIS, Couleur.PIQUE, new StrategyTropheeHighest(Couleur.PIQUE)));
					//trophees.add(new Carte(Valeur.JOKER, Couleur.JOKER, new StrategyTropheeBestJest()));
					//trophees.add(new Carte(Valeur.AS, Couleur.PIQUE, new StrategyTropheeLowest(Couleur.PIQUE)));
					//window.afficherTrophee(trophees);
					
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
		stepCount = -1; 
		steps = new ArrayList<String>();
		steps.add("start");
		steps.add("save");
		steps.add("caracteristics");
		steps.add("bots");
		steps.add("players");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		partie = Partie.getPartie();
		next();
	}
	
	public void next() {
		stepCount++;
		System.out.println(steps.get(stepCount));
		nextStep();
	}
	
	public void next(int count) {
		stepCount += count;
		nextStep();
	}
	
	public void nextStep() {
		MainWindow main = this;
		
		switch(steps.get(stepCount)) {
		
		case "start":
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						startMenu = new StartMenu();
						startMenu.setBounds(100, 100, 450, 300);
						startMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						startMenu.setVisible(true);
						new StartMenuBoutons(startMenu, loadSaveMenu, selectCaracteristicsMenu, partie, main);
					} catch (Exception e) {e.printStackTrace();}
				}
			});
			break;
			
		case "save":
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						startMenu.setVisible(false);
						startMenu.setEnabled(false);
						loadSaveMenu = new LoadSaveMenu();
						loadSaveMenu.setBounds(100, 100, 450, 300);
						loadSaveMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						loadSaveMenu.setVisible(true);
					} catch (Exception e) {e.printStackTrace();}
				}
			});
			break;
			
		case "caracteristics":
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						if (loadSaveMenu != null) {
							loadSaveMenu.setVisible(false);
							loadSaveMenu.setEnabled(false);
						} if (startMenu != null) {
							startMenu.setVisible(false);
							startMenu.setEnabled(false);
						}
						selectCaracteristicsMenu = new SelectCaracteristicsMenu();
						selectCaracteristicsMenu.setBounds(100, 100, 450, 300);
						selectCaracteristicsMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						selectCaracteristicsMenu.setVisible(true);
						new CaracteristicsController(selectCaracteristicsMenu, selectBotMenu, partie, main);
					} catch (Exception e) {e.printStackTrace();}
				}
			});
			break;
			
		case "bots":
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						selectCaracteristicsMenu.setVisible(false);
						selectCaracteristicsMenu.setEnabled(false);
						selectBotMenu = new SelectBotMenu(partie.getNombreJoueursTotal()); //ATTENTION, créer cette fenêtre apres avoir selectionner les caractéristiques de la partie.
						selectBotMenu.setBounds(100, 100, 450, 300);
						selectBotMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						selectBotMenu.setVisible(true);
						new SelectBotController(selectBotMenu, enterPlayerNamesMenu, partie, main);
					} catch (Exception e) {e.printStackTrace();}
				}
			});
			break;
			
		case "players":
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						selectBotMenu.setVisible(false);
						selectBotMenu.setEnabled(false);
						enterPlayerNamesMenu = new EnterPlayerNamesMenu(partie.getNombreJoueursTotal()); //ATTENTION, créer cette fenêtre apres avoir selectionner les bots de la partie.
						enterPlayerNamesMenu.setBounds(100, 100, 450, 300);
						enterPlayerNamesMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						enterPlayerNamesMenu.setVisible(true);
					} catch (Exception e) {e.printStackTrace();}
				}
			});
			break;
			
		case "":
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						enterPlayerNamesMenu.setVisible(false);
						enterPlayerNamesMenu.setEnabled(false);
					} catch (Exception e) {e.printStackTrace();}
				}
			});
			break;
			
		}
	}

}
