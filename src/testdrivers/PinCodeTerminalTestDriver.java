package testdrivers;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import interfaces.PinCodeTerminal;
import main.BicycleGarageManager;

/**
 * Denna klass simulerar en pinkodsterminal.
 * Den kan användas när BicycleGarageManager testas.
 * 
 * @version 1.0
 * @author Martin Höst 
 */
public class PinCodeTerminalTestDriver implements PinCodeTerminal, ActionListener {
	
	private BicycleGarageManager manager = null;
	private static final String[] KEYS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "#", "0", "*"}; 
	private JLabel redLabel, greenLabel;
	private Timer redTimer, greenTimer;

	/**
	 * Registrerar cykelgarage manager så att pinkodsterminalen
	 * vet vilken manager som ska anropas när en användare har
	 * tryckt en tangent.
	 * @param manager cykelgarage manager
	 */
	public void register(BicycleGarageManager manager) {
		this.manager = manager;
	}

	/** 
	 * Slå på LED i lightTime sekunder.
	 * @param colour PinCodeTerminal.RED_LED eller PinCodeTerminal.GREEN_LED 
	 * @param lightTime Slå på LED i lightTime sekunder
	 */
	public void lightLED(int colour, int lightTime) {
		try {
			if (colour == RED_LED) {
				redLabel.setText("RED");	
				redTimer.setInitialDelay(lightTime*1000);
				redTimer.restart();
			}
			if (colour == GREEN_LED) {
				greenLabel.setText("GREEN");
				greenTimer.setInitialDelay(lightTime*1000);
				greenTimer.restart();
			}	
		} catch (IllegalArgumentException e) {
			System.err.println("ERROR: IllegalArgumentException in lightLED " + "" +
					"in PinCodeTerminalTestDriver");	
		}
	}
	
	/** 
	 * Skapar en PinCodeTestDriver.
	 */
	public PinCodeTerminalTestDriver() {
		JFrame frame = new JFrame("Pin code terminal");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		          // make the window impossible to close
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel LEDPanel = new JPanel();
		LEDPanel.setLayout(new GridLayout(1, 2));
		redLabel = new JLabel(" ");
		greenLabel = new JLabel(" ");
		LEDPanel.add(redLabel);
		LEDPanel.add(greenLabel);
		panel.add(LEDPanel, BorderLayout.NORTH);
		JPanel keyPanel = new JPanel();
		keyPanel.setLayout(new GridLayout(4, 3));
		panel.add(keyPanel, BorderLayout.CENTER);
		frame.add(panel);
		JButton aButton;
		int k = 0;
		while (k < 4*3) {
			aButton = new JButton(KEYS[k]);
			keyPanel.add(aButton);
			aButton.addActionListener(this);
			k++;
		}
		frame.pack();
		frame.setVisible(true);
		redTimer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) { 	// Inner class with code
				redTimer.stop();							// to be executed when the timer
				redLabel.setText(" ");						// event happens
			}
		});
		greenTimer = new Timer(1000, new ActionListener() { // Inner class as above
			public void actionPerformed(ActionEvent e) {
				greenTimer.stop();
				greenLabel.setText(" ");
			}
		});
	}

	/** 
	 * Hanterar händelser när en knapp har tryckts.
	 */
	public void actionPerformed(ActionEvent e) {
		String theActionCommand = e.getActionCommand();
		char c = theActionCommand.charAt(0);
		if (manager != null) 
			manager.entryCharacter(c);
	}
}
