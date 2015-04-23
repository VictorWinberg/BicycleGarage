package testdrivers;

import interfaces.BarcodePrinter;

import javax.swing.*;

/**
 * Denna klass simulerar en streckkodsskrivare.
 * Den kan användas när BicycleGarageManager testas.
 * 
 * @version 1.1
 * @author Martin Höst 
 */
public class BarcodePrinterTestDriver implements BarcodePrinter {

	private JTextArea textArea = new JTextArea(5, 20);
	private int serialNr = 0;
	private JFrame frame;

	/**
	 * Skapar en BarcodePrinterTestDriver
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
	 * Skriver ut ett bicycleID som en streckkod.
	 * @param BicycleID en sträng med 5 tecken, varje tecken kan vara '0', '1', ... "9".
	 */
	public void printBarcode(String bicycleID) {
		textArea.append("Event " + ++serialNr + ": Printing " + bicycleID + "\n");
	}
}
