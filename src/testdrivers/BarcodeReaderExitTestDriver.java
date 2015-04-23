package testdrivers;

/**
 * Denna klass simulerar en streckkodsläsare vid cykelutgången.
 * Den kan användas när BicycleGarageManager testas.
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
