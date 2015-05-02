package interfaces;

import java.sql.ResultSet;

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
	
	/** Hämtar alla användare till ett ResultSet
	 * @return ResultSet med användare */
	public ResultSet extractUsers();
	
	/** Hämtar alla cyklar till ett ResultSet
	 * @return ResultSet med cyklar */
	public ResultSet extractBicycles();
}
