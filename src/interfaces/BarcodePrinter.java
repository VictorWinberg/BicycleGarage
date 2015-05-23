package interfaces;

public interface BarcodePrinter {

	/**
	 * Skriver ut ett bicycleID som en streckkod. Cykel ID:t bör vara en sträng
	 * med 5 tecken, där varje tecken kan vara '0', '1', ... "9".
	 * @param bicycleID Cykelstreckkoden
	 */
	public void printBarcode(String bicycleID);
}
