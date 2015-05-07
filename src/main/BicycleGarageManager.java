package main;

import gui.ViewState;
import gui.buttons.ExitButton;
import gui.panels.*;
import interfaces.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JFrame;
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
@SuppressWarnings("serial")
public class BicycleGarageManager extends JFrame {

	private NavigationPanel navPanel;
	private JPanel panel, mainPanel, userPanel, bicyclePanel, searchPanel;
	private Database db;

	public BicycleGarageManager() {
		super("Operatörsgränssnittet");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(600, 300));
		setLayout(new BorderLayout(0, 10));

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

		add(navPanel, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
		add(new ExitButton(this), BorderLayout.SOUTH);

		pack();
		setVisible(true);
	}
	/**
	 * Byter operatörsgränsnittets panel till läge state.
	 * @param state Ett ViewState läge
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
	
	/** Hämtar en cykelmanagerns databas
	 * @return Database databas, annars null */
	public Database getDB() {
		return db;
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
	 * cykelutgången. Cykel ID bör vara en sträng med 5 tecken, där varje tecken
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
