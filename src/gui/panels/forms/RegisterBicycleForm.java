package gui.panels.forms;

import javax.swing.JOptionPane;

import gui.panels.ViewState;
import interfaces.Database;
import main.BicycleGarageManager;
import database.Bicycle;
import database.User;

public class RegisterBicycleForm extends Form {
	private BicycleGarageManager manager;
	
	public RegisterBicycleForm(BicycleGarageManager manager) {
		super(manager, "Registrera cykel");
		this.manager = manager;
	}

	@Override
	public String[] getLabels() {
		String[] labels = { "Personnummer", "PIN-kod"};
		return labels;
	}

	@Override
	public int[] getWidths() {
		int[] widths = { 11, 15};
		return widths;
	}

	@Override
	public boolean action(String[] fields) {
		Database db = manager.getDB();
		User user = db.getUser(fields[0]);
		User userPIN = db.getUserWithPIN(fields[1]);
		if(user.equals(userPIN)){
			try {
				Bicycle bc = db.createBicycle(user);
				db.insertBicycle(bc);
				manager.changeState(ViewState.BICYCLE_STATE);
				return true;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} else JOptionPane.showMessageDialog(null, "Felaktig PIN");
		return false;
	}
}
