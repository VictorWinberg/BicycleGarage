package testdrivers;

import java.awt.Dimension;

import interfaces.BarcodePrinter;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Denna klass simulerar en streckkodsskrivare. Den kan användas när
 * BicycleGarageManager testas.
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
		frame = new JFrame("Streckkodsskrivare");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setMinimumSize(new Dimension(200, 200));

		textArea = new JTextArea(5, 20);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);

		frame.add(scrollPane);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Skriver ut ett bicycleID som en streckkod.
	 * 
	 * @param bicycleID
	 *            en sträng med 5 tecken, varje tecken kan vara '0', '1', ...
	 *            "9".
	 */
	public void printBarcode(String bicycleID) {
		if(bicycleID.length() != 5 || bicycleID.matches("[0-9]+")){
			throw new IllegalArgumentException("Streckkoden är inte giltlig");
		}
		textArea.append("Event " + ++serialNr + ": "
				+ "Skriver ut " + bicycleID + "\n");
	}
	
	public static void main(String[] args) {
		new BarcodePrinterTestDriver();
	}
}
