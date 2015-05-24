package database;
/**
 * Främst en hjälpklass för databasen och systemet för att arbeta med användare
 * @author Grupp 25
 *
 */
public class User {
	private String personnr, first_name, last_name, mail, phonenr, pin;
	private int reservedSlots, freeSlots, nbrOfBicycles;

	/**
	 * Redan definierad användare
	 * @param personnr En string med användarens personnummer
	 * @param first_name En string med användarens förnamn
	 * @param last_name En string med användarens efternamn
	 * @param mail En string med användarens mail
 	 * @param phonenr En string med anvåndarens telefonnr
	 * @param pin En string med användarens PIN-kod
	 * @param reservedSlots En int med antalet reserverade platser
	 * @param freeSlots En int med antalet lediga platser
	 * @param nbrOfBicycles En int med antalet cyklar
	 */
	public User(String personnr, String first_name, String last_name, String mail, String phonenr,
			String pin, int reservedSlots, int freeSlots, int nbrOfBicycles) {
		this.personnr = personnr;
		this.first_name = first_name;
		this.last_name = last_name;
		this.mail = mail;
		this.phonenr = phonenr;
		this.pin = pin;
		this.reservedSlots = reservedSlots;
		this.freeSlots = freeSlots;
		this.nbrOfBicycles = nbrOfBicycles;
	}


	/**
	 * @return String med personnumret
	 */
	public String getPersonnr() 	{ return personnr; }

	/**
	 * @param personnr En string som personnumret ska ändras till 
	 */
	public void setPersonnr(String personnr) { this.personnr = personnr; }
	
	/**
	 * @return En string med användarens förnamn
	 */
	public String getFirstName() 	{ return first_name; }

	/**
	 * @param first_name En string som användarens förnamn ska ändras till
	 */
	public void setFirstName(String first_name) { this.first_name = first_name; }

	/**
	 * @return En string med användarens efternamn
	 */
	public String getLastName() 	{ return last_name; }

	/**
	 * @param last_name En string som användarens efternamn ska ändras till
	 */
	public void setLastName(String last_name) { this.last_name = last_name; }

	/**
	 * @return En string med användarens mail
	 */
	public String getMail() 		{ return mail; }

	/**
	 * @param mail En string som användarens mail ska ändras till
	 */
	public void setMail(String mail) { this.mail = mail; }

	/**
	 * @return En string med användarens telefonnummer
	 */
	public String getPhonenr() 		{ return phonenr; }

	/**
	 * @param phonenr En string som användarens telefonnummer ska ändras till.
	 */
	public void setPhonenr(String phonenr){ this.phonenr = phonenr; }

	/**
	 * @return En string med användarens PIN
	 */
	public String getPIN() 			{ return pin; }

	/**
	 * @param slots En int med så många platser som användaren ska reservera
	 */
	public void addReserverdSlot(int slots) { reservedSlots += slots; }
	
	/**
	 * Tar bort reserverade platser och kastar IllegalArgumentException om man försöker avreservera fler platser än man har reserverade.
	 * @param slots En int med så många slots som användaren ska avreservera
	 */
	public void removeReservedSlot(int slots) {
		if (slots > reservedSlots) {
			throw new IllegalArgumentException(
					"Antalet platser du vill ta bort är större än antalet du ha reserverade");
		}
		reservedSlots -= slots;
	}

	/**
	 * @return En int med så många platser som användaren har reserverade
	 */
	public int getReserverdSlots() 	{ return reservedSlots; }

	/**
	 * @param slots En int med så många extra platser som användaren ska få lediga
	 */
	public void addFreeSlots(int slots) { freeSlots += slots; }
	
	/**
	 * Minskar antalet lediga platser för användaren med slots. Kastar IllegalArgumentException om man försöker minska med fler än möjligt.
	 * @param slots En int med så många extra platser som användaren ska få lediga
	 */
	public void removeFreeSlots(int slots) {
		if (slots > freeSlots) {
			throw new IllegalArgumentException(
					"Antalet platser du vill ta bort är större än antalet du har lediga");
		}
		freeSlots -= slots;
	}

	/**
	 * @return En int med antalet lediga platser för användaren
	 */
	public int getFreeSlots() 	{ return freeSlots; }
	
	/**
	 * Registrerar att användaren har fått en till cykel
	 */
	public void addBicycle() 	{ nbrOfBicycles++; }

	/**
	 * Registrerar att användaren har fått en mindre cykel
	 */
	public void removeBicycle() {
		if (nbrOfBicycles <= 0) {
			throw new IllegalArgumentException("Antalet cyklar är mindre än noll");
		}
		nbrOfBicycles--;
	}

	/**
	 * @return Antalet cyklar användaren har
	 */
	public int getNbrOfBicycles() { return nbrOfBicycles; }

	@Override
	/**
	 * Få en sträng med all information om användaren
	 * @return Em sträng med all information som en användare kan innehålla
	 */
	public String toString() {
		return personnr + " | " + first_name + " | " + last_name + " | " + mail + " | " + phonenr
				+ " | " + pin + " | " + reservedSlots + " | " + freeSlots + " | " + nbrOfBicycles;
	}

	@Override
	/**
	 * Tar reda på om det är samma användare
	 * @return true om det är samma användare, annars false
	 */
	public boolean equals(Object o) {
		if (o instanceof User) {
			return personnr.equals(((User) o).personnr);
		} else {
			return false;
		}
	}
}
