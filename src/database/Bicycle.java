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

	/** Redan definierad cykel 
	 * @param barcode streckkod
	 * @param user användare
	 * @param deposited inlämnad
	 */
	public Bicycle(String barcode, User user, boolean deposited) {
		this.barcode = barcode;
		this.user = user;
		this.deposited = deposited;
	}

	/**
	 * Ta reda på streckkoden
	 * @return streckkoden i form av en string med 5 tecken
	 */
	public String getBarcode() {
		return barcode;
	}

	/**
	 * Tar reda på användaren till cykeln
	 * @return user som är kopplad till streckkoden
	 */
	public User getOwner() {
		return user;
	}

	/**
	 * Ta reda på om cykeln är inlämnad eller inte
	 * @return true om cykeln är registrerad som inlämnad, annars false 
	 */
	public boolean isDeposited() {
		return deposited;
	}

	@Override
	/**
	 * Ta reda på all information om cykeln och gör det till en sträng
	 * @retrun en sträng som innehåller användarens personnummer, streckkod mm.
	 */
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
