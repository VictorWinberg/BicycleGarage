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
				+ "first_name VARCHAR( 25 ) NOT NULL ,"
				+ "last_name VARCHAR( 25 ) NOT NULL ,"
				+ "mail VARCHAR( 25 ) NOT NULL ,"
				+ "phonenr VARCHAR( 10 ) NOT NULL ,"
				+ "pin VARCHAR( 6 ) NOT NULL ,"
				+ "reservedSlots INT ( 4 ) NOT NULL ,"
				+ "freeSlots INT ( 4 ) NOT NULL ,"
				+ "PRIMARY KEY (  personnr ))";
		try {
			conn.createStatement().executeUpdate(sql);
			System.out.println("skapad.");
			created = true;
		} catch (SQLException e) {
			System.out.println("inte skapad, finns redan.");
		}
		System.out.print("Cykeltabell ");
		sql = "CREATE TABLE  bicycles ("
				+ "barcode VARCHAR( 5 ) NOT NULL ,"
				+ "user_personnr VARCHAR( 11 ) NOT NULL ,"
				+ "deposited BOOLEAN NOT NULL ,"
				+ "PRIMARY KEY (  barcode ))";
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
		if (EmailValidator.getInstance().isValid(mail) && isPNRValid(personnr)) {
			String chars = "0123456789";
			while (true) {
				StringBuilder sb = new StringBuilder();
				while (sb.length() < 6) {
					int index = (int) (Math.random() * chars.length());
					sb.append(chars.charAt(index));
				}
				String pin = sb.toString();
				if (getUserWithPIN(pin) == null)
					return new User(personnr, first_name, last_name, mail, phonenr, pin, 0, 0);
			}
		} else if(EmailValidator.getInstance().isValid(mail)) {
			throw new Exception("Ditt personnummer är felaktigt");
		} else throw new Exception(isPNRValid(personnr) ? 
					"Din mailadress är felaktigt" : "Ditt personnummer och din mailadress är felaktig");
	}

	@Override
	public boolean insertUser(User user) {
		if (user == null) {
			System.out.println("Användare user som skulle läggas till är null.");
			return false;
		}
		System.out.print("Användare " + user.getFirstName() + " ");
		sql = "INSERT INTO users " + "VALUES (" 
				+ "'" + user.getPersonnr() + "', " 
				+ "'" + user.getFirstName() + "', " 
				+ "'" + user.getLastName() + "', " 
				+ "'" + user.getMail() + "', "
				+ "'" + user.getPhonenr() + "', " 
				+ "'" + user.getPIN() + "', "
				+ user.getReserverdSlots() + ", " 
				+ user.getFreeSlots() + ")";
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
		} else if(getUser(user.getPersonnr()) == null) {
			System.out.println("Användare " + user.getFirstName() + " inte borttagen, finns inte.");
			return false;
		} else if(!getBicycles(user).isEmpty()) {
			System.out.println("Användare " + user.getFirstName() + " inte borttagen, har cyklar");
			return false;
		}
		System.out.print("Användare " + user.getFirstName() + " ");
		sql = "DELETE FROM users WHERE personnr = '" + user.getPersonnr() + "'";
		try {
			conn.createStatement().executeUpdate(sql);
			System.out.println("borttagen.");
			return true;
		} catch (SQLException e) {
			System.out.println("inte borttagen. SQL Message: " + e.getMessage());
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
		sql = "UPDATE users SET "
			+ "first_name = '" + user.getFirstName() + "', " 
			+ "last_name = '" + user.getLastName() + "', " 
			+ "mail = '" + user.getMail() + "', "
			+ "phonenr = '" + user.getPhonenr() + "', " 
			+ "pin = '" + user.getPIN() + "', "
			+ "reservedSlots = " + user.getReserverdSlots() + ", " 
			+ "freeSlots = " + user.getFreeSlots() + " "
			+ "WHERE personnr = '" + user.getPersonnr() + "'";
		try {
			conn.createStatement().executeUpdate(sql);
			System.out.println("uppdaterad.");
			return true;
		} catch (SQLException e) {
			System.out.println("inte uppdaterad. SQL Message: " + e.getMessage());
			return false;
		}
	}
	
	@Override
	public User getUser(String personnr) {
		if (personnr == null) {
			System.out.println("Användare med personnr som skulle hittas är null.");
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
							rs.getString(6), rs.getInt(7), rs.getInt(8));
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
							rs.getString(6), rs.getInt(7), rs.getInt(8));
			}
		} catch (SQLException e) {
			System.out.println("SQL Message i getUserWithPIN: " + e.getMessage());
		}
		return user;
	}

	@Override
	public List<Bicycle> getBicycles(User user) {
		if (user == null) {
			System.out.println("Cyklar med användaren user som skulle hittas är null.");
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
		if(user == null)
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
			System.out.println("Cykeln bicycle som skulle läggas till är null.");
			return false;
		}
		System.out.print("Cykeln med streckkod " + bicycle.getBarcode() + " ");
		sql = "INSERT INTO bicycles " + "VALUES (" 
				+ "'" + bicycle.getBarcode() + "', "
				+ "'" + bicycle.getOwner().getPersonnr() + "', "
				+ bicycle.isDeposited() + ")";
		try {
			conn.createStatement().executeUpdate(sql);
			System.out.println("tillagd.");
			User user = bicycle.getOwner();
			user.addReserverdSlot();
			user.addFreeSlot();
			updateUser(user);
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
		if(getBicycle(barcode) == null) {
			System.out.println("Cykeln med streckkod " + barcode + " inte borttagen, finns inte.");
			return false;
		}
		System.out.print("Cykeln med användare " + bicycle.getOwner().getFirstName() + " ");
		sql = "DELETE FROM bicycles WHERE barcode = '" + barcode + "'";
		try {
			conn.createStatement().executeUpdate(sql);
			User user = bicycle.getOwner();
			user.removeReservedSlot();
			user.removeFreeSlot();
			updateUser(user);
			System.out.println("borttagen.");
			return true;
		} catch (SQLException e) {
			System.out.println("inte borttagen. SQL Message: " + e.getMessage());
			return false;
		}
	}
	
	@Override
	public boolean updateBicycle(Bicycle bicycle) {
		if (bicycle == null) {
			System.out.println("Cykeln bicycle som skulle uppdateras är null.");
			return false;
		}
		System.out.print("Cykel med ägare " + bicycle.getOwner().getFirstName() + " ");
		sql = "UPDATE bicycles SET "
			+ "user_personnr = '" + bicycle.getOwner().getPersonnr() + "', " 
			+ "deposited = " + bicycle.isDeposited() + " "
			+ "WHERE barcode = '" + bicycle.getBarcode() + "'";
		try {
			conn.createStatement().executeUpdate(sql);
			System.out.println("uppdaterad.");
			return true;
		} catch (SQLException e) {
			System.out.println("inte uppdaterad. SQL Message: " + e.getMessage());
			return false;
		}
	}

	@Override
	public Bicycle getBicycle(String barcode) {
		if (barcode == null) {
			System.out.println("Cykeln med sträckkod barcode som skulle hittas är null.");
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
	public boolean clearInactiveUsers() {
		ResultSet rs = extractUsers();
		boolean cleared = false;
		try {
			rs.beforeFirst();
			while(rs.next()) {
				String personnr = rs.getString(1);
				int reservedSlots = rs.getInt(7);
				if(reservedSlots == 0) {
					System.out.print("Användare " + personnr + " ");
					sql = "DELETE FROM users WHERE personnr = '" + personnr + "'";
					try {
						conn.createStatement().executeUpdate(sql);
						System.out.println("borttagen.");
					} catch (SQLException e) {
						System.out.println("inte borttagen. SQL Message: " + e.getMessage());
					}
					cleared = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cleared;
	}
	
	private static boolean isPNRValid(String pnr) {
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

	private static boolean check(String pnr) {
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

	public static void main(String[] args) {
		DatabaseDriver db = null;
		try {
			db = new DatabaseDriver();
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC drivrutin hittades ej. SQL Message: "
					+ e.getMessage());
		} catch (SQLException e) {
			System.out.println("Gick ej att ansluta till databas. SQL Message: "
							+ e.getMessage());
		}

		int nbr = 0;
		for (int i = 0; i < 100; i++) {
			String pnr = "001122-33"+i;
			if(isPNRValid(pnr)){
				System.out.println(pnr);
				nbr++;
			}
		}
		 System.out.println(nbr);
		
//		db.dropTables();
//		db.createTables();

//		User u = db.getUserWithPIN("754532");
//		if(u != null){
//			System.out.println(u.toString());
//			db.deleteUser(u);
//		} else System.out.println("User not found");

		try{User victor = db.createUser("950407-0856", "Victor", "Winberg",
				"cool@swag.com", "0707133700");
		db.insertUser(victor);
		
		User pelle = db.createUser("950102-0300", "Pelle", "Nilsson",
				"pelle@nilsson.se", "0707001122");
		db.insertUser(pelle);
		
		User notfake = db.createUser("950123-4562", "Fake", "Fakesson",
				"swag@lol.se", "0707123456");
		db.insertUser(notfake);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
//		User u = db.getUser("950123-4562");
//		if(u != null) {
//			Bicycle bicycle = db.createBicycle(u);
//			db.insertBicycle(bicycle);
//		}
		
		Bicycle bike = db.getBicycle("49896");
		if(bike != null)
			bike.withdraw();
		
		db.updateBicycle(bike);
		
//		db.clearInactiveUsers();
		
//		db.deleteBicycle(db.getBicycle("77032"));
//		db.deleteBicycle(db.getBicycle("50582"));

//		if(db.getUserWithPIN("553587") != null)
//			System.out.println("PIN 553587 finns");
//		else System.out.println("PIN 553587 finns inte");
		
//		List<Bicycle> list = db.getBicycles(db.getUser("950407-0856"));
//		if(list == null)
//			System.out.println("Användaren är felaktig.");
//		else if(list.isEmpty())
//			System.out.println("Användaren har inga cyklar.");
//		else {
//			System.out.println();
//			for (Bicycle bicycle : list) {
//				System.out.print(bicycle.getBarcode() + ", ");
//			}
//			System.out.println();
//		}
		
		System.out.println();
		print(db.extractUsers());
		System.out.println();
		print(db.extractBicycles());
	}
	
	public static void print(ResultSet rs) {
		if(rs == null)
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
