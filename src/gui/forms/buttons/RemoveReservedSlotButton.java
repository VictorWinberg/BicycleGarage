package gui.forms.buttons;

import gui.forms.panels.RemoveReservedSlotForm;
import gui.misc.buttons.ModifiedUserButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class RemoveReservedSlotButton extends ModifiedUserButton implements ActionListener {

	private BicycleGarageManager manager;

	public RemoveReservedSlotButton(BicycleGarageManager manager, double size) {
		super("Avreservera platser", size);
		this.manager = manager;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (user != null) {
			manager.enable(false);
			new RemoveReservedSlotForm(manager, user);
		}
	}
}
