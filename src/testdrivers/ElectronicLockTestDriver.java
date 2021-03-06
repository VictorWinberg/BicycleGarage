package testdrivers;

import gui.misc.JTitle;
import interfaces.ElectronicLock;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;

/**
 * Denna klass simulerar ett elektroniskt lås. Den kan användas när
 * BicycleGarageManager testas.
 * 
 * @version 1.1
 * @author Grupp25
 */
public class ElectronicLockTestDriver implements ElectronicLock {

	private JLabel state;
	private Timer timer;

	/**
	 * Skapar en ElectronicLockTestDriver
	 * 
	 * @param doorIdentifier
	 *            en sträng som identifierar dörren, t.ex. "entré" eller
	 *            "utgång"
	 */
	public ElectronicLockTestDriver(String doorIdentifier) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.setMinimumSize(new Dimension(150, 90));
		frame.setLocation(java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getCenterPoint());
		frame.setLocation(frame.getLocation().x - 150, frame.getLocation().y - 400);
		if (doorIdentifier.equals("Utgångslås"))
			frame.setLocation(frame.getLocation().x + 400, frame.getLocation().y);
		JPanel panel = new JPanel();
		frame.add(panel);
		state = new JTitle("LÅST");
		panel.setLayout(new BorderLayout());
		JLabel title = new JLabel(doorIdentifier);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(title, BorderLayout.NORTH);
		panel.add(state, BorderLayout.CENTER);

		frame.pack();
		frame.setVisible(true);

		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // Inner class with
															// code
				state.setText("LÅST"); // to be executed when the timer
				timer.stop(); // event happens
			}
		});
		timer.stop();
	}

	/**
	 * Öppnar låset.
	 * 
	 * @param timeOpen
	 *            den tid det ska vara öppet (s)
	 */
	@Override
	public void open(int timeOpen) {
		state.setText("ÖPPET");
		try {
			timer.setInitialDelay(timeOpen * 1000);
			timer.restart();
		} catch (IllegalArgumentException e) {
			System.err.println("ERROR: IllegalArgumentException i "
					+ "open i ElectronicLockTestDriver");
		}
	}
}
