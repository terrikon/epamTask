package dataBase.dao;

import dataBase.Book;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//смотри, я убираю поля books и result, когда в Киеве буду, объясню подробно почему

public class BookDao {

    private PreparedStatement ps;
	private Connection c;

	public void setBookAvailability(String bookId, int bookAvailability) {
		try {
			c = DaoLibrary.startConnection();
			ps = c.prepareStatement("UPDATE Library.Book SET availability=? "
					+ "WHERE id=?;");
			ps.setString(2, bookId);
			ps.setInt(1, bookAvailability);
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

	public Book getBookById(String value) {
		Book book = new Book();
		try {
			c = DaoLibrary.startConnection();

			ps = c.prepareStatement("SELECT * FROM Book WHERE id =?;  ");
			ps.setString(1, value);
            ResultSet result = ps.executeQuery();
			book = parseBook(result);

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
		return book;

	}


    //зачем здесь возвращать список книг? ведь нужна только одна
	public Book getBookByName(String bookName) {
        Book book = new Book();
		try {

			c = DaoLibrary.startConnection();
			ps = c.prepareStatement("SELECT * FROM Book WHERE name =?;  ");
			ps.setString(1, bookName);
			ResultSet result = ps.executeQuery();
			book = parseBook(result);

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
		return book;

	}

    //method renamed
	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<Book>();
		try {
			Connection c = DaoLibrary.startConnection();

			ps = c.prepareStatement("SELECT * FROM Book;");
			ResultSet result = ps.executeQuery();
			addNewBooks(result, books);

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
		return books;

	}

	private List<Book> addNewBooks(ResultSet booksResultSet, List<Book> books)
			throws SQLException {
		Book currBook;
		while (booksResultSet.next()) {
			books.add(parseBook(booksResultSet));
		}
		return books;
	}

    private Book parseBook(ResultSet booksResultSet) throws SQLException{
        Book book = new Book();
        book.setId(booksResultSet.getInt("id"));
        book.setName(booksResultSet.getString("name"));
        book.setAuthor(booksResultSet.getString("author"));
        book.setYear(booksResultSet.getInt("year"));
        book.setAvailability(booksResultSet.getInt("availability"));
        return  book;
    }

}
