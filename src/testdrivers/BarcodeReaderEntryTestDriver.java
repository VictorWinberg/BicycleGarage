package testdrivers;

/**
 * Denna klass simulerar en streckkodsläsare vid entrédörren. Den kan
 * användas när BicycleGarageManager testas.
 * 
 * @version 1.0
 * @author Martin Höst
 */
public class BarcodeReaderEntryTestDriver extends BarcodeReaderTestDriver {

	/**
	 * Konstruerar en instans av BarcodeReaderEntryTestDriver.
	 */
	public BarcodeReaderEntryTestDriver() {
		super("Entréläsare");
		frame.setLocation(265,30);
	}

	/**
	 * Implementering av abstrakt metod informManager.
	 * 
	 * @see BarcodeReaderTestDriver
	 */
	@Override
	void informManager(String code) {
		manager.entryBarcode(code);
	}
	
	public static void main(String[] args) {
		new BarcodeReaderEntryTestDriver();
	}
}
