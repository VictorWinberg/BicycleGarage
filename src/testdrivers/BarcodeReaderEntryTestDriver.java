package testdrivers;

/**
 * Denna klass simulerar en streckkodsläsare vid entrédörren.
 * Den kan användas när BicycleGarageManager testas.
 * 
 * @version 1.0
 * @author Martin Höst 
 */
public class BarcodeReaderEntryTestDriver extends BarcodeReaderTestDriver {
	
	/**
	 * Konstruerar en instans av BarcodeReaderEntryTestDriver.
	 */
	public BarcodeReaderEntryTestDriver() {
		super("Entry door barcode reader");
	}
	
	/**
	 * Implementering av abstrakt metod informManager.
	 * @see Abstrakt klass BarcodeReaderTestDriver
	 */
	void informManager(String code) {
		manager.entryBarcode(code);
	}

}
