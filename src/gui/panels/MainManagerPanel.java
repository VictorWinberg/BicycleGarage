package gui.panels;

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
		setBorder(new SoftBevelBorder(1));
		add(new JLabel("Välkommen till cykelgaraget!"));
	}
}