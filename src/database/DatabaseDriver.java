package database;

import interfaces.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;

public class DatabaseDriver implements Database {

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://sql4.freesqldatabase.com:3306/sql474777";

	private static final String USER = "sql474777";
	private static final String PASS = "cS9!pG3*";

	private Connection conn = null;
	private Statement stmt = null;
	private String sql;

	public DatabaseDriver() throws ClassNotFoundException, SQLException {
		// Registrera JDBC drivrutin
		Class.forName(JDBC_DRIVER);

		// Anslut
		System.out.print("Databasen "+ DB_URL);
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		System.out.println(" ansluten.");

		// Villkorssats
		System.out.print("Villkorssats");
		stmt = conn.createStatement();
		System.out.println(" skapad.");
	}

	@Override
	public boolean createTables() {
		boolean created = false;
		System.out.println();
		System.out.print("Användartabell ");
		sql = "CREATE TABLE  users ("
				+ "personnr VARCHAR( 11 ) NOT NULL ,"
				+ "first_name VARCHAR( 25 ) NOT NULL ,"
				+ "last_name VARCHAR( 25 ) NOT NULL ,"
				+ "mail VARCHAR( 25 ) NOT NULL ,"
				+ "phonenr VARCHAR( 10 ) NOT NULL ,"
				+ "pin VARCHAR( 6 ) NOT NULL ,"
				+ "reservedSlots INT ( 4 ) NOT NULL ,"
				+ "freeSlots INT ( 4 ) NOT NULL ,"
				+ "PRIMARY KEY (  personnr ))";
		try {
			stmt.executeUpdate(sql);
			System.out.println("skapad.");
			created = true;
		} catch (SQLException e) {
			System.out.println("inte skapad, finns redan.");			
		}
		System.out.print("Cykeltabell ");
		sql = "CREATE TABLE  bicycles ("
				+ "user_personnr VARCHAR( 11 ) NOT NULL ,"
				+ "barcode INT( 5 ) NOT NULL ,"
				+ "PRIMARY KEY (  user_personnr ))";
		try {
			stmt.executeUpdate(sql);
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
			stmt.executeUpdate(sql);
			System.out.println("borttagen.");
			dropped = true;
		} catch (SQLException e) {
			System.out.println("inte borttagen, finns inte.");
		}
		System.out.print("Cykeltabellen ");
		sql = "DROP TABLE bicycles";
		try {
			stmt.executeUpdate(sql);
			System.out.println("borttagen.");
			dropped = true;
		} catch (SQLException e) {
			System.out.println("inte borttagen, finns inte.");
		}
		return dropped;
	}
	
	public static User createUser(String personnr, String first_name, String last_name,
			String mail, String phonenr) {
		User newUser = null;
		if(EmailValidator.getInstance().isValid(mail) && isSSNValid(personnr)) {
			newUser = new User(personnr, first_name, last_name, mail, phonenr);
		}
		return newUser;
	}
	
