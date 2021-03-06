package gui.forms.buttons;

import gui.managers.ViewState;
import gui.misc.buttons.ModifiedButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.BicycleGarageManager;
import database.Bicycle;

@SuppressWarnings("serial")
public class UnregisterBicycleButton extends ModifiedButton implements ActionListener {

	private BicycleGarageManager manager;

	public UnregisterBicycleButton(BicycleGarageManager manager, double size) {
		super("Avregistrera cykel", size);
		this.manager = manager;
		addActionListener(this);
	}

	private Bicycle bicycle;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (bicycle == null)
			return;
		if (bicycle.isDeposited()) {
			JOptionPane.showMessageDialog(null, "Cykeln med streckkod " + bicycle.getBarcode()
					+ " är inlämnad i garaget");
			return;
		}
		String options[] = { "Ja, avregistrera", "Nej" };
		if (JOptionPane.showOptionDialog(null,
				"Är du säker på att du vill avregistrera cykeln " + bicycle.getBarcode() + "?", "Säkerhetsfråga",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
				options[0]) == JOptionPane.YES_OPTION) {
			manager.enable(false);
			manager.getDB().deleteBicycle(bicycle);
			manager.changeState(ViewState.BICYCLE_STATE);
			if(bicycle.getOwner() == null)
				JOptionPane.showMessageDialog(null, "Cykeln med streckkod " + bicycle.getBarcode() + " borttagen");
			else
				JOptionPane.showMessageDialog(null, "Cykeln med streckkod " + bicycle.getBarcode() + " med ägare " + bicycle.getOwner().getFirstName() + " borttagen");
			manager.enable(true);
		}
	}
	
	public void changeBicycle(Bicycle bicycle) {
		this.bicycle = bicycle;
	}
}
