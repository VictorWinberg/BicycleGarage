package gui.forms.panels;

import gui.managers.ViewState;
import interfaces.Database;

import javax.swing.JOptionPane;

import main.BicycleGarageManager;
import database.User;

public class ReserveSlotForm extends Form {
	private BicycleGarageManager manager;

	public ReserveSlotForm(BicycleGarageManager manager) {
		super(manager, "Reservera plats");
		this.manager = manager;
	}

	@Override
	public String[] getLabels() {
		String[] labels = { "Personnummer", "PIN-kod", "Antal platser" };
		return labels;
	}

	@Override
	public int[] getWidths() {
		int[] widths = { 11, 15, 5 };
		return widths;
	}

	private User user;
	private User userPIN;

	@Override
	public boolean check(String[] fields) {
		Database db = manager.getDB();
		user = db.getUser(fields[0]);
		userPIN = db.getUserWithPIN(fields[1]);
		if (!db.isPNRValid(fields[0])) {
			JOptionPane.showMessageDialog(null,
					"Personnumret angavs på fel format");
			return false;
		}
		if (user == null) {
			JOptionPane.showMessageDialog(null,
					"Personnumret är ej kopplat till en användare");
			return false;
		}
		if (userPIN == null) {
			JOptionPane.showMessageDialog(null, "Felaktig PIN-kod");
			return false;
		}
		if (user.equals(userPIN)) {
			return true;
		}
		return false;
	}

	@Override
	public void action(String[] fields) {
		String nbrOfSpots = fields[2];
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
