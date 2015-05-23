package database;
/**
 * Beskriver cyklarna i databasen med pssande metoder och funktioner.
 * @author Grupp 25
 *
 */
public class Bicycle {
	private String barcode;
	private User user;
	private boolean deposited;

	/** Redan definierad cykel */
	public Bicycle(String barcode, User user, boolean deposited) {
		this.barcode = barcode;
		this.user = user;
		this.deposited = deposited;
	}

	/**
	 * 
	 * @return streckkoden i form av en string med 5 tecken
	 */
	public String getBarcode() {
		return barcode;
	}

	/**
	 * 
	 * @return user som är kopplad till streckkoden
	 */
	public User getOwner() {
		return user;
	}

	/**
	 * 
	 * @return true om cykeln är registrerad som inlämnad, false om den inte är
	 *         det
	 */
	public boolean isDeposited() {
		return deposited;
	}

	/**
	 * Ger en string med "personnummer | streckkod | deposited"
	 */
	@Override
	public String toString() {
		return user.getPersonnr() + " | " + barcode + " | " + deposited;
	}

	/**
	 * Registrerar cykeln som inlämnad
	 */
	public void deposit() {
		deposited = true;
	}
	/**
	 * Registrerar cykeln som icke inlämnad
	 */
	public void withdraw() {
		deposited = false;
	}
}
