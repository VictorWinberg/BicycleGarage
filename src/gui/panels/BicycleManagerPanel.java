package gui.panels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;

import main.BicycleGarageManager;

/**
 * Denna panel öppnas när man klickar på knappen “Cyklar” i
 * operatörsgränssnittet och här visas alla cyklar samt vilken användare och
 * streckkod de är kopplade till. Klassen kopplar panelen till den
 * cykelgaragemanager anger.
 *
 */
@SuppressWarnings("serial")
public class BicycleManagerPanel extends JPanel {

	/**
	 * Skapar en cykelmanagerpanel som hanterar cyklar
	 * 
	 * @param manager
	 *            cykelgaragemanager
	 */
	public BicycleManagerPanel(BicycleGarageManager manager) {
		setBorder(new SoftBevelBorder(1));
		add(new JLabel("Hantera cyklar osv."));
	}
}
