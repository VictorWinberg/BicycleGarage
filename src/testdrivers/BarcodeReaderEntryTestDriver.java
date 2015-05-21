package testdrivers;

/**
 * Denna klass simulerar en streckkodsläsare vid entrédörren. Den kan användas
 * när BicycleGarageManager testas.
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
		frame.setLocation(java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getCenterPoint());
		frame.setLocation(frame.getLocation().x - 400, frame.getLocation().y - 400);

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
}
