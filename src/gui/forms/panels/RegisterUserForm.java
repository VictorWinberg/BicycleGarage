package gui.forms.panels;

import gui.ViewState;
import interfaces.Database;

import javax.swing.JOptionPane;

import main.BicycleGarageManager;
import database.User;

public class RegisterUserForm extends Form {
	private BicycleGarageManager manager;

	public RegisterUserForm(BicycleGarageManager manager) {
		super(manager, "Registrera användare");
		this.manager = manager;
	}
	
	public RegisterUserForm(BicycleGarageManager manager, String altTitle) {
		super(manager, altTitle);
		this.manager = manager;
	}

	@Override
	public String[] getLabels() {
		String[] labels = { "Personnummer", "Förnamn", "Efternamn",
				"Mailadress", "Telefonnummer" };
		return labels;
	}

	@Override
	public int[] getWidths() {
		int[] widths = { 11, 15, 15, 15, 15 };
		return widths;
	}

	private User user;
	
	@Override
	public boolean check(String[] fields) {
		Database db = manager.getDB();
		try {
			user = db.createUser(fields[0], fields[1], fields[2], fields[3],
					fields[4]);
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}
	
	@Override
	public void action(String[] fields) {
		manager.getDB().insertUser(user);
		manager.changeState(ViewState.USER_STATE);
	}
}
