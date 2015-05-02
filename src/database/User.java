package database;

public class User {
	private String personnr, first_name, last_name, mail, pin, phonenr;
	private int reservedSlots, freeSlots;

	public User(String personnr, String first_name, String last_name,
			String mail, String phonenr) {
		this.personnr = personnr;
		this.first_name = first_name;
		this.last_name = last_name;
		this.mail = mail;
		this.phonenr = phonenr;
		String chars = "0123456789";
		StringBuilder sb = new StringBuilder();
		while (sb.length() < 6) {
			int index = (int) (Math.random() * chars.length());
			sb.append(chars.charAt(index));
		}
		pin = sb.toString();
	}
	
	public String getPersonnr() { return personnr; }
	public String getFirstName() { return first_name; }
	public String getLastName() { return last_name; }
	public String getMail() { return mail; }
	public String getPIN() { return pin; }
	public String getPhonenr() { return phonenr; }
	
	public static void main(String[] args) {
		while(true) {
			User u = new User("950407-1234", "Victor", "Winberg", "mail@swag.com", "0707123456");
			System.out.println(u.getPIN());
		}
	}
}
