package gui.buttons;

import gui.ViewState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class BicycleManagerButton extends JModifiedButton implements
		ActionListener {

	private BicycleGarageManager manager;

	public BicycleManagerButton(BicycleGarageManager manager) {
		super("Cyklar");
		this.manager = manager;
		setToolTipText("Hanterar cyklar");
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		manager.changeState(ViewState.BICYCLE_STATE);
	}
}
