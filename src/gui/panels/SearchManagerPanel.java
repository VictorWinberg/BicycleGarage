package gui.panels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class SearchManagerPanel extends JPanel {

	public SearchManagerPanel(BicycleGarageManager manager) {
		setBorder(new SoftBevelBorder(1));
		add(new JLabel("Hantera platser osv."));
	}
}
