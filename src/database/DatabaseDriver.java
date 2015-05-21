package database;

import interfaces.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;

public class DatabaseDriver implements Database {

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://sql4.freesqldatabase.com:3306/sql474777";

	private static final String USER = "sql474777";
	private static final String PASS = "cS9!pG3*";

	private Connection conn = null;
	private String sql;

	public DatabaseDriver() throws ClassNotFoundException, SQLException {
		// Registrera JDBC drivrutin
		Class.forName(JDBC_DRIVER);

		// Anslut
		System.out.print("Databasen " + DB_URL);
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		System.out.println(" ansluten.");
	}

	@Override
	public boolean createTables() {
		boolean created = false;
		System.out.println();
		System.out.print("Användartabell ");
		sql = "CREATE TABLE  users (" + "personnr VARCHAR( 11 ) NOT NULL ,"
				+ "first_name VARCHAR( 255 ) NOT NULL ,"
				+ "last_name VARCHAR( 255 ) NOT NULL ,"
				+ "mail VARCHAR( 255 ) NOT NULL ,"
				+ "phonenr VARCHAR( 10 ) NOT NULL ,"
				+ "pin VARCHAR( 6 ) NOT NULL ,"
				+ "reservedSlots INT ( 4 ) NOT NULL ,"
				+ "freeSlots INT ( 4 ) NOT NULL ,"
				+ "nbrOfBicycles INT ( 4 ) NOT NULL ,"
				+ "PRIMARY KEY (  personnr ))";
		try {
			conn.createStatement().executeUpdate(sql);
			System.out.println("skapad.");
			created = true;
		} catch (SQLException e) {
			System.out.println("inte skapad, finns redan.");
		}
		System.out.print("Cykeltabell ");
		sql = "CREATE TABLE  bicycles (" + "barcode VARCHAR( 5 ) NOT NULL ,"
				+ "user_personnr VARCHAR( 11 ) NOT NULL ,"
				+ "deposited BOOLEAN NOT NULL ," + "PRIMARY KEY (  barcode ))";
		try {
			conn.createStatement().executeUpdate(sql);
			System.out.println("skapad.");
			created = true;
		} catch (SQLException e) {
			System.out.println("inte skapad, finns redan.");
		}
		return created;
	}

	@Override
	public boolean dropTables() {
		boolean dropped = false;
		System.out.println();
		System.out.print("Användartabellen ");
		sql = "DROP TABLE users";
		try {
			conn.createStatement().executeUpdate(sql);
			System.out.println("borttagen.");
			dropped = true;
		} catch (SQLException e) {
			System.out.println("inte borttagen, finns inte.");
		}
		System.out.print("Cykeltabellen ");
		sql = "DROP TABLE bicycles";
		try {
			conn.createStatement().executeUpdate(sql);
			System.out.println("borttagen.");
			dropped = true;
		} catch (SQLException e) {
			System.out.println("inte borttagen, finns inte.");
		}
		return dropped;
	}

	@Override
	public User createUser(String personnr, String first_name,
			String last_name, String mail, String phonenr) throws Exception {
		if (isPNRValid(personnr) && nameIsValid(first_name)
				&& nameIsValid(last_name)
				&& EmailValidator.getInstance().isValid(mail)
				&& phonenr.matches("[0-9]{10}")) {
			String chars = "0123456789";
			while (true) {
				StringBuilder sb = new StringBuilder();
				while (sb.length() < 6) {
					int index = (int) (Math.random() * chars.length());
					sb.append(chars.charAt(index));
				}
				String pin = sb.toString();
				if (getUserWithPIN(pin) == null)
					return new User(personnr, first_name, last_name, mail,
							phonenr, pin, 0, 0, 0);
			}
		} else {
			StringBuilder sb = new StringBuilder("Felaktigt: ");
			int length = sb.length();
			if (!isPNRValid(personnr)) {
				sb.append("Personnummer");
			}
			if (!nameIsValid(first_name)) {
				if (sb.length() > length)
					sb.append(", förnamn");
				else
					sb.append("Förnamn");
			}
			if (!nameIsValid(last_name)) {
				if (sb.length() > length)
					sb.append(", efternamn");
				else
					sb.append("Efternamn");
			}
			if (!EmailValidator.getInstance().isValid(mail)) {
				if (sb.length() > length)
					sb.append(", mailadress");
				else
					sb.append("Mailadress");
			}
			if (!phonenr.matches("[0-9]{10}")) {
				if (sb.length() > length)
					sb.append(", telefonnummer");
				else
					sb.append("Telefonnummer");
			}
			throw new Exception(sb.toString());
		}
	}

