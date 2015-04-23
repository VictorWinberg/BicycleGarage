package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class Database {

	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final String DB_URL = "jdbc:mysql://sql4.freesqldatabase.com:3306/sql474777";
	
	private final String USER = "sql474777";
	private final String PASS = "cS9!pG3*";

	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement prepstmt = null;
	private ResultSet rs = null;

	private String sql;

	public Database() throws ClassNotFoundException, SQLException {
		// Registrera JBDC drivrutin
		Class.forName(JDBC_DRIVER);

		// Anslut
		System.out.println("Ansluter till databas " + DB_URL);
		conn = DriverManager.getConnection(DB_URL, USER, PASS);

		// Villkorssats
		System.out.println("Skapar villkorssats...");
		stmt = conn.createStatement();

	}

	public void createUserTable(String named) throws SQLException {
		System.out.println("Skapar användartabell...");
		sql = "CREATE TABLE " + named + " (id INTEGER not NULL, "
				+ " first VARCHAR(255), " + " last VARCHAR(255), "
				+ " age INTEGER, " + " PRIMARY KEY ( id ))";

		stmt.executeUpdate(sql);
	}

	public void createBicyclesTable(String named) throws SQLException {
		System.out.println("Skapar cykeltabell...");
		sql = "CREATE TABLE " + named + " (id INTEGER not NULL, "
				+ " first VARCHAR(255), " + " last VARCHAR(255), "
				+ " age INTEGER, " + " PRIMARY KEY ( id ))";
		stmt.executeQuery(sql);
	}

	public void query(String sql) throws SQLException {
		// Exekverar sql-query
		rs = stmt.executeQuery(sql);
	}
	
	public void update(String sql) throws SQLException {
		// Exekverar sql-update
		stmt.executeUpdate(sql);
	}

	public void insert(String name, String mail, String table) throws SQLException {
		if (find(name) != -1) {
			System.out.println(name + " finns redan.");
		} else {
			String sql = "INSERT INTO " + table + " (id, name, mail) "
					+ "VALUES (default, '" + name + "', '" + mail + "')";
			stmt.executeUpdate(sql);
			System.out.println(name + " med mail " + mail + " tillagd");
		}
	}

	public boolean remove(String name) throws SQLException {
		System.out.println("Försöker ta bort " + name);
		int id = find(name);
		if (id == -1) {
			System.out.println("Hittas ej");
			return false;
		}
		sql = "DELETE FROM users " + "WHERE id = " + id;
		stmt.executeUpdate(sql);
		System.out.println(name + " med id " + id + " är borttagen.");
		return true;
	}

	private int find(String name) throws SQLException {
		// Hitta användare och retunera id
		int i = -1;
		while (rs.next()) {
			// Hämta kolumnnamn
			String rsname = rs.getString("name");
			if (name.equals(rsname))
				i = rs.getInt("id");
		}
		rs.beforeFirst();
		return i;
	}

	public void extract() throws SQLException {
		// Hämta data från resultat
		while (rs.next()) {
			ResultSetMetaData rsmd = rs.getMetaData();
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

			System.out.println();
		}
		rs.beforeFirst();
	}

	public void close() throws SQLException {
		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (conn != null)
			conn.close();
		System.out.println("ResultatSet, villkorssats och anslutning stängd");
	}

	public static void main(String[] args) {
		Database db = null;
		try {
			db = new Database();

//			db.createUserTable("UsersOfAnton");

//			db.update("INSERT INTO UsersOfAnton (id, first, age) "
//					+ "VALUES (2, 'AntonPlz', 1337)");
//			db.update("DROP TABLE ANTONS");

//			db.update("CREATE TABLE users2nd AS "
//					+ "SELECT * "
//					+ "FROM users");
			db.query("SELECT * FROM users2nd");
			db.extract();

			// db.execute("SELECT * FROM users");
			// db.extract();
			
//			db.query("SELECT * FROM users2nd");
//			db.extract();
			// db.execute("SELECT * FROM users");
			// db.extract();
			// db.remove("Emma");
			// db.execute("SELECT * FROM users");
			// db.extract();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
//		for (int i = 0; i < 3000; i++) {
//			try {
//				db.query("SELECT * FROM users2nd");
//				db.insert("Emma" + i, "mail", "users2nd");
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}				
//		}
		System.out.println("Klart.");
	}
}