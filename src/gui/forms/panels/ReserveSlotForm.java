package gui.forms.panels;

import gui.managers.ViewState;

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
	public String[] getDefaultLabels() {
		String[] labels = { "NNNNNN", "NN" };
		return labels;
	}

	@Override
	public int[] getWidths() {
		int[] widths = { 7, 5 };
		return widths;
	}

	private int spots;

	@Override
	public boolean check(String[] fields) {
		if (user == null)
			return false;
		User checkUser = db.getUserWithPIN(fields[0]);
		if (checkUser == null || !user.equals(checkUser)) {
			JOptionPane.showMessageDialog(null, "Felaktig PIN-kod");
			return false;
		}
		try{
			spots = Integer.parseInt(fields[1]);
		}
		catch (NumberFormatException n){
			spots = 0;
		}
		
		if (spots <= 0) {
			JOptionPane.showMessageDialog(null,
					"Felaktigt värde på antal platser som ska reserveras");
			return false;
		}
		if (db.getReservedSlots() + spots > 300) {
			JOptionPane.showMessageDialog(null, "Inte tillräckligt många platser i garaget. "
					+ (300 - db.getReservedSlots()) + " Lediga platser kvar");
			return false;
		}
		return true;
	}

	@Override
	public void action(String[] fields) {

		db.reserveSlot(user, spots);
		if(spots > 1)
			JOptionPane.showMessageDialog(null, spots + " platser reserverades");
		else
			JOptionPane.showMessageDialog(null, spots + " plats reserverades");
		manager.changeState(ViewState.USER_STATE);
	}
}
