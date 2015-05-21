package main;

import interfaces.BarcodePrinter;
import interfaces.BarcodeReader;
import interfaces.ElectronicLock;
import interfaces.PinCodeTerminal;
import testdrivers.BarcodePrinterTestDriver;
import testdrivers.BarcodeReaderEntryTestDriver;
import testdrivers.BarcodeReaderExitTestDriver;
import testdrivers.ElectronicLockTestDriver;
import testdrivers.PinCodeTerminalTestDriver;

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
	 * Skapar ett BicycleGarageManager-objekt samt alla testdrivrutinobjekt och
	 * länkar ihop dessa.
	 */
	public BicycleGarage() {
		BicycleGarageManager manager = new BicycleGarageManager();
		BarcodeReader readerEntry = new BarcodeReaderEntryTestDriver();
		ElectronicLock entryLock = new ElectronicLockTestDriver("Entrélås");
		BarcodeReader readerExit = new BarcodeReaderExitTestDriver();
		ElectronicLock exitLock = new ElectronicLockTestDriver("Utgångslås");
		PinCodeTerminal terminal = new PinCodeTerminalTestDriver();
		BarcodePrinter printer = new BarcodePrinterTestDriver();
		manager.registerHardwareDrivers(printer, entryLock, exitLock, terminal);
		terminal.register(manager);
		readerEntry.register(manager);
		readerExit.register(manager);
	}

	public static void main(String[] args) {
		new BicycleGarage();
	}
}