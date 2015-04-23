package interfaces;

public interface BarcodePrinter {
	
	/* Skriver ut ett bicycleID som en streckkod.
	* Cykel ID:t b�r vara en str�ng med 5 tecken, d�r 
	* varje tecken kan vara '0', '1', ... "9". */
	public void printBarcode(String bicycleID);
}
