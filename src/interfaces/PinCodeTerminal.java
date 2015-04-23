package interfaces;

import main.BicycleGarageManager;

public interface PinCodeTerminal {
	
	/* Register bicycle garage manager so that the pin code
	 * terminal knows which manager to call when a user has 
	 * pressed a key. */
	public void register(BicycleGarageManager manager);
	
	/* Turn on LED for lightTime seconds.
	 * Colour: 
	 * colour = RED_LED = 0 => red 
	 * colour = GREEN_LED = 1 => green 
	 */
	public void lightLED(int colour, int lightTime);

	public static final int RED_LED = 0,
							GREEN_LED = 1;
}
