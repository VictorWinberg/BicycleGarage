package database;

public class User {
	private String personnr, first_name, last_name, mail, phonenr, pin;
	private int reservedSlots, freeSlots;

	/** Ny användare */
	public User(String personnr, String first_name, String last_name,
			String mail, String phonenr) {
		this.personnr = personnr;
		this.first_name = first_name;
		this.last_name = last_name;
		this.mail = mail;
		this.phonenr = phonenr;
	}
	
	/** Redan definierad användare */
	public User(String personnr, String first_name, String last_name,
			String mail, String phonenr, String pin, int reservedSlots, int freeSlots) {
		this.personnr = personnr;
		this.first_name = first_name;
		this.last_name = last_name;
		this.mail = mail;
		this.phonenr = phonenr;
		this.pin = pin;
		this.reservedSlots = reservedSlots;
		this.freeSlots = freeSlots;
	}
	
	public String getPersonnr() { return personnr; }
	public String getFirstName() { return first_name; }
	public String getLastName() { return last_name; }
	public String getMail() { return mail; }
	public String getPhonenr() { return phonenr; }
	public String getPIN() { return pin; }
	public int getReserverdSlots() { return reservedSlots; }
	public int getFreeSlots() { return freeSlots; }
	
	public String generatePIN() {
		String chars = "0123456789";
		StringBuilder sb = new StringBuilder();
		while (sb.length() < 6) {
			int index = (int) (Math.random() * chars.length());
			sb.append(chars.charAt(index));
		}
		pin = sb.toString();
		return pin;
	}
	
	public String toString() {
		return personnr + " | " + first_name + " | " + 
		last_name + " | " + mail + " | " + phonenr + " | " + 
		pin + " | " + reservedSlots + " | " + freeSlots;
	}
	
	public static void main(String[] args) {
		while(true) {
			User u = new User("950407-1234", "Victor", "Winberg", "mail@swag.com", "0707123456");
			System.out.println(u.getPIN());
		}
	}
}
