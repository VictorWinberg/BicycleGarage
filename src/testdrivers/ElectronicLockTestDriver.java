package testdrivers;

import interfaces.ElectronicLock;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Timer;

import java.awt.event.*;

/**
 * This class simulates an electronic lock. 
 * It can be used when BicycleGarageManager is tested.
 * 
 * @version 1.1
 * @author Martin Host 
 */
public class ElectronicLockTestDriver implements ElectronicLock {
	
	private JLabel state = new JLabel("LOCKED");
	private Timer timer;
	
	/**
	 * Create an ElectronicLockTestDriver
	 * @param doorIdentifier a string identifying the door, e.g. "entry" or "exit"
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
	 *  Open the lock.
	 *  @param timeOpen time it should be open (s) 
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
