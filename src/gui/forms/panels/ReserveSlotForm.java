package gui.forms.panels;

import gui.managers.ViewState;
import interfaces.Database;

import javax.swing.JOptionPane;

import main.BicycleGarageManager;
import database.User;

public class ReserveSlotForm extends Form {
	private BicycleGarageManager manager;

	public ReserveSlotForm(BicycleGarageManager manager, User user) {
		super(manager, "Reservera plats");
		this.manager = manager;
		this.user = user;
	}

	@Override
	public String[] getLabels() {
		String[] labels = { "PIN-kod", "Antal platser" };
		return labels;
	}

	@Override
	public int[] getWidths() {
		int[] widths = { 4, 5 };
		return widths;
	}

	@Override
	public boolean check(String[] fields) {
		if(user == null)
			return false;
		User checkUser = db.getUserWithPIN(fields[0]);
		if (checkUser == null || !user.equals(checkUser)) {
			JOptionPane.showMessageDialog(null, "Felaktig PIN-kod");
			return false;
		}
		return true;
	}

	@Override
	public void action(String[] fields) {
		String nbrOfSpots = fields[1];
		int spot = Integer.parseInt(nbrOfSpots);
		if (spot < 0) {
			JOptionPane.showMessageDialog(null,
					"Felaktigt värde på antal platser som ska reserveras");
			return; 
		}
		db.reserveSlot(user, spot);
		JOptionPane.showMessageDialog(null, nbrOfSpots
				+ " plats/er reserverades");
		manager.changeState(ViewState.USER_STATE);
	}
}
