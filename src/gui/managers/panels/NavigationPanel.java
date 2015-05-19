package gui.managers.panels;

import gui.JTitle;
import gui.forms.buttons.SearchButton;
import gui.managers.buttons.BicycleManagerButton;
import gui.managers.buttons.MainManagerButton;
import gui.managers.buttons.UserManagerButton;

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
		panel.add(new MainManagerButton(manager, 1.3));
		panel.add(new UserManagerButton(manager, 1.3));
		panel.add(new BicycleManagerButton(manager, 1.3));
		panel.add(new SearchButton(manager, 1.3));
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