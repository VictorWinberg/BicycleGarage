package gui.forms.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import database.User;
import gui.misc.buttons.JModifiedButton;

@SuppressWarnings("serial")
public class ShowUserButton extends JModifiedButton implements ActionListener {

	private User user;
	
	public ShowUserButton(String text, double sizeModifier) {
		super(text, sizeModifier);
	}
	
	public void changeUser(User user){
		this.user = user;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
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
