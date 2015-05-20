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

/**
 * Denna klass simulerar ett elektroniskt lås. Den kan användas när
 * BicycleGarageManager testas.
 * 
 * @version 1.1
 * @author Martin Host
 */
public class ElectronicLockTestDriver implements ElectronicLock {

	private JLabel state;
	private Timer timer;

	/**
	 * Skapa ett ElectronicLockTestDriver
	 * 
	 * @param doorIdentifier
	 *            en sträng som identifierar dörren, t.ex. "entré" eller
	 *            "utgång"
	 */
	public ElectronicLockTestDriver(String doorIdentifier) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setMinimumSize(new Dimension(100, 100));
		frame.setLocation(516,30);
		if(doorIdentifier.equals("Entrélås")){
			frame.setLocation(677,30);
		}
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
	
	public static void main(String[] args) {
		new ElectronicLockTestDriver("Entrélås");
	}
}
