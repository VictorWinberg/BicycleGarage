package gui.forms.panels;

import gui.managers.ViewState;
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
	public void action(String[] fields) {
		User user = new User(fields[0], fields[1], fields[2], fields[3], fields[4], null, -1, -1);
		db.updateUser(user);
		manager.changeState(ViewState.USER_STATE);
	}
}
