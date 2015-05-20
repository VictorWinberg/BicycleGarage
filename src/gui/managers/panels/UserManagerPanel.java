package gui.managers.panels;

import gui.forms.buttons.EditUserButton;
import gui.forms.buttons.RegisterUserButton;
import gui.forms.buttons.ShowUserButton;
import gui.forms.buttons.UnregisterUserButton;
import gui.misc.buttons.JModifiedButton;
import interfaces.Database;

import java.awt.BorderLayout;
import java.sql.ResultSet;
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
 * Denna panel öppnas när man klickar på “Användare” i operatörsgränssnittet och
 * här visas en tabell på vilka användare som är registrerade i systemet. Det
 * går även att ändra information om användare efter att man har klickat på dem.
 * Denna klass kopplar också panelen till den cykelgaragemanager man anger.
 *
 */
@SuppressWarnings("serial")
public class UserManagerPanel extends JPanel {

	private String[] columnNames = { "Personummer", "Förnamn", "Efternman", "Mail",
			"Telefonnummer", "PIN-kod", "Reserverade platser",
			"Lediga platser" };
	private BicycleGarageManager manager;
	private JPanel northPanel;
	private JPanel southPanel;
	private EditUserButton EButton;
	private ShowUserButton SButton;
	
	/**
	 * Skapar en användarmanagerpanel som hanterar användare
	 * 
	 * @param manager
	 *            cykelgaragemanager
	 */
	public UserManagerPanel(BicycleGarageManager manager) {
		this.manager = manager;
		setBorder(new SoftBevelBorder(1));
		setLayout(new BorderLayout());
		northPanel = new JPanel();
		northPanel.add(new RegisterUserButton(manager, 1.1));
		northPanel.add(new UnregisterUserButton(manager, 1.1));
		southPanel = new JPanel();
		EButton = new EditUserButton(manager, null, 1.1);
		southPanel.add(EButton);
		SButton = new ShowUserButton("Användarens information", 1.1);
		southPanel.add(SButton);
	}
	
	public void update() {
		removeAll();
		add(northPanel, BorderLayout.NORTH);
		add(southPanel, BorderLayout.WEST);
		
		Database db = manager.getDB();
		ResultSet users = db.extractUsers();
		try {
			users.last();
			Object[][] data = new Object[users.getRow()][8];
			users.beforeFirst();
			int j = 0;
			while (users.next()) {
				for (int i = 0; i < 6; i++)
					data[j][i] = users.getString(i + 1);
				data[j][6] = users.getInt(7);
				data[j][7] = users.getInt(8);
				j++;
			}
			JTable table = new JTable(data, columnNames);
			add(table.getTableHeader(), BorderLayout.CENTER);
			table.setCellSelectionEnabled(false);
		    table.setRowSelectionAllowed(true);
		   
		    ListSelectionModel cellSelectionModel = table.getSelectionModel();
		    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
		    	public void valueChanged(ListSelectionEvent e) {
		    		if(!e.getValueIsAdjusting()) {
		    			String selectedData = null;

		    			int[] selectedRow = table.getSelectedRows();
		    			int[] selectedColumns = table.getSelectedColumns();

		    			for (int i = 0; i < selectedRow.length; i++) {
		    				for (int j = 0; j < selectedColumns.length; j++) {
		    					selectedData = (String) table.getValueAt(selectedRow[i], 0);
		    				}
		    			}
		    			User chosen = manager.getDB().getUser(selectedData);
		    			EButton.changeUser(chosen);
		    			SButton.changeUser(chosen);
		    		}
		    	}

		    });
			
			
			
//			table.setEnabled(false);
//			table.setFillsViewportHeight(true);
//			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			JScrollPane scrollPane = new JScrollPane(table);
			add(scrollPane, BorderLayout.CENTER);
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
