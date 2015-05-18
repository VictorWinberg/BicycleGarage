package gui.panels.forms;

import interfaces.Database;
import main.BicycleGarageManager;
import database.Bicycle;

public class UnregisterBicycleForm extends Form {
	private BicycleGarageManager manager;

	public UnregisterBicycleForm(BicycleGarageManager manager) {
		super(manager, "Avregistrera cykel");
		this.manager= manager;
	}

	@Override
	public String[] getLabels() {
		String[] labels = { "Streckkod" };
		return labels;
	}

	@Override
	public int[] getWidths() {
		int[] widths = { 11 };
		return widths;
	}

	@Override
	public void action(String[] fields) {
		Database db = manager.getDB();
		Bicycle bc = db.getBicycle(fields[0]);
		db.deleteBicycle(bc);
	}
}
