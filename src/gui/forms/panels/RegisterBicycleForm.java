package gui.forms.panels;

import gui.managers.ViewState;

import javax.swing.JOptionPane;

import main.BicycleGarageManager;
import database.Bicycle;
import database.User;

public class RegisterBicycleForm extends Form {
	
	public RegisterBicycleForm(BicycleGarageManager manager) {
		super(manager, "Registrera cykel");
	}

	@Override
	public String[] getLabels() {
		String[] labels = { "Personnummer", "PIN-kod"};
		return labels;
	}

	@Override
	public int[] getWidths() {
		int[] widths = { 11, 15};
		return widths;
	}
	
	private Bicycle bc;
	
	@Override
	public boolean check(String[] fields) {
		if(!db.isPNRValid(fields[0])) {
			JOptionPane.showMessageDialog(null, "Personnumret angavs på fel format", "Felmeddelande", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		User user = db.getUser(fields[0]);
		if(user == null) {
			JOptionPane.showMessageDialog(null, "Personnumret är ej kopplat till en användare", "Felmeddelande", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		User checkUser = db.getUserWithPIN(fields[1]);
		if(!user.equals(checkUser)){
			JOptionPane.showMessageDialog(null, "Felaktig PIN", "Felmeddelande", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		try {
			bc = db.createBicycle(user);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Felmeddelande", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	@Override
	public void action(String[] fields) {
		db.insertBicycle(bc);
		manager.changeState(ViewState.BICYCLE_STATE);
		JOptionPane.showMessageDialog(null, bc.getOwner().getFirstName() + " har cykel med streckkod: " + bc.getBarcode());
		manager.printBarcode(bc.getBarcode());
	}
}