	@Override
	public boolean insertUser(User user) {
		System.out.print("Användare " + user.getFirstName() + " ");
		sql = "INSERT INTO users "
			+ "VALUES ("
			+ "'" + user.getPersonnr() + "', "
			+ "'" + user.getFirstName() + "', "
			+ "'" + user.getLastName() + "', "
			+ "'" + user.getMail() + "', "
			+ "'" + user.getPhonenr() + "', "
			+ "'" + user.getPIN() + "', "
			+ user.getReserverdSlots() + ", "
			+ user.getFreeSlots()
			+ ")";
		try {
			stmt.executeUpdate(sql);
			System.out.println("tillagd.");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteUser(User user) {
		System.out.print("Användare " + user.getFirstName() + " ");
		sql = "DELETE FROM users WHERE personnr = '" + user.getPersonnr() + "'";
		try {
			stmt.executeUpdate(sql);
			System.out.println("borttagen.");
			return true;
		} catch (SQLException e) {
			System.out.println("inte borttagen, finns inte.");
			return false;
		}
	}
	
	public boolean deleteUser(String personnr) {
		System.out.print("Användare med personnummer " + personnr + " ");
		sql = "DELETE FROM users WHERE personnr = '" + personnr + "'";
		try {
			stmt.executeUpdate(sql);
			System.out.println("borttagen.");
			return true;
		} catch (SQLException e) {
			System.out.println("inte borttagen, finns inte.");
			return false;
		}
	}

	@Override
	public boolean insertBicycle(Bicycle bicycle) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBicycle(Bicycle bicycle) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public User getUser(String personnr) {
		User user = null;
		ResultSet rs = extractUsers();
		try {
			while (rs.next()) {
				if(rs.getString(1).equals(personnr)) {
					user = new User(rs.getString(1), 
							rs.getString(2), 
							rs.getString(3), 
							rs.getString(4), 
							rs.getString(5), 
							rs.getString(6), 
							rs.getInt(7), 
							rs.getInt(8));
					rs.beforeFirst();
					return user;
				}
			}
			rs.beforeFirst();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public User getUserWithPIN(String pin) {
		User user = null;
		ResultSet rs = extractUsers();
		try {
			while (rs.next()) {
				if(rs.getString(6).equals(pin)) {
					user = new User(rs.getString(1), 
							rs.getString(2), 
							rs.getString(3), 
							rs.getString(4), 
							rs.getString(5), 
							rs.getString(6), 
							rs.getInt(7), 
							rs.getInt(8));
					rs.beforeFirst();
					return user;
				}
			}
			rs.beforeFirst();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public ResultSet extractUsers() {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery("SELECT * FROM users");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public ResultSet extractBicycles() {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery("SELECT * FROM bicycles");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public static void main(String[] args) {
		DatabaseDriver db = null;
		try {
			db = new DatabaseDriver();
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC drivrutin hittades ej");
		} catch (SQLException e) {
			System.out.println("Gick ej att ansluta till databas");
		}
		
//		int nbr = 0;
//		for (int i = 0; i < 100; i++) {
//			String ssn = "950123-45"+i;
//			if(isSSNValid(ssn)){
//				System.out.println(ssn);
//				nbr++;
//			}
//		}
//		System.out.println(nbr);
		
//		db.dropTables();
//		db.createTables();
		
//		User u = db.getUserWithPIN("754532");
//		if(u != null){
//			System.out.println(u.toString());
//			db.deleteUser(u);
//		} else System.out.println("User not found");
		
//		User victor = new User("950407-1337", "Victor", "Winberg", "cool@swag.com", "0707133700");
//		victor.generatePIN();
//		db.insertUser(victor);
		
//		ResultSet rs = db.extractUsers();
//		if(rs != null) {
//			try {
//				ResultSetMetaData rsmd = rs.getMetaData();
//				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//					if (i > 1) {
//						System.out.print(" | ");
//					}
//					System.out.print(rsmd.getColumnName(i) + " " + rsmd.getColumnTypeName(i));
//					
//				}
//				while (rs.next()) {
//					System.out.println();
//					for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//						if (i > 1) {
//							System.out.print(" | ");
//						}
//						int type = rsmd.getColumnType(i);
//						if (type == Types.VARCHAR || type == Types.CHAR) {
//							System.out.print(rs.getString(i));
//						} else {
//							System.out.print(rs.getLong(i));
//						}
//					}
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
	}
	
	private static boolean isSSNValid(String ssn){  
		boolean isValid = false;  

		// Initiera reg ex för SSN.
		String expression = "^\\d{6}[- ]\\d{4}$"; 
		CharSequence inputStr = ssn;
		Pattern pattern = Pattern.compile(expression);  
		Matcher matcher = pattern.matcher(inputStr); 
		
		if(matcher.matches() && check(ssn)){  
			isValid = true;  
		}
		return isValid;  
	}  

	private static boolean check(String ssn) {
		StringBuilder sb = new StringBuilder(ssn);
		sb.deleteCharAt(6);
		int[] digits = new int[10];
		for (int i = 0; i < sb.length(); i++) {
			digits[i] = Character.digit(sb.charAt(i), 10);
		}
		
		int sum = 0;
		int length = digits.length;
		for (int i = 0; i < length; i++) {

			// siffrorna i omvänd ordning
			int digit = digits[length - i - 1];

			// vart 2:e nummer multipliceras med 2
			if (i % 2 == 1) {
				digit *= 2;
			}
			sum += digit > 9 ? digit - 9 : digit;
		}
		return sum % 10 == 0;
	}

}