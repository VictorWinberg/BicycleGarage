package gui.misc.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class NumberOfAvailableSlotsButton extends ModifiedButton implements
		ActionListener {

	private BicycleGarageManager manager;

	public NumberOfAvailableSlotsButton(BicycleGarageManager manager, double size) {
		super("Visa antal lediga platser", size);
		this.manager = manager;
		setToolTipText("Rensar inaktiva användare");
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(null, "Antalet reserverbara platser är "+(300-manager.getDB().getReservedSlots())+".");
	}
}
