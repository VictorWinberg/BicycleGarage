package gui.forms.buttons;

import gui.misc.buttons.JModifiedButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.BicycleGarageManager;
import database.User;

@SuppressWarnings("serial")
public class ShowUserButton extends JModifiedButton implements ActionListener {

	private BicycleGarageManager manager;
	private User user;
	
	public ShowUserButton(BicycleGarageManager manager, double sizeModifier) {
		super("Info", sizeModifier);
		this.manager = manager;
		addActionListener(this);
	}
	
	public void changeUser(User user){
		this.user = user;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("KNAPP");
		String[] labels = { "Personnummer", "Förnamn", "Efternamn",
				"Mailadress", "Telefonnummer", "Reserverade platser", "Lediga platser" };
		String[] fields = new String[labels.length];
		StringBuilder sb = new StringBuilder("Användareinformation");
		for (int i = 0; i < labels.length; i++){
			sb.append("\n" + labels[i] + ": " + fields[i]);
		}
		JOptionPane.showMessageDialog(null, sb.toString(), "Information", JOptionPane.INFORMATION_MESSAGE);
	}
}
