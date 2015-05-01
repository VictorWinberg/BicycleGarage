package database;

public class User {
	private String personnr, first_name, last_name, mail, pin, phonenr;
	private int slots;

	public User(String personnr, String first_name, String last_name,
			String mail, String phonenr) {
		this.personnr = personnr;
		this.first_name = first_name;
		this.last_name = last_name;
		this.mail = mail;
		this.phonenr = phonenr;
		pin = (int)((Math.random() * 900000) + 100000) + "";
	}
	
	public String getPersonnr() { return personnr; }
	public String getFirstName() { return first_name; }
	public String getLastName() { return last_name; }
	public String getMail() { return mail; }
	public String getPIN() { return pin; }
	public String getPhonenr() { return phonenr; }
	public int getSlots() { return slots; }
	public boolean hasSlots() { 
		if(slots > 0) return true; 
		else return false; }

	public void addSlot() { slots++; }
	public boolean removeSlot() { 
		if(slots < 1) return false;
		else slots--; return true; 
	}
	
	public static void main(String[] args) {
//		while(true) {
//			User u = new User("950407-1234", "Victor", "Winberg", "mail@swag.com", "0707123456");
//			System.out.println(u.getPIN());
//		}
		User u = new User("950407-1234", "Victor", "Winberg", "mail@swag.com", "0707123456");
		u.addSlot();
		u.addSlot();
		u.removeSlot();
		u.removeSlot();
		u.removeSlot();
		System.out.println("Has none slots " + u.hasSlots());
		u.addSlot();
		System.out.println("Has slots " + u.hasSlots());
	}
}
