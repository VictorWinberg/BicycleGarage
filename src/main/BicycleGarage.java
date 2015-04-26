package main;

import interfaces.*;
import testdrivers.*;

public class BicycleGarage {
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