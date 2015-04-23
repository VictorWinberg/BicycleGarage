package main;

import interfaces.*;

public class BicycleGarageManager {
	
	/* Registrerar h�rdvara s� att BicycleGarageManager
	* vet vilka drivrutiner som �r tillg�ngliga. */
	public void registerHardwareDrivers(BarcodePrinter printer,
	     ElectronicLock entryLock, ElectronicLock exitLock,
	     PinCodeTerminal terminal) {
	}

	/* Kommer att kallas n�r en anv�ndare har anv�nt 
	* strecksl�saren vid entr�d�rren. Cykel ID b�r 
	* vara en str�ng med 5 tecken, d�r varje tecken
	* kan vara '0', '1', ... "9". */
	public void entryBarcode(String bicycleID) {
	}

	/* Kommer att kallas n�r en anv�ndare har anv�nt 
	* strecksl�saren vid cykelutg�ngen. Cykel ID b�r 
	* vara en str�ng med 5 tecken, d�r varje tecken
	* kan vara '0', '1', ... "9". */
	public void exitBarcode(String bicycleID) {
	}

	/* Kommer att kallas n�r en anv�ndare har tryckt p� en tangent p�
	* knappsats p� entr�d�rren. F�ljande tecken kan vara
	* intryckta: "0", "1", ... "9", "*", "#". */
	public void entryCharacter(char c) {
	}
}
