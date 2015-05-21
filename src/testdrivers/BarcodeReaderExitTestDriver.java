package testdrivers;

/**
 * Denna klass simulerar en streckkodsläsare vid cykelutgången. Den kan användas
 * när BicycleGarageManager testas.
 * 
 * @version 1.0
 * @author Martin Host
 */
public class BarcodeReaderExitTestDriver extends BarcodeReaderTestDriver {

	/**
	 * Konstruerar en instans av BarcodeReaderExitTestDriver.
	 */
	public BarcodeReaderExitTestDriver() {
		super("Utgångsläsare");
		frame.setLocation(java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getCenterPoint());
		frame.setLocation(frame.getLocation().x, frame.getLocation().y - 400);
	}

	/**
	 * Implementering av abstrakt metod informManager.
	 * 
	 * @see BarcodeReaderTestDriver
	 */
	@Override
	void informManager(String code) {
		manager.exitBarcode(code);
	}

	public void main(String[] args) {
		new BarcodeReaderExitTestDriver();
	}
}
