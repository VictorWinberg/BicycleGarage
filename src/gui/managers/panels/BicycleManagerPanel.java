package gui.managers.panels;

import gui.forms.buttons.RegisterBicycleButton;
import gui.forms.buttons.ShowOwnerButton;
import gui.forms.buttons.UnregisterBicycleButton;
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
	private ShowOwnerButton showOwnBtn;
	private UnregisterBicycleButton unregBicBtn;

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
		showOwnBtn = new ShowOwnerButton(manager, 1.1);
		westPanel.add(showOwnBtn);
		unregBicBtn = new UnregisterBicycleButton(manager, 1.1);
		westPanel.add(unregBicBtn);
	}

	public void update() {
		removeAll();
		add(westPanel, BorderLayout.WEST);

		Database db = manager.getDB();
		try {
			ResultSet bicycles = db.extractBicycles();
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
			table.getTableHeader().setReorderingAllowed(false);
			
			ListSelectionModel cellSelectionModel = table.getSelectionModel();
			cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					if (!e.getValueIsAdjusting()  && table.getSelectedRow() >= 0) {
						Database db = manager.getDB();
						String userData = (String) table.getValueAt(table.getSelectedRow(), 1);
						regBicBtn.changeUser(db.getUser(userData));
						showOwnBtn.changeUser(db.getUser(userData));
						String bicycleData = (String) table.getValueAt(table.getSelectedRow(), 0);
						unregBicBtn.changeBicycle(db.getBicycle(bicycleData));
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
		
	}
}
