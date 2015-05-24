package testdrivers;

import interfaces.PinCodeTerminal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import main.BicycleGarageManager;

/**
 * Denna klass simulerar en pinkodsterminal. Den kan användas när
 * BicycleGarageManager testas.
 * 
 * @version 1.0
 * @author Grupp25
 */
public class PinCodeTerminalTestDriver implements PinCodeTerminal, ActionListener {

	private BicycleGarageManager manager = null;
	private static final String[] KEYS = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "#", "0",
			"*" };
	private JPanel redPanel, greenPanel;
	private Timer redTimer, greenTimer;

	/**
     * Registrerar cykelgarage manager så att pinkodsterminalen vet vilken
	 * manager som ska anropas när en användare har tryckt en tangent.
	 * 
	 * @param manager
	 *            cykelgarage manager
	 */
	@Override
	public void register(BicycleGarageManager manager) {
		this.manager = manager;
	}

	/**
	 * Slå på LED i lightTime sekunder.
	 * 
	 * @param colour
	 *            RED_LED eller GREEN_LED
	 * @param lightTime
	 *            Slå på LED i lightTime sekunder
	 */
	@Override
	public void lightLED(int colour, int lightTime) {
		try {
			if (colour == RED_LED) {
				redPanel.setBackground(Color.RED);
				redTimer.setInitialDelay(lightTime * 1000);
				redTimer.restart();
			}
			if (colour == GREEN_LED) {
				greenPanel.setBackground(Color.GREEN);
				greenTimer.setInitialDelay(lightTime * 1000);
				greenTimer.restart();
			}
		} catch (IllegalArgumentException e) {
			System.err.println("ERROR: IllegalArgumentException i lightLED "
					+ "i PinCodeTerminalTestDriver");
		}
	}

	/**
	 * Skapar en PinCodeTestDriver.
	 */
	public PinCodeTerminalTestDriver() {
		JFrame frame = new JFrame("Pinkodsterminal");
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.setMinimumSize(new Dimension(240, 240));
		frame.setLocation(java.awt.GraphicsEnvironment.
				getLocalGraphicsEnvironment().getCenterPoint());
		frame.setLocation(frame.getLocation().x - 650, frame.getLocation().y - 300);
		// make the window impossible to close
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel LEDPanel = new JPanel();
		LEDPanel.setLayout(new GridLayout(1, 2));
		redPanel = new JPanel();
		greenPanel = new JPanel();
		LEDPanel.add(redPanel);
		LEDPanel.add(greenPanel);
		panel.add(LEDPanel, BorderLayout.NORTH);
		JPanel keyPanel = new JPanel();
		keyPanel.setLayout(new GridLayout(4, 3));
		panel.add(keyPanel, BorderLayout.CENTER);
		frame.add(panel);
		JButton aButton;
		int k = 0;
		while (k < 4 * 3) {
			aButton = new JButton(KEYS[k]);
			keyPanel.add(aButton);
			aButton.addActionListener(this);
			k++;
		}
		frame.pack();
		frame.setVisible(true);
		redTimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // Inner class with
															// code
				redTimer.stop(); // to be executed when the timer
				redPanel.setBackground(new Color(238, 238, 238));
				; // event happens
			}
		});
		greenTimer = new Timer(1000, new ActionListener() { // Inner class as
															// above
					@Override
					public void actionPerformed(ActionEvent e) {
						greenTimer.stop();
						greenPanel.setBackground(new Color(238, 238, 238));
						;
					}
				});
		sb = new StringBuilder();
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // Inner class with
															// code
				sb = new StringBuilder(); // to be executed when the timer
				lightLED(RED_LED, 3);
				timer.stop(); // event happens
			}
		});
		timer.stop();
	}

	private Timer timer;
	private StringBuilder sb;

	/**
	 * Hanterar händelser när en knapp har tryckts.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String theActionCommand = e.getActionCommand();
		char c = theActionCommand.charAt(0);
		printPIN(c);
	}

	/**
	 * Hanterar inmatning av pinkod i pinkodsterminalen.
	 * @param c
	 * 			Tecknet på knappen som klickas/trycks på.
	 */
	
	private void printPIN(char c) {
		sb.append(c);
		try {
			timer.setInitialDelay(10 * 1000);
			timer.restart();
		} catch (IllegalArgumentException e) {
			System.err.println("ERROR: IllegalArgumentException i "
					+ "pin i PinCodeTerminalTestDriver");
		}
		if (sb.length() == 6) {
			manager.entryPIN(sb.toString());
			sb = new StringBuilder();
			timer.stop();
		}
	}
}
