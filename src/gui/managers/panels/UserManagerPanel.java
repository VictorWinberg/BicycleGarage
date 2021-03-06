package gui.managers.panels;

import gui.forms.buttons.EditUserButton;
import gui.forms.buttons.RegisterBicycleButton;
import gui.forms.buttons.RegisterUserButton;
import gui.forms.buttons.RemoveReservedSlotButton;
import gui.forms.buttons.ReserveSlotButton;
import gui.forms.buttons.ShowBicyclesButton;
import gui.forms.buttons.ShowUserButton;
import gui.forms.buttons.UnregisterUserButton;
import interfaces.Database;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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

	private String[] columnNames = { "Personummer", "Förnamn", "Efternamn", "Reserv", "Lediga",
			"Cyklar" };
	private JPanel westPanel;
	private BicycleGarageManager manager;
	private UnregisterUserButton unregBtn;
	private EditUserButton editBtn;
	private ShowUserButton showBtn;
	private RegisterBicycleButton regBicBtn;
	private ReserveSlotButton resBtn;
	private RemoveReservedSlotButton remResBtn;
	private ShowBicyclesButton showBicBtn;

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
		
		westPanel.add(new RegisterUserButton(manager, 1.1));
		
		westPanel.setLayout(new GridLayout(10, 1, 1, 1));
		unregBtn = new UnregisterUserButton(manager, 1.1);
		westPanel.add(unregBtn);
		editBtn = new EditUserButton(manager, 1.1);
		westPanel.add(editBtn);
		showBtn = new ShowUserButton(1.1);
		westPanel.add(showBtn);
		showBicBtn = new ShowBicyclesButton(manager, 1.1);
		westPanel.add(showBicBtn);
		regBicBtn = new RegisterBicycleButton(manager, 1.1);
		westPanel.add(regBicBtn);
		resBtn = new ReserveSlotButton(manager, 1.1);
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
			Object[][] data = new Object[users.getRow()][6];
			users.beforeFirst();
			int j = 0;
			while (users.next()) {
				for (int i = 0; i < 3; i++)
					data[j][i] = users.getString(i + 1);
				for(int i = 3; i < 6; i++)
					data[j][i] = users.getInt(i + 4);
				j++;
			}
			JTable table = new JTable(data, columnNames) {
			    @Override
			    public boolean isCellEditable(int row, int column) {
			        return false;
			    }
			};
			table.getTableHeader().setReorderingAllowed(false);
			table.setCellSelectionEnabled(false);
			table.setRowSelectionAllowed(true);
			table.getColumnModel().getColumn(3).setPreferredWidth(10);
			table.getColumnModel().getColumn(4).setPreferredWidth(10);
			table.getColumnModel().getColumn(5).setPreferredWidth(10);

			ListSelectionModel cellSelectionModel = table.getSelectionModel();
			cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (!e.getValueIsAdjusting() && table.getSelectedRow() >= 0) {
						String selectedData = (String) table.getValueAt(table.getSelectedRow(), 0);
						User chosen = manager.getDB().getUser(selectedData);
						
						unregBtn.changeUser(chosen);
						editBtn.changeUser(chosen);
						showBtn.changeUser(chosen);
						regBicBtn.changeUser(chosen);
						resBtn.changeUser(chosen);
						remResBtn.changeUser(chosen);
						showBicBtn.changeUser(chosen);
					}
				}
			});

			JScrollPane scrollPane = new JScrollPane(table);
			add(scrollPane, BorderLayout.CENTER);
			
			TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
			table.setRowSorter(rowSorter);
			
			jtfFilter = new JTextField();
			
			JPanel panel = new JPanel(new BorderLayout());
	        panel.add(new JLabel("Sök:"),
	                BorderLayout.WEST);
	        panel.add(jtfFilter, BorderLayout.CENTER);
			
	        add(panel, BorderLayout.SOUTH);
	        
	        jtfFilter.getDocument().addDocumentListener(new DocumentListener(){

	            @Override
	            public void insertUpdate(DocumentEvent e) {
	                String text = jtfFilter.getText();

	                if (text.trim().length() == 0) {
	                    rowSorter.setRowFilter(null);
	                } else {
	                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
	                }
	            }

	            @Override
	            public void removeUpdate(DocumentEvent e) {
	                String text = jtfFilter.getText();

	                if (text.trim().length() == 0) {
	                    rowSorter.setRowFilter(null);
	                } else {
	                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
	                }
	            }

	            @Override
	            public void changedUpdate(DocumentEvent e) {
	                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	            }

	        });

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private JTextField jtfFilter;
	
	public void setText(String text){
		jtfFilter.setText(text);
}}
