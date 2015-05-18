package main;

import gui.FormState;
import gui.ViewState;
import gui.panels.forms.RegisterBicycleForm;
import gui.panels.forms.RegisterUserForm;
import gui.panels.forms.UnregisterBicycleForm;
import gui.panels.forms.UnregisterUserForm;
import gui.panels.managers.BicycleManagerPanel;
import gui.panels.managers.MainManagerPanel;
import gui.panels.managers.NavigationPanel;
import gui.panels.managers.SearchManagerPanel;
import gui.panels.managers.UserManagerPanel;
import interfaces.BarcodePrinter;
import interfaces.Database;
import interfaces.ElectronicLock;
import interfaces.PinCodeTerminal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import database.DatabaseDriver;

/**
 * Denna klass skapar operatörsgränssnittet. Den länkar ihop de fem
 * panelklasserna och använder sig av olika “states”, t.ex. startläge och
 * användarläge. Här registrerar man även de olika testdrivrutinerna.
 * 
 * @author Victor Winberg, Anton Göransson, Povel Larsson, Erik Danielsson, Emma
 *         Asklund, Tobias Olsson
 */
public class BicycleGarageManager {

	private JFrame frame;
	private NavigationPanel navPanel;
	private JPanel panel, mainPanel, userPanel, bicyclePanel, searchPanel;
	private Database db;

	public BicycleGarageManager() {
		frame = new JFrame("Operatörsgränssnittet");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setMinimumSize(new Dimension(600, 300));
		frame.setLayout(new BorderLayout(0, 10));
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				String options[] = { "Ja, stäng", "Nej" };
				if (JOptionPane.showOptionDialog(null,
						"Är du säker på att du vill stänga applikationen?",
						"Säkerhetsfråga", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]) == JOptionPane.YES_OPTION) {
					frame.dispose();
				}
			}
		});

		panel = new JPanel();
		navPanel = new NavigationPanel(this);
		mainPanel = new MainManagerPanel(this);
		userPanel = new UserManagerPanel(this);
		bicyclePanel = new BicycleManagerPanel(this);
		searchPanel = new SearchManagerPanel(this);

		try {
			db = new DatabaseDriver();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		changeState(ViewState.START_STATE);

		frame.add(navPanel, BorderLayout.NORTH);
		frame.add(panel, BorderLayout.CENTER);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Byter operatörsgränsnittets panel till läge state.
	 * 
	 * @param state
	 *            Ett ViewState läge
	 */
	public void changeState(ViewState state) {
		panel.removeAll();
		switch (state) {
		case START_STATE:
			panel.add(mainPanel);
			navPanel.setTitle("Huvudmeny");
			break;
		case USER_STATE:
			panel.add(userPanel);
			navPanel.setTitle("Användarpanel");
			break;
		case BICYCLE_STATE:
			panel.add(bicyclePanel);
			navPanel.setTitle("Cyklelpanel");
			break;
		case SEARCH_STATE:
			panel.add(searchPanel);
			navPanel.setTitle("Sökpanel");
			break;
		}
		panel.revalidate();
		panel.repaint();
	}

	/**
	 * Hämtar en cykelmanagerns databas
	 * 
	 * @return Database databas, annars null
	 */
	public Database getDB() {
		return db;
	}

	/**
	 * Öppnar ett formulär-fönster med läge state.
	 * 
	 * @param state
	 *            Ett FormState läge
	 */
	public void form(FormState state) {
		enable(false);
		switch (state) {
		case REGISTER_USER: 
			new RegisterUserForm(this); 
			break;
		case UNREGISTER_USER:
			new UnregisterUserForm(this);
			break;
		case REGISTER_BICYCLE:
			new RegisterBicycleForm(this);
			break;
		case UNREGISTER_BICYCLE:
			new UnregisterBicycleForm(this);
			break;
		}
	}

	@SuppressWarnings("deprecation")
	public void enable(boolean b) {
		frame.enable(b);
	}

	private BarcodePrinter printer;
	private ElectronicLock entryLock, exitLock;
	private PinCodeTerminal terminal;

	/**
	 * Registrerar hårdvara så att BicycleGarageManager vet vilka drivrutiner
	 * som är tillgängliga.
	 */
	public void registerHardwareDrivers(BarcodePrinter printer,
			ElectronicLock entryLock, ElectronicLock exitLock,
			PinCodeTerminal terminal) {
		this.printer = printer;
		this.entryLock = entryLock;
		this.exitLock = exitLock;
		this.terminal = terminal;
	}

	/**
	 * Kommer att kallas när en användare har använt strecksläsaren vid
	 * entrédörren. Cykel ID bör vara en sträng med 5 tecken, där varje tecken
	 * kan vara '0', '1', ... "9".
	 */
	public void entryBarcode(String bicycleID) {
		printer.printBarcode(bicycleID);
	}

	/**
	 * Kommer att kallas när en användare har använt strecksläsaren vid
	 * cykelutgången. bicycleID bör vara en sträng med 5 tecken, där varje tecken
	 * kan vara '0', '1', ... "9".
	 */
	public void exitBarcode(String bicycleID) {
		printer.printBarcode(bicycleID);
	}

	/**
	 * Kommer att kallas när en användare har tryckt på en tangent på knappsats
	 * på entrédörren. Följande tecken kan vara intryckta: "0", "1", ... "9",
	 * "*", "#".
	 */
	public void entryCharacter(char c) {
		terminal.lightLED(1, 1);
	}

	public static void main(String[] args) {
		new BicycleGarageManager();
	}
}
