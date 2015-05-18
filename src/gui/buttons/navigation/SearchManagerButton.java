package gui.buttons.navigation;

import gui.buttons.JModifiedButton;
import gui.panels.FormState;
import gui.panels.ViewState;

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
		manager.form(FormState.SEARCH_USER);
	}
}
