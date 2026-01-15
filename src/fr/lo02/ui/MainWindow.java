package fr.lo02.ui;

import java.awt.EventQueue;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import fr.lo02.controlleur.*;
import fr.lo02.jest.*;
import fr.lo02.jest.enums.*;

/**
 * Permet l'utilisation d'une Vue (patron MVC) graphique.<br>
 * Crée et supprime les fenêtres de la vue en fonction de l'état de la partie.
 */
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
	private AttributionTropheesDisplay tropheesMenu;
	private ScoreBoard scoreboard;
	private SauvegarderPartieMenu saveMenu;
	private NouvelleSauvegardeMenu newSaveMenu;

	/**
	 * Crée une fenêtre avec les trophées de la partie.
	 * @param trophees la liste des trophées de la partie.
	 */
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
	
	/**
	 * Crée une fenêtre avec les cartes du jest du joueur.
	 * @param jest la liste des cartes du jest du joueur.
	 */
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
	
	/**
	 * [Debug only] Crée une fenêtre avec les cartes d'une offre.
	 * @param offre la liste des cartes d'une offre.
	 */
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
	
	/**
	 * Affiche ou supprime une fenêtre.
	 * @param window la fenêtre à afficher.
	 * @param enabled affiche si true, supprime si false.
	 */
	public void enableWindow (JFrame window, boolean enabled) {
		if (window != null) {
			window.setVisible(enabled);
			window.setEnabled(enabled);
			if (!enabled) {
				window.dispose();
				window = null;
			}
		}
	}
	
	/**
	 * Initialise une fenêtre (taille, opération, affichage).
	 * @param window la fen^tre à afficher.
	 */
	public void initializeWindow (JFrame window) {
		if (window instanceof ChoisirOffreMenu || window instanceof ChoixOffreBotMenu) {
			window.setBounds(250, 150, 1000, 500);
		} else {
			window.setBounds(500, 150, 500, 500);
		}
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	
	/**
	 * Lance le menu de démarrage;
	 */
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
	
	/**
	 * Lance le menu de caractéristiques.
	 */
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
	
	/**
	 * Lance le menu des bots.
	 */
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
	
	/**
	 * Lance le menu des joueurs.
	 */
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
	
	/**
	 * Lance le menu de nouveau round.
	 */
	public void new_round_menu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					enableWindow(enterPlayerNamesMenu, false);
					enableWindow(choisirOffreMenu, false);
					enableWindow(choisirOffreBotMenu, false);
					nouveauRoundMenu = new NouveauRoundMenu();
					initializeWindow(nouveauRoundMenu);
					new NewRoundController(nouveauRoundMenu, partie);
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	}
	
	/**
	 * Lance le menu de création des offres.
	 */
	public void faire_offre_menu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					enableWindow(nouveauRoundMenu, false);
					enableWindow(conceptionOffreMenu, false);
					conceptionOffreMenu = new ConceptionOffreMenu(partie.getJoueurFocus());
					initializeWindow(conceptionOffreMenu);
					new ConceptionOffreController(conceptionOffreMenu, partie);
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	}
	
	/**
	 * Lance le menu du choix des offres.
	 */
	public void choisir_offre_menu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					enableWindow(conceptionOffreMenu, false);
					enableWindow(choisirOffreMenu, false);
					enableWindow(choisirOffreBotMenu, false);
					LinkedList<Joueur> listeJoueur = Round.getAutresJoueurs(partie.getJoueurFocus());
					choisirOffreMenu = new ChoisirOffreMenu(partie.getJoueurFocus(), listeJoueur);
					initializeWindow(choisirOffreMenu);
					new ChoisirOffreController(choisirOffreMenu, partie);
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	}
	
	/**
	 * Lance le menu du choix des offres pour les bots.
	 */
	public void choisir_offre_bot_menu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					enableWindow(conceptionOffreMenu, false);
					enableWindow(choisirOffreMenu, false);
					enableWindow(choisirOffreBotMenu, false);
					LinkedList<Joueur> listeJoueur = Round.getAutresJoueurs(partie.getJoueurFocus());
					choisirOffreBotMenu = new ChoixOffreBotMenu(partie.getJoueurFocus(), listeJoueur, partie.getRound().getCarteChoisieBot());
					initializeWindow(choisirOffreBotMenu);
					new ChoisirOffreBotController(choisirOffreBotMenu, partie);
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	}
	
	/**
	 * Lance le menu d'attribution des trophées.
	 */
	public void trophees_menu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					enableWindow(conceptionOffreMenu, false);
					enableWindow(choisirOffreMenu, false);
					enableWindow(choisirOffreBotMenu, false);
					tropheesMenu = new AttributionTropheesDisplay(partie.getTropheesJoueurs());
					initializeWindow(tropheesMenu);
					new TropheesController(tropheesMenu);
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	}
	
	/**
	 * Lance le menu de visionnage des scores.
	 */
	public void scoreboard_menu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					enableWindow(tropheesMenu, false);
					scoreboard = new ScoreBoard(partie.compterScores());
					initializeWindow(scoreboard);
					new ScoreBoardController(scoreboard);
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	}
	
	/**
	 * Lance le menu de chargement d'une partie.
	 */
	public void load_menu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					enableWindow(scoreboard, false);
					enableWindow(startMenu, false);
					loadSaveMenu = new LoadSaveMenu();
					initializeWindow(loadSaveMenu);
					new LoadMenuController(loadSaveMenu);
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	}
	
	/**
	 * Lance le menu de sauvegarde (choix du fichier) d'une partie.
	 */
	public void save_menu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					enableWindow(scoreboard, false);
					enableWindow(nouveauRoundMenu, false);
					saveMenu = new SauvegarderPartieMenu();
					initializeWindow(saveMenu);
					new SaveController(saveMenu);
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	}
	
	/**
	 * Lance le menu de sauvegarde (l'utilisateur entre le nom du fichier) d'une partie.
	 */
	public void new_save_menu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					enableWindow(saveMenu, false);
					newSaveMenu = new NouvelleSauvegardeMenu();
					initializeWindow(newSaveMenu);
					new NewSaveController(newSaveMenu);
				} catch (Exception e) {e.printStackTrace();}
			}
		});
	}

	/**
	 * Mise à jour des fenêtres, lorsque la Partie change d'état.
	 */
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
			case TROPHEES:
				trophees_menu();
				break;
			case SCOREBOARD:
				scoreboard_menu();
				break;
			case SAVE:
				save_menu();
				break;
			case NEW_SAVE:
				new_save_menu();
				break;
			default:
				break;
			
			}
			
		}
		
	}

}
