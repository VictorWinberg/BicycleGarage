package gui.panels.forms;

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
	public void action(String[] fields) {
		System.out.println("Användareregistrering:");
		Database db = manager.getDB();
		// for (int i = 0; i < fields.length; i++)
		User user = db.createUser(fields[0], fields[1], fields[2], fields[3],
				fields[4]);
		db.insertUser(user);
		//System.out.println(fields[i]);
	}
}
