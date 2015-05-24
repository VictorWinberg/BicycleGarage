package interfaces;

public interface ElectronicLock {

	/** Öppnar låset i timeOpen sekunder. 
	 * @param timeOpen tiden låset ska vara öppet
	 */
	public void open(int timeOpen);
}
