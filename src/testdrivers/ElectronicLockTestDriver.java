package testdrivers;

import interfaces.ElectronicLock;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Timer;

import java.awt.event.*;

/**
 * Denna klass simulerar ett elektroniskt l�s.
�* Den kan anv�ndas n�r BicycleGarageManager testas.
 * 
 * @version 1.1
 * @author Martin Host 
 */
public class ElectronicLockTestDriver implements ElectronicLock {
	
	private JLabel state = new JLabel("LOCKED");
	private Timer timer;
	
	/**
	 * Skapa ett ElectronicLockTestDriver
	 * @param doorIdentifier en str�ng som identifierar d�rren, t.ex. "entr�" eller "utg�ng"
	 */
	public ElectronicLockTestDriver(String doorIdentifier) {
		JFrame frame = new JFrame(doorIdentifier);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JPanel panel = new JPanel();
		frame.add(panel);
		panel.add(state);
		
		frame.pack();
		frame.setVisible(true);
		
		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {  	// Inner class with code
				state.setText("LOCKED");					// to be executed when the timer
				timer.stop();								// event happens
			}
		});
		timer.stop();
	}

	/**
	 *  �ppnar l�set.
	 *  @param timeOpen den tid det ska vara �ppet (s) 
	 */
	public void open(int timeOpen) {
		state.setText("OPEN");
		try {
			timer.setInitialDelay(timeOpen*1000);
			timer.restart();
		} catch (IllegalArgumentException e) {
			System.err.println("ERROR: IllegalArgumentException in " +
					"open in ElectronicLockTestDriver");
		}
	}
}
