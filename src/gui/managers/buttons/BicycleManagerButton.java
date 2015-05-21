package gui.managers.buttons;

import gui.managers.ViewState;
import gui.misc.buttons.ModifiedButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class BicycleManagerButton extends ModifiedButton implements ActionListener {

	private BicycleGarageManager manager;

	public BicycleManagerButton(BicycleGarageManager manager, double size) {
		super("Cyklar", size);
		this.manager = manager;
		setToolTipText("Hanterar cyklar");
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		manager.changeState(ViewState.BICYCLE_STATE);
	}
}
