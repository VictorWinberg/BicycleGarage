package gui.managers.panels;

import gui.forms.buttons.EditUserButton;
import gui.forms.buttons.RegisterBicycleButton;
import gui.forms.buttons.RegisterUserButton;
import gui.forms.buttons.ReserveSlotButton;
import gui.forms.buttons.UnregisterBicycleButton;
import gui.forms.buttons.UnregisterUserButton;
import gui.misc.buttons.ClearInactiveUsersButton;
import gui.misc.buttons.ExitButton;

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
		setLayout(new GridLayout(9, 1, 10, 10));
		add(new RegisterUserButton(manager, 1.1));
		add(new EditUserButton(manager, manager.getDB().getUser("950407-0856"), 1.1)); // Testknapp, ska inte finnas egentligen
		add(new UnregisterUserButton(manager, 1.1));
		add(new RegisterBicycleButton(manager, 1.1));
		add(new UnregisterBicycleButton(manager, 1.1));
		add(new ClearInactiveUsersButton(manager, 1.1));
		add(new ReserveSlotButton(manager,1.1));
		add(new JLabel(""));
		add(new ExitButton(manager, 1.1));
	}
}