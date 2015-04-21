package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final String DB_URL = "jdbc:mysql://sql4.freesqldatabase.com:3306/sql474777";

	private final String USER = "sql474777";
	private final String PASS = "cS9!pG3*";

	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement prepstmt = null;
	private ResultSet rs = null;

	public Database() {
		try {
			// Registrera JBDC drivrutin
			Class.forName(JDBC_DRIVER);

			// Anslut
			System.out.println("Ansluter till databas " + DB_URL);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void readDB(String sql) {
		try {
			// Exekverar villkorssats
			System.out.println("Skapar villkorssats...");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(String name, String mail) {
		if (find(name) != -1) {
			System.out.println(name + " finns redan.");
		} else {
			String sql = "INSERT INTO users (id, name, mail) "
					+ "VALUES (default, '" + name + "', '" + mail + "')";
			try {
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean remove(String name) {
		System.out.println("Försöker ta bort " + name);
		int id = find(name);
		if (id == -1) {
			System.out.println("Hittas ej");
			return false;
		}
		String sql = "DELETE FROM users " + "WHERE id = " + id;
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(name + " med id " + id + " är borttagen.");
		return true;
	}

	private int find(String name) {
		// Hitta användare och retunera id
		int i = -1;
		try {
			while (rs.next()) {
				// Hämta kolumnnamn
				String rsname = rs.getString("name");
				if (name.equals(rsname))
					i = rs.getInt("id");
			}
			rs.beforeFirst();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public void extract() {
		try {
			// Hämta data från resultat
			while (rs.next()) {
				// Hämta kolumnnamn
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String mail = rs.getString("mail");

				// Visa värden
				System.out.print("ID: " + id);
				System.out.print(", Name: " + name);
				System.out.println(", Mail: " + mail);
			}
			rs.beforeFirst();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
			System.out.println("Resultatuppställning, villkorssats och anslutning stängd");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Database db = new Database();
		db.readDB("SELECT * FROM users");
		db.extract();
		db.insert("Emma", "mail");
		db.remove("Emma");
		db.readDB("SELECT * FROM users");
		db.extract();
//		db.close();

		System.out.println("Klart.");
	}
}