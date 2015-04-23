package main;

import testdrivers.*;
import interfaces.*;

public class BicycleGarage {
    public BicycleGarage() {
        BicycleGarageManager manager = new BicycleGarageManager();
        ElectronicLock entryLock = new ElectronicLockTestDriver("Entr�l�s");
        ElectronicLock exitLock = new ElectronicLockTestDriver("Utg�ngsl�s");
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