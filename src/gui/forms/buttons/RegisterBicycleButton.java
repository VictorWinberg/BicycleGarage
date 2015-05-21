package gui.forms.buttons;

import gui.forms.panels.RegisterBicycleForm;
import gui.misc.buttons.ModifiedUserButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class RegisterBicycleButton extends ModifiedUserButton implements
		ActionListener {

	private BicycleGarageManager manager;

	public RegisterBicycleButton(BicycleGarageManager manager, double size) {
		super("Registrera cykel", size);
		this.manager = manager;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(user != null) {
			manager.enable(false);
			new RegisterBicycleForm(manager, user);
		}
	}
}
