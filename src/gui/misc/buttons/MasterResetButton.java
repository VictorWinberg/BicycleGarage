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
		super("Nollställ databas", size);
		this.manager = manager;
		setToolTipText("Tar bort all information lagrad i databasen.");
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object[] options = { "Ja, återställ", "Nej" };
		switch (JOptionPane.showOptionDialog(null,
				"All information lagrad i databasen kommer tas bort, är du säker på att du vill nollställa hela systemet?", "Säkerhetsfråga",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0])){
		case 0:
			String submittedCode = JOptionPane.showInputDialog("Ange operatörskoden.");
			if (submittedCode == null){
			}
			else if (submittedCode.equals("133337")){
			Database db = manager.getDB();
			db.dropTables();
			db.createTables();
			JOptionPane.showMessageDialog(null, "Systemet är nu återställt",
						"Meddelande", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, "Felaktig operatörskod.", "Felmeddelande", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
