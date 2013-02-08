package libraryTags;

import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import java.io.IOException;
import java.util.List;

import dataBase.Book;
import dataBase.BookDaoLibrary;

public class GetBooksTag extends TagSupport {
	private String bookName;

	public void setBookName(String name) {
		bookName = name;
	}

	@Override
	public int doStartTag() throws JspException {
		List<Book> books = BookDaoLibrary.getBookByName(bookName);

		try {
			JspWriter out = pageContext.getOut();
			if (!books.isEmpty()) {

				out.write("<table><tr><td>ID</td><td>Name</td><td>Author</td>"
						+ "<td>Availability</td></tr>");

				for (Book book : books) {

					out.write(book.toString() + "<br>");
				}
				out.write("</table>");
			} else {
				out.write("There is no such book");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SKIP_BODY;
	}
}
