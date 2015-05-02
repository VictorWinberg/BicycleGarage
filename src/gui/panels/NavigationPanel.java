package gui.panels;

import gui.JTitle;
import gui.buttons.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JPanel;

import main.BicycleGarageManager;

@SuppressWarnings("serial")
public class NavigationPanel extends JPanel {

	private JTitle title;

	/**
	 * Skapar en navigeringspanel
	 * 
	 * @param manager
	 *            cykelgaragemanager
	 */
	public NavigationPanel(BicycleGarageManager manager) {
		setLayout(new BorderLayout(0, 10));
		add(new JTitle(), BorderLayout.NORTH);
		title = new JTitle();
		add(title, BorderLayout.CENTER);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(new MainManagerButton(manager));
		panel.add(new UserManagerButton(manager));
		panel.add(new BicycleManagerButton(manager));
		panel.add(new SearchManagerButton(manager));
		add(panel, BorderLayout.SOUTH);
	}

	/**
	 * Ändrar navigeringspanelens titel
	 * 
	 * @param title
	 *            ny titel
	 */
	public void setTitle(String title) {
		this.title.setText(title);
	}
}