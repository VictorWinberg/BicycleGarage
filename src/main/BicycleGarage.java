package main;

import gui.ProgressBar;
import interfaces.*;
import testdrivers.*;

/**
 * Detta är huvudprogrammet som körs när programmet startas. Den skapar ett
 * BicycleGarageManager-objekt samt alla testdrivrutinobjekt och länkar ihop
 * dessa.
 * 
 * @author Victor Winberg, Anton Göransson, Povel Larsson, Erik Danielsson, Emma
 *         Asklund, Tobias Olsson
 *
 */
public class BicycleGarage {
	/**
	 * Skapar ett BicycleGarageManager-objekt samt alla testdrivrutinobjekt
	 * och länkar ihop dessa.
	 */
	public BicycleGarage() {
		BicycleGarageManager manager = new BicycleGarageManager();
		ElectronicLock entryLock = new ElectronicLockTestDriver("Entrélås");
		ElectronicLock exitLock = new ElectronicLockTestDriver("Utgångslås");
		BarcodePrinter printer = new BarcodePrinterTestDriver();
		PinCodeTerminal terminal = new PinCodeTerminalTestDriver();
		manager.registerHardwareDrivers(printer, entryLock, exitLock, terminal);
		terminal.register(manager);
		BarcodeReader readerEntry = new BarcodeReaderEntryTestDriver();
		BarcodeReader readerExit = new BarcodeReaderExitTestDriver();
		readerEntry.register(manager);
		readerExit.register(manager);
	}

	public static void main(String[] args) {
		new BicycleGarage();
	}
}