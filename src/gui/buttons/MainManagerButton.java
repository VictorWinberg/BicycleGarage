package gui.buttons;

import gui.ViewState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class MainManagerButton extends JModifiedButton implements
		ActionListener {

	private BicycleGarageManager manager;

	public MainManagerButton(BicycleGarageManager manager) {
		super("Huvudmeny");
		this.manager = manager;
		setToolTipText("Gå tillbaka till huvudmenyn");
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		manager.changeState(ViewState.START_STATE);
	}
}
