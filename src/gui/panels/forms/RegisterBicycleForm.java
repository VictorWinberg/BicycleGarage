package gui.panels.forms;

import main.BicycleGarageManager;

public class RegisterBicycleForm extends Form {

	public RegisterBicycleForm(BicycleGarageManager manager) {
		super(manager, "Registrera cykel");
	}

	@Override
	public String[] getLabels() {
		String[] labels = { "Personnummer", "Streckkod", "Mer" };
		return labels;
	}

	@Override
	public int[] getWidths() {
		int[] widths = { 11, 15, 2 };
		return widths;
	}

	@Override
	public void action(String[] fields) {
		System.out.println("Cykelregistrering:");
		for (int i = 0; i < fields.length; i++)
			System.out.println(fields[i]);
	}
}
