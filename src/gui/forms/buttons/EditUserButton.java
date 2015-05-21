package gui.forms.buttons;

import gui.forms.panels.EditUserForm;
import gui.misc.buttons.ModifiedUserButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class EditUserButton extends ModifiedUserButton implements ActionListener {

	private BicycleGarageManager manager;

	public EditUserButton(BicycleGarageManager manager, double size) {
		super("Redigera användare", size);
		this.manager = manager;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (user != null) {
			manager.enable(false);
			new EditUserForm(manager, user);
		}
	}
}
