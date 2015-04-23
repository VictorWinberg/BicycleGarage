package testdrivers;

/**
 * This class simulates a bar code reader at the entry door. 
 * It can be used when BicycleGarageManager is tested.
 * 
 * @version 1.0
 * @author Martin Höst 
 */
public class BarcodeReaderEntryTestDriver extends BarcodeReaderTestDriver {
	
	/**
	 * Construct an instance of BarcodeReaderEntryTestDriver.
	 */
	public BarcodeReaderEntryTestDriver() {
		super("Entry door barcode reader");
	}
	
	/**
	 * Implementation of abstract method informManager.
	 * @see abstract class BarcodeReaderTestDriver
	 */
	void informManager(String code) {
		manager.entryBarcode(code);
	}

}
