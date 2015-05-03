package interfaces;

import main.BicycleGarageManager;

public interface PinCodeTerminal {

	/**
	 * Registrerar cykelgarage manager så att pinkodsterminalen vet vilken
	 * manager som ska anropas när en användare har tryckt en tangent.
	 */
	public void register(BicycleGarageManager manager);

	/**
	 * Slå på LED i lightTime sekunder. Färg: Färg = RED_LED = 0 => röd Färg =
	 * GREEN_LED = 1 => grön
	 */
	public void lightLED(int colour, int lightTime);

	public static final int RED_LED = 0, GREEN_LED = 1;
}
