package gui.managers.buttons;

import gui.managers.ViewState;
import gui.misc.buttons.ModifiedButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class MainManagerButton extends ModifiedButton implements ActionListener {

	private BicycleGarageManager manager;

	public MainManagerButton(BicycleGarageManager manager, double size) {
		super("Huvudmeny", size);
		this.manager = manager;
		setToolTipText("Gå tillbaka till huvudmenyn");
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		manager.changeState(ViewState.START_STATE);
	}
}
