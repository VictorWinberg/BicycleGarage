package gui.forms.buttons;

import gui.forms.panels.ReserveSlotForm;
import gui.misc.buttons.ModifiedUserButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class ReserveSlotButton extends ModifiedUserButton implements ActionListener {

	private BicycleGarageManager manager;

	public ReserveSlotButton(BicycleGarageManager manager, double size) {
		super("Reservera platser", size);
		this.manager = manager;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (user != null) {
			manager.enable(false);
			new ReserveSlotForm(manager, user);
		}
	}
}
