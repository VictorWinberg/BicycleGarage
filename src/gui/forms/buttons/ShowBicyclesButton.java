package gui.forms.buttons;


import gui.managers.ViewState;
import gui.misc.buttons.ModifiedUserButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class ShowBicyclesButton extends ModifiedUserButton implements ActionListener {

	private BicycleGarageManager manager;

	public ShowBicyclesButton(BicycleGarageManager manager, double size){
		super("Visa cyklar", size);
		this.manager = manager;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (user != null) {
			manager.changeState(ViewState.BICYCLE_STATE);
			manager.setBicycleSearchTo(user.getPersonnr());			
		}
}}

