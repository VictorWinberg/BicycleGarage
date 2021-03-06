package gui.misc.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class ClearInactiveUsersButton extends ModifiedButton implements ActionListener {

	private BicycleGarageManager manager;

	public ClearInactiveUsersButton(BicycleGarageManager manager, double size) {
		super("Rensa", size);
		this.manager = manager;
		setToolTipText("Tar bort inaktiva användare");
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object[] options = { "Ja, rensa", "Nej" };
		switch (JOptionPane.showOptionDialog(null,
				"Är du säker på att du vill rensa inaktiva användare?", "Säkerhetsfråga",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])) {
		case 0:
			if (manager.getDB().clearInactiveUsers())
				JOptionPane.showMessageDialog(null, "Inaktiva användare borttagna",
						"Rensning av inaktiva användare", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Inga inaktiva användare",
						"Rensning av inaktiva användare", JOptionPane.WARNING_MESSAGE);
		}
	}
}
