package gui.forms.panels;

import gui.managers.ViewState;

import javax.swing.JOptionPane;

import main.BicycleGarageManager;
import database.User;

public class RegisterUserForm extends Form {

	public RegisterUserForm(BicycleGarageManager manager) {
		super(manager, "Registrera användare");
	}
	
	public RegisterUserForm(BicycleGarageManager manager, String altTitle) {
		super(manager, altTitle);
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

	protected User user;
	
	@Override
	public boolean check(String[] fields) {
		try {
			user = db.createUser(fields[0], fields[1], fields[2], fields[3], fields[4]);
			if(db.getUser(fields[0]) != null) {
				JOptionPane.showMessageDialog(null, "Användaren är redan registrerad", "Felmeddelande", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Felmeddelande", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	@Override
	public void action(String[] fields) {
		db.insertUser(user);
		manager.changeState(ViewState.USER_STATE);
		JOptionPane.showMessageDialog(null, "Ny användare skapad med PIN-koden " + user.getPIN());
	}
}
