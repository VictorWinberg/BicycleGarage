package gui.managers.panels;

import gui.forms.buttons.RegisterBicycleButton;
import gui.forms.buttons.UnregisterBicycleButton;
import interfaces.Database;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database.User;
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
	private JPanel westPanel;
	private RegisterBicycleButton regBicBtn;

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
		westPanel = new JPanel();
		westPanel.setLayout(new GridLayout(10, 1, 1, 1));
		regBicBtn = new RegisterBicycleButton(manager, 1.1);
		westPanel.add(regBicBtn);
		westPanel.add(new UnregisterBicycleButton(manager, 1.1));

	}

	public void update() {
		removeAll();
		add(westPanel, BorderLayout.WEST);

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
				data[j][2] = bicycles.getBoolean(3) ? "Ja" : "Nej";
				j++;
			}
			JTable table = new JTable(data, columnNames) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			
			ListSelectionModel cellSelectionModel = table.getSelectionModel();
			cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					if (!e.getValueIsAdjusting()) {
						String selectedData = (String) table.getValueAt(table.getSelectedRow(), 1);
						User chosen = manager.getDB().getUser(selectedData);
						
						regBicBtn.changeUser(chosen);
						
					}
				}
			});
			
			
			
			table.getTableHeader().setReorderingAllowed(false);

			JScrollPane scrollPane = new JScrollPane(table);
			add(scrollPane, BorderLayout.CENTER);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
