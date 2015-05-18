package gui.panels.forms;

import main.BicycleGarageManager;

public class UnregisterBicycleForm extends Form {

	public UnregisterBicycleForm(BicycleGarageManager manager) {
		super(manager, "Avregistrera cykel");
	}

	@Override
	public String[] getLabels() {
		String[] labels = { "Personnummer", "Streckkod", "Mer" };
		return labels;
	}

	@Override
	public int[] getWidths() {
		int[] widths = { 11, 4, 2 };
		return widths;
	}

	@Override
	public void action(String[] fields) {
		System.out.println("Cykelavregistrering:");
		for (int i = 0; i < fields.length; i++)
			System.out.println(fields[i]);
	}
}
