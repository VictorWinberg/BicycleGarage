package gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class ExitButton extends JModifiedButton implements ActionListener {

	public ExitButton(BicycleGarageManager manager) {
		super("Avsluta");
		setToolTipText("Avslutar operatörsgränssnittet");
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}