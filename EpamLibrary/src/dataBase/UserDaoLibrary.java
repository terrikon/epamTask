package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoLibrary {

	public static User getUserByLogin(String login) {
		User currUser = new User();
		Connection c = DaoLibrary.startConnection();
		try {
			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM User WHERE login=?; ");
			
			ps.setString(1, login);
			ResultSet result = ps.executeQuery();
			
			while (result.next()) {
				
				currUser.setId(result.getInt("id"));
				currUser.setLogin(result.getString("login"));
				currUser.setPass(result.getString("pass"));
				currUser.setAccess(result.getInt("access"));
				
			}
			DaoLibrary.stopConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return currUser;

	}
}