	@Override
	public boolean insertUser(User user) {
		if (user == null) {
			System.out
					.println("Användare user som skulle läggas till är null.");
			return false;
		}
		System.out.print("Användare " + user.getFirstName() + " ");
		sql = "INSERT INTO users " + "VALUES (" + "'" + user.getPersonnr()
				+ "', " + "'" + user.getFirstName() + "', " + "'"
				+ user.getLastName() + "', " + "'" + user.getMail() + "', "
				+ "'" + user.getPhonenr() + "', " + "'" + user.getPIN() + "', "
				+ user.getReserverdSlots() + ", " + user.getFreeSlots() + ", " + user.getNbrOfBicycles() + ")";
		try {
			conn.createStatement().executeUpdate(sql);
			System.out.println("tillagd.");
			return true;
		} catch (SQLException e) {
			System.out.println("inte tillagd. SQL Message: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteUser(User user) {
		if (user == null) {
			System.out.println("Användare user som skulle tas bort är null.");
			return false;
		} else if (getUser(user.getPersonnr()) == null) {
			System.out.println("Användare " + user.getFirstName()
					+ " inte borttagen, finns inte.");
			return false;
		} else if (!getBicycles(user).isEmpty()) {
			System.out.println("Användare " + user.getFirstName()
					+ " inte borttagen, har cyklar");
			return false;
		}
		System.out.print("Användare " + user.getFirstName() + " ");
		sql = "DELETE FROM users WHERE personnr = '" + user.getPersonnr() + "'";
		try {
			conn.createStatement().executeUpdate(sql);
			System.out.println("borttagen.");
			return true;
		} catch (SQLException e) {
			System.out
					.println("inte borttagen. SQL Message: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean updateUser(User user) {
		if (user == null) {
			System.out.println("Användare user som skulle uppdateras är null.");
			return false;
		}
		System.out.print("Användare " + user.getFirstName() + " ");
		sql = "UPDATE users SET " + "first_name = '" + user.getFirstName()
				+ "', " + "last_name = '" + user.getLastName() + "', "
				+ "mail = '" + user.getMail() + "', " + "phonenr = '"
				+ user.getPhonenr() + "', " + "pin = '" + user.getPIN() + "', "
				+ "reservedSlots = " + user.getReserverdSlots() + ", "
				+ "freeSlots = " + user.getFreeSlots() + ", "
				+ "nbrOfBicycles = " + user.getNbrOfBicycles() + " "
				+ "WHERE personnr = '" + user.getPersonnr() + "'";
		try {
			conn.createStatement().executeUpdate(sql);
			System.out.println("uppdaterad.");
			return true;
		} catch (SQLException e) {
			System.out.println("inte uppdaterad. SQL Message: "
					+ e.getMessage());
			return false;
		}
	}

	@Override
	public User getUser(String personnr) {
		if (personnr == null) {
			System.out
					.println("Användare med personnr som skulle hittas är null.");
			return null;
		}
		ResultSet rs = extractUsers();
		if (rs == null)
			return null;
		try {
			rs.beforeFirst();
			while (rs.next()) {
				if (rs.getString(1).equals(personnr))
					return new User(rs.getString(1), rs.getString(2),
							rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
			}
		} catch (SQLException e) {
			System.out.println("SQL Message i getUser: " + e.getMessage());
		}
		return null;
	}

	@Override
	public User getUserWithPIN(String pin) {
		if (pin == null) {
			System.out.println("Användare med pin som skulle hittas är null.");
			return null;
		}
		User user = null;
		ResultSet rs = extractUsers();
		if (rs == null)
			return null;
		try {
			rs.beforeFirst();
			while (rs.next()) {
				if (rs.getString(6).equals(pin))
					return new User(rs.getString(1), rs.getString(2),
							rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
			}
		} catch (SQLException e) {
			System.out.println("SQL Message i getUserWithPIN: "
					+ e.getMessage());
		}
		return user;
	}

	@Override
	public List<Bicycle> getBicycles(User user) {
		if (user == null) {
			System.out
					.println("Cyklar med användaren user som skulle hittas är null.");
			return null;
		}
		List<Bicycle> bicycles = new LinkedList<Bicycle>();
		ResultSet rs = extractBicycles();
		if (rs == null)
			return bicycles;
		try {
			rs.beforeFirst();
			while (rs.next()) {
				if (rs.getString(2).equals(user.getPersonnr())) {
					String barcode = rs.getString(1);
					Boolean deposited = rs.getBoolean(3);
					bicycles.add(new Bicycle(barcode, user, deposited));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL Message getBicycle: " + e.getMessage());
		}
		return bicycles;
	}

	@Override
	public ResultSet extractUsers() {
		ResultSet rs = null;
		try {
			rs = conn.createStatement().executeQuery("SELECT * FROM users");
		} catch (SQLException e) {
			System.out.println("Användartabell saknas. SQL Message: "
					+ e.getMessage());
		}
		return rs;
	}

	@Override
	public Bicycle createBicycle(User user) throws Exception {
		if (user == null)
			throw new Exception("Användaren är felaktigt");
		String chars = "0123456789";
		while (true) {
			StringBuilder sb = new StringBuilder();
			while (sb.length() < 5) {
				int index = (int) (Math.random() * chars.length());
				sb.append(chars.charAt(index));
			}
			String barcode = sb.toString();
			if (getBicycle(barcode) == null) {
				return new Bicycle(barcode, user, false);
			}
		}
	}

	@Override
	public boolean insertBicycle(Bicycle bicycle) {
		if (bicycle == null) {
			System.out
					.println("Cykeln bicycle som skulle läggas till är null.");
			return false;
		}
		System.out.print("Cykeln med streckkod " + bicycle.getBarcode() + " ");
		sql = "INSERT INTO bicycles " + "VALUES (" + "'" + bicycle.getBarcode()
				+ "', " + "'" + bicycle.getOwner().getPersonnr() + "', "
				+ bicycle.isDeposited() + ")";
		try {
			conn.createStatement().executeUpdate(sql);
			System.out.println("tillagd.");
			return true;
		} catch (SQLException e) {
			System.out.println("inte tillagd. SQL Message: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteBicycle(Bicycle bicycle) {
		if (bicycle == null) {
			System.out.println("Cykeln bicycle som skulle tas bort är null.");
			return false;
		}
		String barcode = bicycle.getBarcode();
		if (getBicycle(barcode) == null) {
			System.out.println("Cykeln med streckkod " + barcode
					+ " inte borttagen, finns inte.");
			return false;
		}
		System.out.print("Cykeln med användare "
				+ bicycle.getOwner().getFirstName() + " ");
		sql = "DELETE FROM bicycles WHERE barcode = '" + barcode + "'";
		try {
			conn.createStatement().executeUpdate(sql);
			System.out.println("borttagen.");
			return true;
		} catch (SQLException e) {
			System.out
					.println("inte borttagen. SQL Message: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean updateBicycle(Bicycle bicycle) {
		if (bicycle == null) {
			System.out.println("Cykeln bicycle som skulle uppdateras är null.");
			return false;
		}
		System.out.print("Cykel med ägare " + bicycle.getOwner().getFirstName()
				+ " ");
		sql = "UPDATE bicycles SET " + "user_personnr = '"
				+ bicycle.getOwner().getPersonnr() + "', " + "deposited = "
				+ bicycle.isDeposited() + " " + "WHERE barcode = '"
				+ bicycle.getBarcode() + "'";
		try {
			conn.createStatement().executeUpdate(sql);
			System.out.println("uppdaterad.");
			return true;
		} catch (SQLException e) {
			System.out.println("inte uppdaterad. SQL Message: "
					+ e.getMessage());
			return false;
		}
	}

	public boolean depositBicycle(Bicycle bc) {
		if (bc == null) {
			return false;
		}
		bc.deposit();
		updateBicycle(bc);
		return true;
	}

	public boolean withdrawBicycle(Bicycle bc) {
		if (bc == null) {
			return false;
		}
		bc.withdraw();
		updateBicycle(bc);
		return true;
	}

	public boolean removeFreeSlot(User user, int slots) {
		if (user == null) {
			return false;
		}
		user.removeFreeSlot(slots);
		updateUser(user);
		return true;
	}

	public boolean addFreeSlot(User user, int slots) {
		if (user == null) {
			return false;
		}
		user.addFreeSlot(slots);
		updateUser(user);
		return true;
	}

	@Override
	public Bicycle getBicycle(String barcode) {
		if (barcode == null) {
			System.out
					.println("Cykeln med sträckkod barcode som skulle hittas är null.");
			return null;
		}
		ResultSet rs = extractBicycles();
		if (rs == null)
			return null;
		try {
			rs.beforeFirst();
			while (rs.next()) {
				if (rs.getString(1).equals(barcode)) {
					String personnr = rs.getString(2);
					Boolean deposited = rs.getBoolean(3);
					return new Bicycle(barcode, getUser(personnr), deposited);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL Message getBicycle: " + e.getMessage());
		}
		return null;
	}

	@Override
	public ResultSet extractBicycles() {
		ResultSet rs = null;
		try {
			rs = conn.createStatement().executeQuery("SELECT * FROM bicycles");
		} catch (SQLException e) {
			System.out.println("Cykeltabell saknas. SQL Message: "
					+ e.getMessage());
		}
		return rs;
	}

	@Override
	public boolean reserveSlot(User user, int slots) {
		if (user == null || slots < 0) {
			return false;
		}
		user.addReserverdSlot(slots);
		user.addFreeSlot(slots);
		updateUser(user);
		return true;
	}

	@Override
	public boolean removeReservedSlot(User user, int slots) {
		if (user == null || slots < 0) {
			return false;
		}
		user.removeReservedSlot(slots);
		user.removeFreeSlot(slots);
		updateUser(user);
		return true;
	}

	@Override
	public boolean clearInactiveUsers() {
		ResultSet rs = extractUsers();
		boolean cleared = false;
		try {
			rs.beforeFirst();
			while (rs.next()) {
				String personnr = rs.getString(1);
				int reservedSlots = rs.getInt(7);
				if (reservedSlots == 0) {
					System.out.print("Användare " + personnr + " ");
					sql = "DELETE FROM users WHERE personnr = '" + personnr
							+ "'";
					try {
						conn.createStatement().executeUpdate(sql);
						System.out.println("borttagen.");
					} catch (SQLException e) {
						System.out.println("inte borttagen. SQL Message: "
								+ e.getMessage());
					}
					cleared = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cleared;
	}
	
	@Override
	public int getReservedSlots() {
		ResultSet rs = extractUsers();
		int slots = 0;
		try {
			rs.beforeFirst();
			while(rs.next()) {
				slots += rs.getInt(7);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return slots;
	}
	
	@Override
	public int getFreeSlots() {
		ResultSet rs = extractUsers();
		int slots = 0;
		try {
			rs.beforeFirst();
			while(rs.next()) {
				slots += rs.getInt(8);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return slots;
	}
	
	@Override
	public int getNbrOfBicycles() {
		ResultSet rs = extractUsers();
		int slots = 0;
		try {
			rs.beforeFirst();
			while(rs.next()) {
				slots += rs.getInt(9);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return slots;
	}

	public boolean isPNRValid(String pnr) {
		boolean isValid = false;

		// Initiera reg ex för PNR.
		String expression = "^\\d{6}[- ]\\d{4}$";
		CharSequence inputStr = pnr;
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(inputStr);

		if (matcher.matches() && check(pnr))
			isValid = true;
		return isValid;
	}

	private boolean check(String pnr) {
		StringBuilder sb = new StringBuilder(pnr);
		sb.deleteCharAt(6);
		int[] digits = new int[10];
		for (int i = 0; i < sb.length(); i++)
			digits[i] = Character.digit(sb.charAt(i), 10);

		int sum = 0;
		int length = digits.length;
		for (int i = 0; i < length; i++) {
			// siffrorna i omvänd ordning
			int digit = digits[length - i - 1];

			// vart 2:e nummer multipliceras med 2
			if (i % 2 == 1)
				digit *= 2;
			sum += digit > 9 ? digit - 9 : digit;
		}
		return sum % 10 == 0;
	}

	private boolean nameIsValid(String name){

		if(name.matches("[A-ö-]{2,25}")){
			return true;
		}
		return false;
		
	}

	public static void main(String[] args) {
		DatabaseDriver db = null;
		try {
			db = new DatabaseDriver();
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC drivrutin hittades ej. SQL Message: "
					+ e.getMessage());
		} catch (SQLException e) {
			System.out
					.println("Gick ej att ansluta till databas. SQL Message: "
							+ e.getMessage());
		}

		// db.dropTables();
		// db.createTables();

		System.out.println();
		print(db.extractUsers());
		System.out.println();
		print(db.extractBicycles());
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(db.getReservedSlots());
	}

	public static void print(ResultSet rs) {
		if (rs == null)
			return;
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				if (i > 1) {
					System.out.print(" | ");
				}
				System.out.print(rsmd.getColumnName(i) + " "
						+ rsmd.getColumnTypeName(i));

			}
			rs.beforeFirst();
			while (rs.next()) {
				System.out.println();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					if (i > 1) {
						System.out.print(" | ");
					}
					int type = rsmd.getColumnType(i);
					if (type == Types.VARCHAR || type == Types.CHAR) {
						System.out.print(rs.getString(i));
					} else {
						System.out.print(rs.getLong(i));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
