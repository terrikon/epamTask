package dataBase.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;
import javax.sql.DataSource;

public class DaoLibrary {
	private static java.sql.Connection c;

	public static Connection startConnection() throws NamingException,
			SQLException, ClassNotFoundException {

		
		
	
		Class.forName("com.mysql.jdbc.Driver");
//		javax.naming.Context ct =
//			new javax.naming.InitialContext();
//				DataSource ds = (DataSource)ct.lookup("java:/comp/env/jdbc/Library");
//				Connection c = ds.getConnection("root", "root");
		 c = DriverManager
		 .getConnection("jdbc:mysql://localhost:3306/Library","root","root");

		return c;

	}

	public static void softStop(Connection c) {
		if (c == null) {
			return;
		}
		try {
			c.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void softStop(ResultSet rs) {
		if (rs == null) {
			return;
		}
		try {
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void softStop(Statement st) {
		if (st == null) {
			return;
		}
		try {
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void softStop(PreparedStatement pst) {
		if (pst == null) {
			return;
		}
		try {
			pst.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
