package gui.buttons.navigation;

import gui.buttons.JModifiedButton;
import gui.panels.ViewState;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class UserManagerButton extends JModifiedButton implements
		ActionListener {

	private BicycleGarageManager manager;

	public UserManagerButton(BicycleGarageManager manager, double size) {
		super("Visa användare", size);
		this.manager = manager;
		setToolTipText("Hanterar användare");
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		manager.changeState(ViewState.USER_STATE);
	}
}
