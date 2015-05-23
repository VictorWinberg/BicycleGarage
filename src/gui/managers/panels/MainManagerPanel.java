package gui.managers.panels;

import gui.forms.buttons.RegisterUserButton;
import gui.misc.buttons.ClearInactiveUsersButton;
import gui.misc.buttons.ExitButton;
import gui.misc.buttons.MasterResetButton;
import gui.misc.buttons.NumberOfAvailableSlotsButton;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;

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
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(8, 1, 1, 1));
		panel.add(new RegisterUserButton(manager, 1.1));
		panel.add(new ClearInactiveUsersButton(manager, 1.1));
		panel.add(new NumberOfAvailableSlotsButton(manager, 1.1));
		panel.add(new JLabel(""));
		panel.add(new MasterResetButton(manager, 1.1));
		panel.add(new ExitButton(manager, 1.1));
		add(panel, BorderLayout.CENTER);
	}
}