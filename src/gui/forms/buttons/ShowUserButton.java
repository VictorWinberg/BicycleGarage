package gui.forms.buttons;

import gui.misc.buttons.ModifiedUserButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class ShowUserButton extends ModifiedUserButton implements ActionListener {

	private BicycleGarageManager manager;

	public ShowUserButton(BicycleGarageManager manager, double sizeModifier) {
		super("Info", sizeModifier);
		this.manager = manager;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String[] labels = { "Personnummer", "Förnamn", "Efternamn", "Mailadress", "Telefonnummer",
				"PIN", "Reserverade platser", "Lediga platser", "Antal cyklar" };
		StringBuilder sb = new StringBuilder("Användareinformation");
		sb.append("\n" + labels[0] + ": " + user.getPersonnr());
		sb.append("\n" + labels[1] + ": " + user.getFirstName());
		sb.append("\n" + labels[2] + ": " + user.getLastName());
		sb.append("\n" + labels[3] + ": " + user.getMail());
		sb.append("\n" + labels[4] + ": " + user.getPhonenr());
		sb.append("\n" + labels[5] + ": " + user.getPIN());
		sb.append("\n" + labels[6] + ": " + user.getReserverdSlots());
		sb.append("\n" + labels[7] + ": " + user.getFreeSlots());
		sb.append("\n" + labels[8] + ": " + user.getNbrOfBicycles());
		JOptionPane.showMessageDialog(null, sb.toString(), "Information",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
