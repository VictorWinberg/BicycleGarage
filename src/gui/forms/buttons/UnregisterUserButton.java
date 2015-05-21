package gui.forms.buttons;

import gui.forms.panels.UnregisterUserForm;
import gui.misc.buttons.ModifiedButton;
import gui.misc.buttons.ModifiedUserButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class UnregisterUserButton extends ModifiedUserButton implements
		ActionListener {

	private BicycleGarageManager manager;

	public UnregisterUserButton(BicycleGarageManager manager, double size) {
		super("Avregistrera användare", size);
		this.manager = manager;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(user != null) {
			manager.enable(false);
			new UnregisterUserForm(manager, user);
		}
	}
}
