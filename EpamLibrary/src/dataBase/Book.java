package dataBase;


import java.util.LinkedList;

public class Book {
	private int id;
	private String name;
	private String author;
	private int year;
	private int availability;
	private LinkedList<Book> books;

	public int getId() {
		return id;
	}

	public int getAvailability() {
		return availability;
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}

	public int getYear() {
		return year;
	}

	public void setId(int BookId) {
		id = BookId;
	}

	public void setName(String BookName) {
		name = BookName;
	}

	public void setAuthor(String BookAuthor) {
		author = BookAuthor;
	}

	public void setYear(int BookYear) {
		year = BookYear;
	}

	public void setAvailability(int BookAvailability) {
		availability = BookAvailability;
	}

}
