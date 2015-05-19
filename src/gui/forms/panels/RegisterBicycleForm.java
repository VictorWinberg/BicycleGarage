package gui.forms.panels;

import gui.managers.ViewState;
import interfaces.Database;

import javax.swing.JOptionPane;

import main.BicycleGarageManager;
import database.Bicycle;
import database.User;

public class RegisterBicycleForm extends Form {
	
	public RegisterBicycleForm(BicycleGarageManager manager) {
		super(manager, "Registrera cykel");
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
	
	private Bicycle bc;
	
	@Override
	public boolean check(String[] fields) {
		User user = db.getUser(fields[0]);
		User checkUser = db.getUserWithPIN(fields[1]);
		if(user.equals(checkUser)){
			try {
				bc = db.createBicycle(user);
				return true;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} else JOptionPane.showMessageDialog(null, "Felaktig PIN");
		return false;
	}

	@Override
	public void action(String[] fields) {
		db.insertBicycle(bc);
		manager.changeState(ViewState.BICYCLE_STATE);
	}
}
