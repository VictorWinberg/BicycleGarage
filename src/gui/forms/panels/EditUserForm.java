package gui.forms.panels;

import gui.managers.ViewState;

import javax.swing.JOptionPane;

import main.BicycleGarageManager;
import database.User;

public class EditUserForm extends RegisterUserForm {

	public EditUserForm(BicycleGarageManager manager, User user) {
		super(manager, "Redigera användare");
		fields[0].setText(user.getPersonnr());
		fields[1].setText(user.getFirstName());
		fields[2].setText(user.getLastName());
		fields[3].setText(user.getMail());
		fields[4].setText(user.getPhonenr());
	}
	
	@Override
	public boolean check(String[] fields) {
		try {
			user = db.createUser(fields[0], fields[1], fields[2], fields[3], fields[4]);
			if(db.getUser(fields[0]) == null) {
				JOptionPane.showMessageDialog(null, "Användaren finns inte registrerad", "Felmeddelande", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Felmeddelande", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	
	@Override
	public void action(String[] fields) {
		db.updateUser(user);
		manager.changeState(ViewState.USER_STATE);
		JOptionPane.showMessageDialog(null, "Användare uppdaterad");
	}
}
