package dataBase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import dataBase.Order;
import dataBase.User;

public class OrderDaoLibrary {
	private static PreparedStatement ps;
	private static Connection c;
	private static ResultSet result;

	public static Order getOrderrById(int orderId) {
		Order currOrder = new Order();

		try {
			c = DaoLibrary.startConnection();
			ps = c.prepareStatement("SELECT * FROM Order WHERE id=?; ");
			ps.setInt(1, orderId);
			result = ps.executeQuery();

			while (result.next()) {
				currOrder.setId(result.getInt("id"));
				currOrder.setUserId(result.getInt("id_user"));
				currOrder.setBookId(result.getInt("id_book"));
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
		return currOrder;

	}	
	public static void insertInOrder(String orderBookId, int orderUserId){
		try {
			c = DaoLibrary.startConnection();
			ps = c.prepareStatement("INSERT INTO Library.Order(id_user, id_book) " +
					"VALUES (?, ?);");
			ps.setInt(1, orderUserId);
			ps.setString(2, orderBookId);
			System.out.println(ps);
			ps.executeUpdate();
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}

		finally {

			DaoLibrary.softStop(ps);
			DaoLibrary.softStop(c);
		}
	}
	public static void deleteFromOrderById(int orderId){
		try {
			c = DaoLibrary.startConnection();
			ps = c.prepareStatement("DELETE FROM Library.Order WHERE id=?;");
			ps.setInt(1, orderId);
			System.out.println(ps);
			ps.executeUpdate();
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}

		finally {

			DaoLibrary.softStop(ps);
			DaoLibrary.softStop(c);
		}
	}
	
		public static void main(String[] args) {
			insertInOrder("1", 1);
		}

}
