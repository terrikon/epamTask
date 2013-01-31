package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDaoLibrary {
	private static PreparedStatement ps;

	private static Book getBookBy(String columnName, Object value) {
		Book currBook;
		Connection c = DaoLibrary.startConnection();
		try {
			ps = c.prepareStatement("SELECT * FROM Book WHERE ?=?; ");

			ps.setString(1, columnName);
			ps.setObject(2, value);
			ResultSet result = ps.executeQuery();

			while (result.next()) {
				currBook = new Book();
				currBook.setId(result.getInt("id"));
				currBook.setName(result.getString("name"));
				currBook.setAuthor(result.getString("author"));
				currBook.setYear(result.getInt("year"));
				currBook.setAvailability(result.getInt("availability"));
				
			}
			DaoLibrary.stopConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return currBook;

	}

	public static Book getBookById(String column, int BookId) {
		return getBookBy(column, BookId);
	}
	public static Book getBookByName(String column, String BookName) {
		return getBookBy(column, BookName);
	}
	

	public static void main(String[] args) {
		Book book1 = new Book();
		System.out.println(book1 = getBookById("id", 1));
	}
}
