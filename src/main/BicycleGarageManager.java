package main;

import interfaces.*;

public class BicycleGarageManager {
	
	/* Registrerar hårdvara så att BicycleGarageManager
	* vet vilka drivrutiner som är tillgängliga. */
	public void registerHardwareDrivers(BarcodePrinter printer,
	     ElectronicLock entryLock, ElectronicLock exitLock,
	     PinCodeTerminal terminal) {
	}

	/* Kommer att kallas när en användare har använt 
	* strecksläsaren vid entrédörren. Cykel ID bör 
	* vara en sträng med 5 tecken, där varje tecken
	* kan vara '0', '1', ... "9". */
	public void entryBarcode(String bicycleID) {
	}

	/* Kommer att kallas när en användare har använt 
	* strecksläsaren vid cykelutgången. Cykel ID bör 
	* vara en sträng med 5 tecken, där varje tecken
	* kan vara '0', '1', ... "9". */
	public void exitBarcode(String bicycleID) {
	}

	/* Kommer att kallas när en användare har tryckt på en tangent på
	* knappsats på entrédörren. Följande tecken kan vara
	* intryckta: "0", "1", ... "9", "*", "#". */
	public void entryCharacter(char c) {
	}
}
