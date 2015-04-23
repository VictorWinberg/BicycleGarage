package testdrivers;

import interfaces.BarcodePrinter;

import javax.swing.*;

/**
 * This class simulates a bar code printer. 
 * It can be used when BicycleGarageManager is tested.
 * 
 * @version 1.1
 * @author Martin Höst 
 */
public class BarcodePrinterTestDriver implements BarcodePrinter {

	private JTextArea textArea = new JTextArea(5, 20);
	private int serialNr = 0;
	private JFrame frame;

	/**
	 * Create a BarcodePrinterTestDriver
	 */
	public BarcodePrinterTestDriver() {
		frame = new JFrame("Bar code printer");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		textArea = new JTextArea(5, 20);		
		JScrollPane scrollPane = new JScrollPane(textArea); 
		textArea.setEditable(false);

		frame.add(scrollPane);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * Print a bicycleID as a barcode.
	 * @param bicycleID a string of 5 characters, every character can be '0', '1',... '9'. 
	 */
	public void printBarcode(String bicycleID) {
		textArea.append("Event " + ++serialNr + ": Printing " + bicycleID + "\n");
	}
}
