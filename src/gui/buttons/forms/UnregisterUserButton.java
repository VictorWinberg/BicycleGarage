package gui.buttons.forms;

import gui.buttons.JModifiedButton;
import gui.panels.forms.UnregisterUserForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class UnregisterUserButton extends JModifiedButton implements
		ActionListener {

	private BicycleGarageManager manager;

	public UnregisterUserButton(BicycleGarageManager manager, double size) {
		super("Avregistrera användare", size);
		this.manager = manager;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		manager.enable(false);
		new UnregisterUserForm(manager);
	}
}
