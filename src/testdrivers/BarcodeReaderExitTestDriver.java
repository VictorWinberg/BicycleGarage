package testdrivers;

/**
 * This class simulates a bar code reader at the exit door. 
 * It can be used when BicycleGarageManager is tested.
 * 
 * @version 1.0
 * @author Martin Host 
 */
public class BarcodeReaderExitTestDriver extends BarcodeReaderTestDriver {

	/**
	 * Construct an instance of BarcodeReaderExitTestDriver.
	 */
	public BarcodeReaderExitTestDriver() {
		super("Exit door barcode reader");
	}

	/**
	 * Implementation of abstract method informManager.
	 * @see abstract class BarcodeReaderTestDriver
	 */
	void informManager(String code) {
		manager.exitBarcode(code);
	}

}
