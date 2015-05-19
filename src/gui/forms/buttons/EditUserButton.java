package gui.forms.buttons;

import gui.forms.panels.EditUserForm;
import gui.misc.buttons.JModifiedButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.BicycleGarageManager;
import database.User;

@SuppressWarnings("serial")
public class EditUserButton extends JModifiedButton implements
		ActionListener {

	private BicycleGarageManager manager;
	private User user;

	public EditUserButton(BicycleGarageManager manager, User user, double size) {
		super("Redigera användare", size);
		this.manager = manager;
		this.user = user;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		manager.enable(false);
		new EditUserForm(manager, user);
	}
}
