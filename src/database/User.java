package database;

public class User {
	private String personnr, first_name, last_name, mail, phonenr, pin;
	private int reservedSlots, freeSlots;
	
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
	
	public String getPersonnr() 	{ return personnr; }
	public String getFirstName() 	{ return first_name; }
	public String getLastName() 	{ return last_name; }
	public String getMail() 		{ return mail; }
	public String getPhonenr() 		{ return phonenr; }
	public String getPIN() 			{ return pin; }
	public void addReserverdSlot(int slots) {
		 reservedSlots+=slots;
	}
	public void removeReservedSlot(int slots) {
		if(slots > reservedSlots){
			throw new IllegalArgumentException("Antalet platser du vill ta bort är större än antalet du ha reserverade");
		}
	 reservedSlots-=slots;
	}
	public int getReserverdSlots() 	{ return reservedSlots; }
	public void addFreeSlot(int slots) {
		freeSlots+=slots;;
	}
	public void removeFreeSlot(int slots) {
		if(slots > freeSlots){
			throw new IllegalArgumentException("Antalet platser du vill ta bort är större än antalet du har lediga");
		}
	 freeSlots-=slots;
	}
	public int getFreeSlots() 		{ return freeSlots; }
	@Override
	public String toString() {
		return personnr + " | " + first_name + " | " + 
		last_name + " | " + mail + " | " + phonenr + " | " + 
		pin + " | " + reservedSlots + " | " + freeSlots;
	}
	public boolean equals(Object o){
		if( o instanceof User){
			return personnr.equals(((User)o).personnr);
		} else {
			return false;
		}
	}
}
