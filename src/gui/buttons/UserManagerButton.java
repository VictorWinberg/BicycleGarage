package gui.buttons;

import gui.ViewState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class UserManagerButton extends JModifiedButton implements
		ActionListener {

	private BicycleGarageManager manager;

	public UserManagerButton(BicycleGarageManager manager) {
		super("Visa användare");
		this.manager = manager;
		setToolTipText("Hanterar användare");
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		manager.changeState(ViewState.USER_STATE);
	}
}
