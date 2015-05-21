package gui.forms.buttons;

import gui.forms.panels.UnregisterBicycleForm;
import gui.misc.buttons.ModifiedButton;
import gui.misc.buttons.ModifiedUserButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class UnregisterBicycleButton extends ModifiedUserButton implements
		ActionListener {

	private BicycleGarageManager manager;

	public UnregisterBicycleButton(BicycleGarageManager manager, double size) {
		super("Avregistrera cykel", size);
		this.manager = manager;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		manager.enable(false);
		new UnregisterBicycleForm(manager);
	}
}
