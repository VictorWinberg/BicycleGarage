package interfaces;

import main.BicycleGarageManager;

public interface BarcodeReader {
	
	/* Registrera cykelgarage manager så att strecksläsaren
	* vet vilken manager som ska anropas när en användare har 
	* använt läsaren. */
	public void register(BicycleGarageManager manager);	
}
