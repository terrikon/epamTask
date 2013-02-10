package dataBase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import dataBase.User;

public class UserDaoLibrary {
	private static PreparedStatement ps;
	private static Connection c;
	private static ResultSet result;

	public static User getUserByLogin(String login) {
		User currUser = new User();

		try {
			c = DaoLibrary.startConnection();
			ps = c.prepareStatement("SELECT * FROM User WHERE login=?; ");

			ps.setString(1, login);
			result = ps.executeQuery();

			while (result.next()) {

				currUser.setId(result.getInt("id"));
				currUser.setLogin(result.getString("login"));
				currUser.setPass(result.getString("pass"));
				currUser.setAccess(result.getInt("access"));

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}

		finally {

			DaoLibrary.softStop(result);
			DaoLibrary.softStop(ps);
			DaoLibrary.softStop(c);
		}
		return currUser;

	}
}
