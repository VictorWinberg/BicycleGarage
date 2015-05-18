package gui.buttons.navigation;

import gui.ViewState;
import gui.buttons.JModifiedButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class SearchManagerButton extends JModifiedButton implements
		ActionListener {

	private BicycleGarageManager manager;

	public SearchManagerButton(BicycleGarageManager manager, double size) {
		super("Sök", size);
		this.manager = manager;
		setToolTipText("Sökfunktion");
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		manager.changeState(ViewState.SEARCH_STATE);
	}
}