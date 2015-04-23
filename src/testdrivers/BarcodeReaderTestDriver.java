package testdrivers;

import interfaces.BarcodeReader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import main.BicycleGarageManager;

import java.awt.BorderLayout;
import java.awt.event.*;

/**
 * This is an abstract class representing the common parts for all
 * barcode readers. 
 * 
 * @version 1.0
 * @author Martin Höst 
 */
abstract public class BarcodeReaderTestDriver implements BarcodeReader, ActionListener {
	
	private JFrame frame;
	private JTextField scannedCode;
	protected BicycleGarageManager manager;
	private JButton scanButton;
	
	/**
	* Register bicycle garage manager so that the bar code
	* reader knows which manager to call when a user has used 
	* the reader. 
	* @param manager The bicycle garage manager 
	*/
	public void register(BicycleGarageManager manager) {
		this.manager = manager;
	}
	
	/** 
	 * Create a BarcodeReaderTestDriver.
	 * @param windowName text to be written in window frame.
	 */
	public BarcodeReaderTestDriver(String windowName) {
		frame = new JFrame(windowName);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				// make the window impossible to close
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		scannedCode = new JTextField(5);
		panel.add(new JLabel("Scanned code:"), BorderLayout.WEST);
		panel.add(scannedCode, BorderLayout.EAST);
		
		scanButton = new JButton("Scan");
		scanButton.addActionListener(this);
		panel.add(scanButton, BorderLayout.SOUTH);		
		
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}	
	
	/**
	 * This abstract method is implemented by subclasses. 
	 * Subclasses either call the manager´s entryBarcode 
	 * or exitBarcode.   
	 * @param code the scanned code. 
	 */
	abstract void informManager(String code);
	
	/**
	 * Handles the event that the "scan button" is pressed.
	 */
	public void actionPerformed(ActionEvent e) {
		String code = scannedCode.getText();
		scannedCode.setText(null);
		informManager(code);
	}
}
