package gui.buttons;

import gui.ViewState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class VacancyManagerButton extends JModifiedButton implements
		ActionListener {

	private BicycleGarageManager manager;

	public VacancyManagerButton(BicycleGarageManager manager) {
		super("Platser");
		this.manager = manager;
		setToolTipText("Hanterar platser");
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		manager.changeState(ViewState.VACANCY_STATE);
	}
}
