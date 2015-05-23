package gui.misc.buttons;

import interfaces.Database;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class MasterResetButton extends ModifiedButton implements ActionListener {

	private BicycleGarageManager manager;

	public MasterResetButton(BicycleGarageManager manager, double size) {
		super("RESET", size);
		this.manager = manager;
		setToolTipText("Återställer systemet");
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object[] options = { "Ja, återställ", "Nej" };
		switch (JOptionPane.showOptionDialog(null,
				"Är du säker på att du vill återställa hela systemet?", "Säkerhetsfråga",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])){
		case 0:
			Database db = manager.getDB();
			db.createTables();
			db.dropTables();
			JOptionPane.showMessageDialog(null, "Systemet återställt",
						"Meddelande", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
