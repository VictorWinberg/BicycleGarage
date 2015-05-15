package gui.panels.managers;

import gui.buttons.managers.RegisterUserButton;
import gui.buttons.managers.UnregisterUserButton;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.SoftBevelBorder;

import main.BicycleGarageManager;

/**
 * Denna panel öppnas när man klickar på “Användare” i operatörsgränssnittet och
 * här visas en tabell på vilka användare som är registrerade i systemet. Det
 * går även att ändra information om användare efter att man har klickat på dem.
 * Denna klass kopplar också panelen till den cykelgaragemanager man anger.
 *
 */
@SuppressWarnings("serial")
public class UserManagerPanel extends JPanel {

	/**
	 * Skapar en användarmanagerpanel som hanterar användare
	 * 
	 * @param manager
	 *            cykelgaragemanager
	 */
	public UserManagerPanel(BicycleGarageManager manager) {
		setBorder(new SoftBevelBorder(1));
		setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.add(new RegisterUserButton(manager, 1.1));
		panel.add(new UnregisterUserButton(manager, 1.1));
		add(panel, BorderLayout.NORTH);
		String[] columnNames = { "First Name", "Last Name", "Sport",
				"# of Years", "Vegetarian" };
		Object[][] data = {
				{ "Kathy", "Smith", "Snowboarding", new Integer(5),
						new Boolean(false) },
				{ "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
				{ "Sue", "Black", "Knitting", new Integer(2),
						new Boolean(false) },
				{ "Jane", "White", "Speed reading", new Integer(20),
						new Boolean(true) },
				{ "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
				{ "Sue", "Black", "Knitting", new Integer(2),
						new Boolean(false) },
				{ "Jane", "White", "Speed reading", new Integer(20),
						new Boolean(true) },
				{ "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
				{ "Sue", "Black", "Knitting", new Integer(2),
						new Boolean(false) },
				{ "Jane", "White", "Speed reading", new Integer(20),
						new Boolean(true) },
				{ "Joe", "Brown", "Pool", new Integer(10), new Boolean(false) } };
		JTable table = new JTable(data, columnNames);
		add(table.getTableHeader(), BorderLayout.CENTER);
//		table.setEnabled(false);
//		table.setFillsViewportHeight(true);
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.SOUTH);
	}
}
