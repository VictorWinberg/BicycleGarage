package gui.buttons.navigation;

import gui.buttons.JModifiedButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class ExitButton extends JModifiedButton implements ActionListener {

	public ExitButton(BicycleGarageManager manager, double size) {
		super("Avsluta", size);
		setToolTipText("Avslutar operatörsgränssnittet");
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
