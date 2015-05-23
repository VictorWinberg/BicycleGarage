package interfaces;

import main.BicycleGarageManager;

public interface BarcodeReader {

	/**
	 * Registrera cykelgarage manager så att strecksläsaren vet vilken manager
	 * som ska anropas när en användare har använt läsaren.
	 * @param manager Cykelgaragets manager
	 */
	public void register(BicycleGarageManager manager);
}
