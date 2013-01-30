package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoLibrary {
	private static java.sql.Connection c;

	public static Connection startConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/Library?"
							+ "user=root");

		} catch (SQLException e) {
			e.printStackTrace();

		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		
		return c;

	}

	public static void stopConnection() {
		if (c==null){
			return;
		}
		try {
			c.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}