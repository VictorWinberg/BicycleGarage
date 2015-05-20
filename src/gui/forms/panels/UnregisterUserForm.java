package gui.forms.panels;

import javax.swing.JOptionPane;

import gui.managers.ViewState;
import interfaces.Database;
import main.BicycleGarageManager;
import database.User;

public class UnregisterUserForm extends Form {

	public UnregisterUserForm(BicycleGarageManager manager) {
		super(manager, "Avregistrera användare");
	}

	@Override
	public String[] getLabels() {
		String[] labels = { "Personnummer", "PIN" };
		return labels;
	}

	@Override
	public int[] getWidths() {
		int[] widths = { 11, 4 };
		return widths;
	}
	
	private User user;
	
	@Override
	public boolean check(String[] fields) {
		try {
			db.createUser(fields[0], "firstname", "lastname", "name@example.com", "0707001122");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Felmeddelande", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if(db.getUser(fields[0]) == null) {
			JOptionPane.showMessageDialog(null, "Personnumret saknar användare", "Felmeddelande", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		User checkUser = db.getUserWithPIN(fields[1]);
		if(!user.equals(checkUser)) {
			JOptionPane.showMessageDialog(null, "Felaktig PIN-kod", "Felmeddelande", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	@Override
	public void action(String[] fields) {
		db.deleteUser(user);
		manager.changeState(ViewState.USER_STATE);
		JOptionPane.showMessageDialog(null, "Användaren är borttagen");
	}
}
