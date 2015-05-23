package interfaces;

import java.sql.ResultSet;
import java.util.List;

import database.Bicycle;
import database.User;

public interface Database {

	/**
	 * Skapar användartabellen och cykeltabellen.
	 * 
	 * @return true ifall användartabellen eller cykeltabellen skapades, annars
	 *         false
	 */
	public boolean createTables();

	/**
	 * Tar bort användartabellen och cykeltabellen.
	 * 
	 * @return true ifall användartabellen och cykeltabellen togs bort, annars
	 *         false
	 */
	public boolean dropTables();

	/**
	 * Skapar en användare User enligt uppgifterna
	 * 
	 * @param personnr
	 *            Användarens personnummer
	 * @param first_name
	 *            Användarens förnamn
	 * @param last_name
	 *            Användarens efternamn
	 * @param mail
	 *            Användarens emailadress
	 * @param phonenr
	 *            Användarens telefonnummer
	 * @return Ny användare User, om felaktig indata retuneras null
	 * @throws Exception
	 *             meddelande om mail eller personnummer är felaktig
	 */
	public User createUser(String personnr, String first_name, String last_name, String mail,
			String phonenr) throws Exception;

	/**
	 * Lägger till användare user.
	 * 
	 * @param user
	 *            ny användare
	 * @return true ifall användaren user lades till, annars false
	 */
	public boolean insertUser(User user);

	/**
	 * Tar bort användaren user.
	 * 
	 * @param user
	 *            befintlig användare user
	 * @return true ifall användaren user togs bort, annars false
	 */
	public boolean deleteUser(User user);

	/**
	 * Uppdaterar en användare user
	 * 
	 * @param Användare
	 *            user
	 * @return true om användaren user uppdaterades, annars false
	 */
	boolean updateUser(User user);

	/**
	 * Hämtar en specifik användare med personnr
	 * 
	 * @return User användare, annars null
	 */
	public User getUser(String personnr);

	/**
	 * Hämtar en specifik användare med pin
	 * 
	 * @return User användare, annars null
	 */
	public User getUserWithPIN(String pin);

	/**
	 * Hämtar lista med cyklar som tillhör användare user
	 * 
	 * @param user
	 *            Sökt användare
	 * @return Lista med eller utan cyklar, null om användaren inte finns
	 */
	public List<Bicycle> getBicycles(User user);

	/**
	 * Hämtar alla användare till ett ResultSet
	 * 
	 * @return ResultSet med användare
	 */
	public ResultSet extractUsers();

	/**
	 * Skapar en ny cykel
	 * 
	 * @param user
	 * @return Ny cykel Bicycle, annars null
	 * @throws Exception
	 *             meddelande om användaren är felaktig
	 */
	public Bicycle createBicycle(User user) throws Exception;

	/**
	 * Lägger till cykeln bicycle.
	 * 
	 * @param bicycle
	 *            ny cykel
	 * @return true ifall cykeln bicycle lades till, annars false
	 */
	public boolean insertBicycle(Bicycle bicycle);

	/**
	 * Tar bort cykeln bicycle
	 * 
	 * @param bicycle
	 *            befintlig cykel bicycle
	 * @return true ifall cykeln bicycle togs bort, annars false
	 */
	public boolean deleteBicycle(Bicycle bicycle);

	/**
	 * Uppdaterar en cykel bicycle
	 * 
	 * @param Cykel
	 *            bicycle
	 * @return true om cykeln bicycle uppdaterades, annars false
	 */
	boolean updateBicycle(Bicycle bicycle);

	/**
	 * Hämtar en specifik cykel Bicycle med streckkod barcode
	 * 
	 * @param barcode
	 *            streckkod
	 * @return Cykel med angiven streckkod, annars null
	 */
	public Bicycle getBicycle(String barcode);

	/**
	 * Hämtar alla cyklar till ett ResultSet
	 * 
	 * @return ResultSet med cyklar
	 */
	public ResultSet extractBicycles();

	/**
	 * Rensar alla inaktiva användare
	 * 
	 * @return true om någon användare tagits bort, annars false
	 */
	boolean clearInactiveUsers();

	/**
	 * Kontrollerar om personnummert pnr är korrekt
	 * 
	 * @return true om personummert är korrekt, annars false
	 */
	public boolean isPNRValid(String pnr);

	/**
	 * Reserverar slots platser åt användaren user
	 * 
	 * @param user
	 *            användaren som man vill reservera platser åt
	 * @param slots
	 *            antalet platser som man vill reservera
	 * @return True om en eller flera platser reserverats, annars false
	 */
	public boolean reserveSlot(User user, int slots);

	/**
	 * Avresererverar platser åt användaren
	 * 
	 * @param user
	 *            användaren som man vill avreservera platser åt
	 * @param slots
	 *            antalet platser man vill avreservera
	 * @return True om en eller flera platser avreserverats, annars false
	 */
	public boolean removeReservedSlot(User user, int slots);

	/**
	 * Registerar en cykel som inlämnad
	 * 
	 * @param bc
	 *            cykeln som man vill registrera
	 * @return True om cykeln registrerats, annars false
	 */
	public boolean depositBicycle(Bicycle bc);

	/**
	 * Registrerar cykeln som ej inlämnad
	 * 
	 * @param bc
	 *            cykeln som man vill registrera
	 * @return True om cykeln registrerades, annars false
	 */
	public boolean withdrawBicycle(Bicycle bc);

	/**
	 * Tar bort lediga platser från en användare
	 * 
	 * @param user
	 *            användaren som man vill ta bort lediga platser från
	 * @param slots
	 *            antalet platser man vill ta bort
	 * @return True om en eller flera platser togs bort, annars false
	 */
	public boolean removeFreeSlot(User user, int slots);

	/**
	 * Lägger till lediga platser till en användare
	 * 
	 * @param user
	 *            använadren som man vill lägga till platser till
	 * @param slots
	 *            antalet platser man vill lägga till
	 * @return True om en eller flera lediga platser lades till, annars false
	 */
	public boolean addFreeSlot(User user, int slots);

	/**
	 * Hämtar totalt antal reserverade platser
	 * 
	 * @return Antalet reserverade platser
	 */
	public int getReservedSlots();
}
