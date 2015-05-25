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
		String[] labels = { "PIN-kod" };
		return labels;
	}

	@Override
	public String[] getDefaultLabels() {
		String[] labels = { "NNNNNN" };
		return labels;
	}

	@Override
	public int[] getWidths() {
		int[] widths = { 7 };
		return widths;
	}

	@Override
	public boolean check(String[] fields) {
		if (user == null)
			return false;
		User checkUser = db.getUserWithPIN(fields[0]);
		if (checkUser == null || !user.equals(checkUser)) {
			JOptionPane.showMessageDialog(null, "Felaktig PIN-kod", "Felmeddelande",
					JOptionPane.WARNING_MESSAGE);
			return false;
		} else if (user.getReserverdSlots()>0){
			JOptionPane.showMessageDialog(null, "Användaren har reserverade platser", "Felmeddelande",
					JOptionPane.WARNING_MESSAGE);
			return false;
		} 
		return true;
	}

	@Override
	public void action(String[] fields) {
		for (database.Bicycle a : db.getBicycles(user)){
			db.deleteBicycle(a);
		}
		db.deleteUser(user);
		manager.changeState(ViewState.USER_STATE);
		JOptionPane.showMessageDialog(null, "Användaren är borttagen");
	}
}
