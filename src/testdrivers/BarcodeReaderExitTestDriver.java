package testdrivers;

/**
 * Denna klass simulerar en streckkodsl�sare vid cykelutg�ngen.
�* Den kan anv�ndas n�r BicycleGarageManager testas.
 * 
 * @version 1.0
 * @author Martin Host 
 */
public class BarcodeReaderExitTestDriver extends BarcodeReaderTestDriver {

	/**
	 * Konstruerar en instans av BarcodeReaderExitTestDriver.
	 */
	public BarcodeReaderExitTestDriver() {
		super("Exit door barcode reader");
	}

	/**
	 * Implementering av abstrakt metod informManager.
	 * @see Abstrakt klass BarcodeReaderTestDriver
	 */
	void informManager(String code) {
		manager.exitBarcode(code);
	}

}
