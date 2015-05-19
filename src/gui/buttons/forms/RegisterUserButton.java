package gui.buttons.forms;

import gui.buttons.JModifiedButton;
import gui.panels.forms.RegisterUserForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class RegisterUserButton extends JModifiedButton implements
		ActionListener {

	private BicycleGarageManager manager;

	public RegisterUserButton(BicycleGarageManager manager, double size) {
		super("Registrera användare", size);
		this.manager = manager;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		manager.enable(false);
		new RegisterUserForm(manager);
	}
}
