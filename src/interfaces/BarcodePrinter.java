//TEST

package interfaces;

public interface BarcodePrinter {
	
	/* Print a bicycleID as a barcode.
	 * Bicycle ID should be a string of 5 characters, where every 
	 * character can be '0', '1',... '9'. */
	public void printBarcode(String bicycleID);
}
