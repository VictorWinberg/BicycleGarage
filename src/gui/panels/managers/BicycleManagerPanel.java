package gui.panels.managers;

import gui.buttons.managers.RegisterBicycleButton;
import gui.buttons.managers.UnregisterBicycleButton;

import java.awt.BorderLayout;

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
		setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.add(new RegisterBicycleButton(manager, 1.1));
		panel.add(new UnregisterBicycleButton(manager, 1.1));
		add(panel, BorderLayout.NORTH);
		add(new JLabel("Hantera cyklar osv."), BorderLayout.CENTER);
	}
}
