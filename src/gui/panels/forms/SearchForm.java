package gui.panels.forms;

import interfaces.Database;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import database.User;
import main.BicycleGarageManager;

public class SearchForm extends Form {
	private BicycleGarageManager manager;
	private String personnummer;
	private User user;
	private String[] columnNames = { "Personummer", "Förnamn", "Efternman", "Mail",
			"Telefonnummer", "PIN-kod", "Reserverade platser",
			"Lediga platser" };
	public SearchForm(BicycleGarageManager manager) {
		super(manager, "Sök");
		this.manager = manager;
	}

	@Override
	public String[] getLabels() {
		String[] labels = { "Sök användare med hjälp av personnummer, namn, mailadress, telefonnummer" };
		return labels;
	}

	@Override
	public int[] getWidths() {
		int[] widths = { 12 };
		return widths;
	}
	
	@Override
	public boolean check(String[] fields) {
		return true;
	}

	@Override
	public void action(String[] fields) {
		Database db = manager.getDB();
		ResultSet rs = db.extractUsers();
		Object[][] data = new Object[1][8];
		try {
			while (rs.next()) {
				for (int i = 0; i < 6; i++) {
					if (rs.getString(i + 1).equals(fields[0])) {
					 personnummer = rs.getString(1);
					 break;
					}
				}
				user = db.getUser(personnummer);
			}
			for (int i = 0; i < 6; i++){
				data[0][i] = rs.getString(i + 1);
			}
			data[0][6] = rs.getInt(7);
			data[0][7] = rs.getInt(8);
			
			JTable table = new JTable(data, columnNames);
//			add(table.getTableHeader(), BorderLayout.CENTER);
			
			table.setEnabled(false);
//			table.setFillsViewportHeight(true);
//			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			JScrollPane scrollPane = new JScrollPane(table);
//			add(scrollPane, BorderLayout.SOUTH);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
