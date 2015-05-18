package gui.panels.forms;

import javax.swing.JOptionPane;

import gui.panels.ViewState;
import interfaces.Database;
import database.User;
import main.BicycleGarageManager;

public class RegisterUserForm extends Form {
	private BicycleGarageManager manager;

	public RegisterUserForm(BicycleGarageManager manager) {
		super(manager, "Registrera användare");
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

	@Override
	public boolean action(String[] fields) {
		System.out.println("Användareregistrering:");
		Database db = manager.getDB();
		try {
			User user = db.createUser(fields[0], fields[1], fields[2], fields[3],
					fields[4]);
			db.insertUser(user);
			manager.changeState(ViewState.USER_STATE);
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}
}
