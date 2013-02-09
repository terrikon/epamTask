package libraryTags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import dataBase.Book;
import dataBase.dao.BookDaoLibrary;
import dataBase.dao.OrderDaoLibrary;
import dataBase.dao.UserDaoLibrary;

public class MakeOrderTag extends TagSupport {
	private String bookId;
	private int userId;

	public void setBookId(String strBookId) {
		bookId=strBookId;
		
	}

	public void setUserId(Object userLogin) {
		userId = UserDaoLibrary.getUserByLogin(userLogin.toString()).getId();
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		List<Book> books = BookDaoLibrary.getBookById(bookId);
		try {
			if  (books.isEmpty()){
				out.write("");
			}else
			if ((books.get(0).getAvailability() == 1)) {
				out.write("Sorry this book is not available right now ");
			} else {
				OrderDaoLibrary.insertInOrder(bookId, userId);
				BookDaoLibrary.setBookAvailability(bookId, 1);
				out.write("Thank you for your order!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return SKIP_BODY;
	}
}
