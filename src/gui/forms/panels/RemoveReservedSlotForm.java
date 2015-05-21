package gui.forms.panels;

import gui.managers.ViewState;
import interfaces.Database;

import javax.swing.JOptionPane;

import main.BicycleGarageManager;
import database.User;

public class RemoveReservedSlotForm extends Form {
	private BicycleGarageManager manager;

	public RemoveReservedSlotForm(BicycleGarageManager manager, User user) {
		super(manager, "Avreservera plats");
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
		else if(user.getReserverdSlots() < Integer.parseInt(fields[1])) {
			JOptionPane.showMessageDialog(null, "Användaren har inte så många reserverade platser");
			return false;
		}
		return true;
	}

	@Override
	public void action(String[] fields) {
		int spot = Integer.parseInt(fields[1]);
		db.removeReservedSlot(user, spot);
		JOptionPane.showMessageDialog(null, spot
				+ " plats/er avreserverades");
		manager.changeState(ViewState.USER_STATE);
	}
}
