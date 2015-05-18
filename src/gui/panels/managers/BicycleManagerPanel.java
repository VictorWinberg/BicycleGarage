package gui.panels.managers;

import gui.buttons.managers.RegisterBicycleButton;
import gui.buttons.managers.UnregisterBicycleButton;
import interfaces.Database;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
	private BicycleGarageManager manager;

	/**
	 * Skapar en cykelmanagerpanel som hanterar cyklar
	 * 
	 * @param manager
	 *            cykelgaragemanager
	 */
	public BicycleManagerPanel(BicycleGarageManager manager) {
		this.manager = manager;
		setBorder(new SoftBevelBorder(1));
		setLayout(new BorderLayout());
		JPanel panel = new JPanel();

		panel.add(new RegisterBicycleButton(manager, 1.1));
		panel.add(new UnregisterBicycleButton(manager, 1.1));
		add(panel, BorderLayout.NORTH);
		add(new JLabel("Hantera cyklar osv."), BorderLayout.CENTER);
		update();
		// "Lediga platser"

	}

	public void update() {
		Database db = manager.getDB();
		try {
			ResultSet bicycles = db.extractBicycles();
			ResultSetMetaData rmsd = bicycles.getMetaData();
			bicycles.last();
			Object[][] data = new Object[bicycles.getRow()][8];
			bicycles.beforeFirst();
			String[] columnNames = { "Streckkod", "Ägare", "Inlämnad" };
			int j = 0;
			while (bicycles.next()) {
				data[j][0] = bicycles.getString(1);
				data[j][1] = bicycles.getString(2);
				if (bicycles.getBoolean(3) == true) {
					data[j][2] = "Ja";
				} else {
					data[j][2] = "Nej";
				}
				j++;
			}
			JTable table = new JTable(data, columnNames);
			add(table.getTableHeader(), BorderLayout.CENTER);
			// table.setEnabled(false);
			// table.setFillsViewportHeight(true);
			// table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			JScrollPane scrollPane = new JScrollPane(table);
			add(scrollPane, BorderLayout.SOUTH);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
