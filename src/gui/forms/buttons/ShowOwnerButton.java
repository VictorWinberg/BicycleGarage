package gui.forms.buttons;


import gui.managers.ViewState;
import gui.misc.buttons.ModifiedUserButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class ShowOwnerButton extends ModifiedUserButton implements ActionListener {

	private BicycleGarageManager manager;

	public ShowOwnerButton(BicycleGarageManager manager, double size){
		super("Visa ägare", size);
		this.manager = manager;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (user != null) {
			manager.changeState(ViewState.USER_STATE);
			manager.setOwnerSearchTo(user.getPersonnr());			
		}
}}

