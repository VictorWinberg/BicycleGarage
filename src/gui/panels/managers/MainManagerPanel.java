package gui.panels.managers;

import gui.buttons.managers.ClearInactiveUsers;
import gui.buttons.managers.RegisterBicycleButton;
import gui.buttons.managers.RegisterUserButton;
import gui.buttons.managers.UnregisterBicycleButton;
import gui.buttons.managers.UnregisterUserButton;
import gui.buttons.navigation.ExitButton;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import main.BicycleGarageManager;

/**
 * Denna panel öppnas när man startar programmet, här finns knappar för att
 * utföra olika operationer som t.ex. lägg till, samt ta bort användare. Klassen
 * kopplar även den cykelgaragemanager som man anger till panelen.
 *
 */
@SuppressWarnings("serial")
public class MainManagerPanel extends JPanel {

	/**
	 * Skapar en huvudmenypanel som utföra olika operationer som t.ex. lägg
	 * till, samt ta bort användare
	 * 
	 * @param manager
	 *            cykelgaragemanager
	 */
	public MainManagerPanel(BicycleGarageManager manager) {
		setLayout(new GridLayout(8, 1, 10, 10));
		add(new RegisterUserButton(manager, 1.1));
		add(new UnregisterUserButton(manager, 1.1));
		add(new RegisterBicycleButton(manager, 1.1));
		add(new UnregisterBicycleButton(manager, 1.1));
		add(new ClearInactiveUsers(manager, 1.1));
		add(new JLabel(""));
		add(new ExitButton(manager, 1.1));
	}
}