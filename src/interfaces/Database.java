package interfaces;

import java.sql.ResultSet;

import database.Bicycle;
import database.User;

public interface Database {

	/** Skapar användartabellen och cykeltabellen.
	 * @return false ifall användartabellen och cykeltabellen
	 * 		   redan finns, annars true */
	public boolean createTables();

	/** Tar bort användartabellen och cykeltabellen.
	 * @return false ifall användartabellen eller cykeltabellen 
	 *         inte finns, annars true */
	public boolean dropTables();

	/** Lägger till användare user.
	 *  @param user
	 *            ny användare
	 * @return false ifall användaren redan finns, annars true */
	public boolean insertUser(User user);

	/** Tar bort användaren user.
	 * @param user
	 *            befintlig användare user
	 * @return false ifall användaren user inte finns, annars true */
	public boolean deleteUser(User user);

	/** Lägger till cykeln bicycle.
	 * @param bicycle
	 *            ny cykel
	 * @return false ifall cykeln bicycle redan finns, annars true */
	public boolean insertBicycle(Bicycle bicycle);

	/** Tar bort cykeln bicycle
	 * @param bicycle
	 *            befintlig cykel bicycle
	 * @return false ifall cykeln bicycle inte finns, annars true */
	public boolean deleteBicycle(Bicycle bicycle);
	
	/** Extraherar alla användare till ett ResultSet
	 * @return ResultSet med användare */
	public ResultSet extractUsers();
	
	/** Extraherar alla cyklar till ett ResultSet
	 * @return ResultSet med cyklar */
	public ResultSet extractBicycles();
}
