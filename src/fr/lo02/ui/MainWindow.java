package fr.lo02.ui;

import java.awt.EventQueue;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import fr.lo02.controlleur.*;
import fr.lo02.jest.*;
import fr.lo02.jest.enums.*;

@SuppressWarnings("deprecation")
public class MainWindow extends Thread implements Observer {

	private Partie partie;
	
	private StartMenu startMenu;
	private LoadSaveMenu loadSaveMenu;
	private SelectCaracteristicsMenu selectCaracteristicsMenu;
	private SelectBotMenu selectBotMenu;
	private EnterPlayerNamesMenu enterPlayerNamesMenu;
	private NouveauRoundMenu nouveauRoundMenu;
	private ConceptionOffreMenu conceptionOffreMenu;
	private ChoisirOffreMenu choisirOffreMenu;
	private ChoixOffreBotMenu choisirOffreBotMenu;

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
				try {SeeJestDisplay popupJestDisplay = new SeeJestDisplay(jest);
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
				try {SeeOfferDisplay popupOfferDisplay = new SeeOfferDisplay(offre);
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
	 * Launch the start window.
	 */
	public void run() {}

	/**
	 * Create the MainWindow object.
	 */
	public MainWindow() {
		partie = Partie.getPartie();
	}
	
	public void enableWindow (JFrame window, boolean enabled) {
		if (window != null) {
			window.setVisible(enabled);
			window.setEnabled(enabled);
			window = null;
		}
	}
	
	public void initializeWindow (JFrame window) {
		window.setBounds(100, 100, 500, 500);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	
	public void start_menu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					startMenu = new StartMenu();
					initializeWindow(startMenu);
					new StartMenuBoutons(startMenu, partie);
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	}
	
	public void load_menu() { //TODO
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					enableWindow(startMenu, false);
					loadSaveMenu = new LoadSaveMenu();
					initializeWindow(loadSaveMenu);
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	}
	
	public void caracteristics_menu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if (loadSaveMenu != null) {
						enableWindow(loadSaveMenu, false);
					} if (startMenu != null) {
						enableWindow(startMenu, false);
					}
					selectCaracteristicsMenu = new SelectCaracteristicsMenu();
					initializeWindow(selectCaracteristicsMenu);
					new CaracteristicsController(selectCaracteristicsMenu, partie);
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	}
	
	public void bots_menu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					enableWindow(selectCaracteristicsMenu, false);
					selectBotMenu = new SelectBotMenu(partie.getNombreJoueursTotal()); //ATTENTION, créer cette fenêtre apres avoir selectionner les caractéristiques de la partie.
					initializeWindow(selectBotMenu);
					new SelectBotController(selectBotMenu, partie);
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	}
	
	public void players_menu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					enableWindow(selectBotMenu, false);
					enterPlayerNamesMenu = new EnterPlayerNamesMenu(partie.getNombreJoueursTotal() - partie.getJoueurs().size()); //ATTENTION, créer cette fenêtre apres avoir selectionner les bots de la partie.
					initializeWindow(enterPlayerNamesMenu);
					new SelectPlayerController(enterPlayerNamesMenu, partie);
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	}
	
	public void new_round_menu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					enableWindow(enterPlayerNamesMenu, false);
					nouveauRoundMenu = new NouveauRoundMenu();
					initializeWindow(nouveauRoundMenu);
					new NewRoundController(nouveauRoundMenu, partie);
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	}
	
	public void faire_offre_menu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					enableWindow(nouveauRoundMenu, false);
					conceptionOffreMenu = new ConceptionOffreMenu(partie.getJoueurFocus());
					initializeWindow(conceptionOffreMenu);
					new ConceptionOffreController(conceptionOffreMenu, partie);
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	}
	
	public void choisir_offre_menu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					enableWindow(nouveauRoundMenu, false);
					enableWindow(choisirOffreMenu, false);
					enableWindow(choisirOffreBotMenu, false);
					LinkedList<Joueur> listeJoueur = new LinkedList<Joueur>(partie.getJoueurs());
					choisirOffreMenu = new ChoisirOffreMenu(partie.getJoueurFocus(), listeJoueur);
					initializeWindow(choisirOffreMenu);
					new ChoisirOffreController(choisirOffreMenu, partie);
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	}
	
	public void choisir_offre_bot_menu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					enableWindow(nouveauRoundMenu, false);
					enableWindow(choisirOffreMenu, false);
					enableWindow(choisirOffreBotMenu, false);
					LinkedList<Joueur> listeJoueur = new LinkedList<Joueur>(partie.getJoueurs());
					choisirOffreBotMenu = new ChoixOffreBotMenu(partie.getJoueurFocus(), listeJoueur, partie.getRound().getCarteChoisieBot());
					initializeWindow(choisirOffreBotMenu);
					new ChoisirOffreBotController(choisirOffreBotMenu, partie);
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if (o instanceof Partie) {
			
			PartieState state = (PartieState)arg;
			
			switch(state) {
			
			case START:
				start_menu();
				break;
			case LOAD:
				load_menu();
				break;
			case C_VARIANTE:
				caracteristics_menu();
				break;
			case C_BOTS:
				bots_menu();
				break;
			case C_PLAYERS:
				players_menu();
				break;
			case NEW_ROUND:
				new_round_menu();
				break;
			case FAIRE_OFFRE:
				faire_offre_menu();
				break;
			case CHOISIR_OFFRE:
				choisir_offre_menu();
				break;
			case CHOISIR_OFFRE_BOT:
				choisir_offre_bot_menu();
				break;
			default:
				break;
			
			}
			
		}
		
	}

}
