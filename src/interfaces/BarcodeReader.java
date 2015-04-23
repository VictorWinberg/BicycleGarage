package interfaces;

import main.BicycleGarageManager;

public interface BarcodeReader {
	
	/* Register bicycle garage manager so that the bar code
	 * reader knows which manager to call when a user has used 
	 * the reader. 
	 */
	public void register(BicycleGarageManager manager);	
}
