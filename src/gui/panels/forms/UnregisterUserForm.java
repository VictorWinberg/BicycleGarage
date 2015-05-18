package gui.panels.forms;

import gui.panels.ViewState;
import interfaces.Database;

import java.sql.ResultSet;

import main.BicycleGarageManager;
import database.User;

public class UnregisterUserForm extends Form {
	private BicycleGarageManager manager;

	public UnregisterUserForm(BicycleGarageManager manager) {
		super(manager, "Avregistrera användare");
		this.manager = manager;
	}

	@Override
	public String[] getLabels() {
		String[] labels = { "Personnummer", "PIN" };
		return labels;
	}

	@Override
	public int[] getWidths() {
		int[] widths = { 11, 4 };
		return widths;
	}

	@Override
	public void action(String[] fields) {
		Database db = manager.getDB();
		User user = db.getUser(fields[0]);
		User user1 = db.getUserWithPIN(fields[1]);
		if(user.equals(user1));
		db.deleteUser(user);
		manager.changeState(ViewState.USER_STATE);
	}
}
