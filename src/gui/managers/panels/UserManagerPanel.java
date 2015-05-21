package gui.managers.panels;

import gui.forms.buttons.EditUserButton;
import gui.forms.buttons.RegisterBicycleButton;
import gui.forms.buttons.RegisterUserButton;
import gui.forms.buttons.RemoveReservedSlotButton;
import gui.forms.buttons.ReserveSlotButton;
import gui.forms.buttons.ShowUserButton;
import gui.forms.buttons.UnregisterBicycleButton;
import gui.forms.buttons.UnregisterUserButton;
import interfaces.Database;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.BicycleGarageManager;
import database.User;

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
	private JPanel westPanel;
	private UnregisterUserButton unregBtn;
	private EditUserButton editBtn;
	private ShowUserButton showBtn;
	private RegisterBicycleButton regBicBtn;
	private UnregisterBicycleButton unregBicBtn;
	private ReserveSlotButton resBtn;
	private RemoveReservedSlotButton remResBtn;
	
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
		westPanel = new JPanel();
		westPanel.setLayout(new GridLayout(10, 1, 1, 1));
		unregBtn = new UnregisterUserButton(manager, 1.1);
		westPanel.add(unregBtn);
		editBtn = new EditUserButton(manager, 1.1);
		westPanel.add(editBtn);
		showBtn = new ShowUserButton(manager, 1.1);
		westPanel.add(showBtn);
		regBicBtn = new RegisterBicycleButton(manager, 1.1);
		westPanel.add(regBicBtn);
		unregBicBtn = new UnregisterBicycleButton(manager, 1.1);
		westPanel.add(unregBicBtn);
		resBtn = new ReserveSlotButton(manager,1.1);
		westPanel.add(resBtn);
		remResBtn = new RemoveReservedSlotButton(manager, 1.1);
		westPanel.add(remResBtn);
	}
	
	public void update() {
		removeAll();
		add(westPanel, BorderLayout.WEST);
		
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
		    			unregBtn.changeUser(chosen);
		    			editBtn.changeUser(chosen);
		    			showBtn.changeUser(chosen);
		    			regBicBtn.changeUser(chosen);
		    			resBtn.changeUser(chosen);
		    			remResBtn.changeUser(chosen);
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
