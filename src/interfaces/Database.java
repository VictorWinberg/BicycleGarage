package interfaces;

import java.sql.ResultSet;
import java.util.List;

import database.Bicycle;
import database.User;

public interface Database {

	/** Skapar användartabellen och cykeltabellen.
	 * @return true ifall användartabellen eller cykeltabellen
	 * 		   skapades, annars false */
	public boolean createTables();

	/** Tar bort användartabellen och cykeltabellen.
	 * @return true ifall användartabellen och cykeltabellen 
	 *         togs bort, annars false */
	public boolean dropTables();
	
	/** Skapar en användare User enligt uppgifterna
	 * @param personnr Användarens personnummer
	 * @param first_name Användarens förnamn
	 * @param last_name Användarens efternamn
	 * @param mail Användarens emailadress
	 * @param phonenr Användarens telefonnummer
	 * @return Ny användare User, om felaktig indata retuneras null */
	public User createUser(String personnr, String first_name, String last_name,
			String mail, String phonenr);

	/** Lägger till användare user.
	 *  @param user
	 *            ny användare
	 * @return true ifall användaren user lades till, annars false */
	public boolean insertUser(User user);

	/** Tar bort användaren user.
	 * @param user
	 *            befintlig användare user
	 * @return true ifall användaren user togs bort, annars false */
	public boolean deleteUser(User user);
	
	/** Uppdaterar en användare user
	 * @param Användare user
	 * @return true om användaren user uppdaterades, annars false */
	boolean updateUser(User user);
	
	/** Hämtar en specifik användare med personnr
	 * @return User användare, annars null */
	public User getUser(String personnr);
	
	/** Hämtar en specifik användare med pin
	 * @return User användare, annars null */
	public User getUserWithPIN(String pin);
	
	/** Hämtar lista med cyklar som tillhör användare user
	 * @param user Sökt användare
	 * @return Lista med eller utan cyklar, null om användaren inte finns */
	public List<Bicycle> getBicycles(User user);
	
	/** Hämtar alla användare till ett ResultSet
	 * @return ResultSet med användare */
	public ResultSet extractUsers();
	
	/** Skapar en ny cykel
	 * @param user
	 * @return Ny cykel Bicycle, annars null */
	public Bicycle createBicycle(User user);
	
	/** Lägger till cykeln bicycle.
	 * @param bicycle
	 *            ny cykel
	 * @return true ifall cykeln bicycle lades till, annars false */
	public boolean insertBicycle(Bicycle bicycle);

	/** Tar bort cykeln bicycle
	 * @param bicycle
	 *            befintlig cykel bicycle
	 * @return true ifall cykeln bicycle togs bort, annars false */
	public boolean deleteBicycle(Bicycle bicycle);
	
	/** Uppdaterar en cykel bicycle
	 * @param Cykel bicycle
	 * @return true om cykeln bicycle uppdaterades, annars false */
	boolean updateBicycle(Bicycle bicycle);
	
	/** Hämtar en specifik cykel Bicycle med streckkod barcode
	 * @param barcode streckkod
	 * @return Cykel med angiven streckkod, annars null */
	public Bicycle getBicycle(String barcode);
	
	/** Hämtar alla cyklar till ett ResultSet
	 * @return ResultSet med cyklar */
	public ResultSet extractBicycles();

	/** Rensar alla inaktiva användare
	 * @return true om någon användare tagits bort, annars false */
	boolean clearInactiveUsers();
}
