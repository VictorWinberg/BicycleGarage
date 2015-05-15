package gui.buttons.managers;

import gui.buttons.JModifiedButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class ClearInactiveUsers extends JModifiedButton implements
		ActionListener {

	private BicycleGarageManager manager;

	public ClearInactiveUsers(BicycleGarageManager manager, double size) {
		super("Rensa", size);
		this.manager = manager;
		setToolTipText("Rensar inaktiva användare");
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object[] options = { "Ja, rensa", "Nej" };
		switch (JOptionPane.showOptionDialog(null, "Rensa inaktiva användare?",
				"Rensa", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0])) {
		case 0:
			if(manager.getDB().clearInactiveUsers())
				JOptionPane.showMessageDialog(null, "Inaktiva användare borttagna", "Rensa", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Inga inaktiva användare", "Rensa", JOptionPane.WARNING_MESSAGE);
		}
	}
}
