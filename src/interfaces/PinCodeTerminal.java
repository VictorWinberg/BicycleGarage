package interfaces;

import main.BicycleGarageManager;

public interface PinCodeTerminal {
	
	/* Registrerar cykelgarage manager s� att pinkodsterminalen
	* vet vilken manager som ska anropas n�r en anv�ndare har
	* tryckt en tangent. */
	public void register(BicycleGarageManager manager);
	
	/* Sl� p� LED i lightTime sekunder.
	* F�rg:
	* F�rg = RED_LED = 0 => r�d
	* F�rg = GREEN_LED = 1 => gr�n */
	public void lightLED(int colour, int lightTime);

	public static final int RED_LED = 0,
							GREEN_LED = 1;
}
