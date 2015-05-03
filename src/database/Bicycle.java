package database;

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
	
	public String getBarcode() 		{ return barcode; }
	public User getOwner() 			{ return user; }
	public boolean isDeposited() 	{ return deposited; }
	public String toString() {
		return user.getPersonnr() + " | " + barcode + " | " + deposited;
	}

	public void deposit() 			{ deposited = true; }
	public void withdraw() 			{ deposited = false; }
}
