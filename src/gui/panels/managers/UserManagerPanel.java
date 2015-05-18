package gui.panels.managers;

import gui.buttons.managers.RegisterUserButton;
import gui.buttons.managers.UnregisterUserButton;
import interfaces.Database;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

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
		Database db = manager.getDB();
		String[] columnNames = { "First Name", "Last Name", "Sport",
				"# of Years", "Vegetarian" };
		try {
	//	if(db!=null){
			ResultSet users = db.extractUsers();
		if(users!=null){
		ResultSetMetaData rmsd = users.getMetaData();
		users.last();
		Object [][] data = new ResultSet [users.getRow()][8];
			users.beforeFirst();
			int j = 0;
			while (users.next()) {
				for(int i = 0; i < rmsd.getColumnCount(); i ++){
					data[j][i] = users.getString(i);
					System.out.println(rmsd.getColumnCount());
//				new User(users.getString(1), users.getString(2),
//							users.getString(3), users.getString(4), users.getString(5),
//							users.getString(6), users.getInt(7), users.getInt(8));
						}
				j++;
				}
				JTable table = new JTable(data, columnNames);
				add(table.getTableHeader(), BorderLayout.CENTER);
//				table.setEnabled(false);
//				table.setFillsViewportHeight(true);
//				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				JScrollPane scrollPane = new JScrollPane(table);
				add(scrollPane, BorderLayout.SOUTH);
			} 
		}catch (SQLException e) {
			System.out.println("SQL Message i getUser: " + e.getMessage());
		}
		
//		Object[][] data = {
//				{ "Kathy", "Smith", "Snowboarding", new Integer(5),
//						new Boolean(false) },
//				{ "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
//				{ "Sue", "Black", "Knitting", new Integer(2),
//						new Boolean(false) },
//				{ "Jane", "White", "Speed reading", new Integer(20),
//						new Boolean(true) },
//				{ "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
//				{ "Sue", "Black", "Knitting", new Integer(2),
//						new Boolean(false) },
//				{ "Jane", "White", "Speed reading", new Integer(20),
//						new Boolean(true) },
//				{ "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
//				{ "Sue", "Black", "Knitting", new Integer(2),
//						new Boolean(false) },
//				{ "Jane", "White", "Speed reading", new Integer(20),
//						new Boolean(true) },
//				{ "Joe", "Brown", "Pool", new Integer(10), new Boolean(false) } };
		
	}

}
