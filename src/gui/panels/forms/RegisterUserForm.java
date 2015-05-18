package gui.panels.forms;

import main.BicycleGarageManager;

public class RegisterUserForm extends Form {

	public RegisterUserForm(BicycleGarageManager manager) {
		super(manager, "Registrera användare");
	}

	@Override
	public String[] getLabels() {
		String[] labels = { "Personnummer", "Förnamn", "Efternamn", "Mer" };
		return labels;
	}

	@Override
	public int[] getWidths() {
		int[] widths = { 11, 15, 15, 2 };
		return widths;
	}

	@Override
	public void action(String[] fields) {
		System.out.println("Användareregistrering:");
		for (int i = 0; i < fields.length; i++)
			System.out.println(fields[i]);
	}
}
