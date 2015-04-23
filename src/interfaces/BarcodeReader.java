package interfaces;

import main.BicycleGarageManager;

public interface BarcodeReader {
	
	/* Registrera cykelgarage manager s� att strecksl�saren
	* vet vilken manager som ska anropas n�r en anv�ndare har 
	* anv�nt l�saren. */
	public void register(BicycleGarageManager manager);	
}
