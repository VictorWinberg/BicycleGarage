package gui.panels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;

import main.BicycleGarageManager;

/**
 * Denna panel öppnas när man klickar på “Sök” i operatörsgränssnittet och här
 * visas en sökruta med en sökfunktion för att hitta en användare.
 * 
 */
@SuppressWarnings("serial")
public class SearchManagerPanel extends JPanel {

	public SearchManagerPanel(BicycleGarageManager manager) {
		setBorder(new SoftBevelBorder(1));
		add(new JLabel("Asfet sökfunktion osv #SWAG."));
	}
}
