package gui.panels;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class MainManagerPanel extends JPanel {

	public MainManagerPanel(BicycleGarageManager manager) {
		setBorder(new SoftBevelBorder(1));
		add(new JLabel("Välkommen till cykelgaraget!"));
	}
}