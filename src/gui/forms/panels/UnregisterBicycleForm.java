package gui.forms.panels;

import javax.swing.JOptionPane;

import gui.managers.ViewState;
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
	
	private Bicycle bc;
	
	@Override
	public boolean check(String[] fields) {
		bc = manager.getDB().getBicycle(fields[0]);
		if(bc == null) {
			JOptionPane.showMessageDialog(null, "Cykeln med streckkod " + fields[0] + " tillhör ej detta garaget");
			return false;
		}
		if(bc.isDeposited()) {
			JOptionPane.showMessageDialog(null, "Cykeln med streckkod " + fields[0] + " är inlämnad i garaget");
			return false;
		}
		return true;
	}

	@Override
	public void action(String[] fields) {
		db.deleteBicycle(bc);
		manager.changeState(ViewState.BICYCLE_STATE);
		JOptionPane.showMessageDialog(null, "Cykeln är borttagen");
	}
}
