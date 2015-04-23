package testdrivers;

/**
 * Denna klass simulerar en streckkodsl�sare vid entr�d�rren.
�* Den kan anv�ndas n�r BicycleGarageManager testas.
 * 
 * @version 1.0
 * @author Martin H�st 
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
