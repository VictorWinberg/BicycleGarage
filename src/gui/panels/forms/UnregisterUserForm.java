package gui.panels.forms;

import main.BicycleGarageManager;

public class UnregisterUserForm extends Form {

	public UnregisterUserForm(BicycleGarageManager manager) {
		super(manager, "Avregistrera användare");
	}

	@Override
	public String[] getLabels() {
		String[] labels = { "Personnummer", "PIN", "Mer" };
		return labels;
	}

	@Override
	public int[] getWidths() {
		int[] widths = { 11, 4, 2 };
		return widths;
	}

	@Override
	public void action(String[] fields) {
		System.out.println("Användareavregistrering:");
		for (int i = 0; i < fields.length; i++)
			System.out.println(fields[i]);
	}
}
