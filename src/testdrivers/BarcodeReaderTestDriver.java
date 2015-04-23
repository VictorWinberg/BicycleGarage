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
 * Detta �r en abstrakt klass som representerar de gemensamma delarna f�r alla
�* streckkodsl�sare.
 * 
 * @version 1.0
 * @author Martin H�st 
 */
abstract public class BarcodeReaderTestDriver implements BarcodeReader, ActionListener {
	
	private JFrame frame;
	private JTextField scannedCode;
	protected BicycleGarageManager manager;
	private JButton scanButton;
	
	/**
	* Registrerar cykelgarage manager s� att streckkodsl�saren
	* vet vilken manager som ska anropas n�r en anv�ndare har 
	* anv�nt l�saren.
	* @param manager Cykelgarage manager
	*/
	public void register(BicycleGarageManager manager) {
		this.manager = manager;
	}
	
	/** 
	 * Skapar en BarcodeReaderTestDriver.
	 * @param WindowName text som ska skrivas i f�nstert.
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
	 * Denna abstrakta metod implementeras av underklasser.
	 * Subklasser kallar antingen p� managerns entryBarcode
	 * eller exitBarcode.
	 * @param Kod den skannade koden. */
	abstract void informManager(String code);
	
	/**
	 * Hanterar den h�ndelse n�r "scan-knappen" trycks.
	 */
	public void actionPerformed(ActionEvent e) {
		String code = scannedCode.getText();
		scannedCode.setText(null);
		informManager(code);
	}
}
