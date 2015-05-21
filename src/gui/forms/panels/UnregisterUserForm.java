package gui.forms.panels;

import gui.managers.ViewState;

import javax.swing.JOptionPane;

import main.BicycleGarageManager;
import database.User;

public class UnregisterUserForm extends Form {

	public UnregisterUserForm(BicycleGarageManager manager, User user) {
		super(manager, "Avregistrera användare");
		this.user = user;
	}

	@Override
	public String[] getLabels() {
		String[] labels = { "PIN" };
		return labels;
	}

	@Override
	public int[] getWidths() {
		int[] widths = { 4 };
		return widths;
	}
	
	@Override
	public boolean check(String[] fields) {
		if(user == null)
			return false;
		User checkUser = db.getUserWithPIN(fields[0]);
		if(checkUser == null || !user.equals(checkUser)) {
			JOptionPane.showMessageDialog(null, "Felaktig PIN-kod", "Felmeddelande", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else if(!db.getBicycles(user).isEmpty()) {
			JOptionPane.showMessageDialog(null, "Användaren har inlämnade cyklar", "Felmeddelande", JOptionPane.WARNING_MESSAGE);
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
