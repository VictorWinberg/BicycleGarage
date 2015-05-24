import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import database.DatabaseDriver;


public class testPINgenerator {

	@Test
	public void testGeneratePIN() {
		DatabaseDriver dbd = null;
		try {
			dbd = new DatabaseDriver();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] list = new String[999999];
		for(int i = 0; i < 999999; i++){
			list[i] = dbd.generatePIN();
		}
		for(int i = 0; i < 999999; i++){
			for(int j = i; j < 999999; j++){
				assertFalse(list[i].equals(list[j]));
			}
		}
	}

}
