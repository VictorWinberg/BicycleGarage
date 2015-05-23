package gui.forms.panels;

import gui.managers.ViewState;

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
					"Felaktigt värde på antal platser som ska avreserveras");
			return false;
		}
		if (user.getFreeSlots() < spots) {
			JOptionPane.showMessageDialog(null, "Användaren har endast "+user.getFreeSlots()+" lediga platser");
			return false;
		}
		return true;
	}

	@Override
	public void action(String[] fields) {
		db.removeReservedSlot(user, spots);
		if(spots > 1)
			JOptionPane.showMessageDialog(null, spots + " platser avreserverades");
		else
			JOptionPane.showMessageDialog(null, spots + " plats avreserverades");
		manager.changeState(ViewState.USER_STATE);
	}
}
