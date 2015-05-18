package gui.buttons.managers;

import gui.buttons.JModifiedButton;
import gui.panels.FormState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class UnregisterBicycleButton extends JModifiedButton implements
		ActionListener {

	private BicycleGarageManager manager;

	public UnregisterBicycleButton(BicycleGarageManager manager, double size) {
		super("Avregistrera cykel", size);
		this.manager = manager;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		manager.form(FormState.UNREGISTER_BICYCLE);
	}
}
