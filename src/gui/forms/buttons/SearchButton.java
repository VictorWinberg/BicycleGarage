package gui.forms.buttons;

import gui.forms.panels.SearchForm;
import gui.misc.buttons.JModifiedButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class SearchButton extends JModifiedButton implements
		ActionListener {

	private BicycleGarageManager manager;

	public SearchButton(BicycleGarageManager manager, double size) {
		super("Sök", size);
		this.manager = manager;
		setToolTipText("Sökfunktion");
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		manager.enable(false);
		new SearchForm(manager);
	}
}
