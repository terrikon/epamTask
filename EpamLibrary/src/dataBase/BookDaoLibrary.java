package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;

public class BookDaoLibrary {
	private static PreparedStatement ps;
	private static LinkedList<Book> books = new LinkedList<Book>();
	private static ResultSet result;
	private static Connection c;

	public static List<Book> getBookById(int value) {
		books.clear();
		try {
			c = DaoLibrary.startConnection();

			ps = c.prepareStatement("SELECT * FROM Book WHERE id =?;  ");
			ps.setInt(1, value);
			result = ps.executeQuery();
			addNewBooks(result);

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
		return books;

	}

	public static List<Book> getBookByName(String bookName) {
		books.clear();
		try {
			c = DaoLibrary.startConnection();

			ps = c.prepareStatement("SELECT * FROM Book WHERE name =?;  ");
			ps.setString(1, bookName);
			ResultSet result = ps.executeQuery();
			addNewBooks(result);

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
		return books;

	}

	public static List<Book> getBookAll() {
		books.clear();
		try {
			Connection c = DaoLibrary.startConnection();

			ps = c.prepareStatement("SELECT * FROM Book;");
			ResultSet result = ps.executeQuery();
			addNewBooks(result);

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
		return books;

	}

	private static List<Book> addNewBooks(ResultSet bookResult)
			throws SQLException {
		Book currBook;
		while (bookResult.next()) {
			currBook = new Book();
			currBook.setId(bookResult.getInt("id"));
			currBook.setName(bookResult.getString("name"));
			currBook.setAuthor(bookResult.getString("author"));
			currBook.setYear(bookResult.getInt("year"));
			currBook.setAvailability(bookResult.getInt("availability"));
			books.add(currBook);
		}
		return books;
	}

}
