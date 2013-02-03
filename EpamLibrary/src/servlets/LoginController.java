package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataBase.*;

public class LoginController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String login = req.getParameter("login");
		String pass = req.getParameter("pass");

		User LibUser = UserDaoLibrary.getUserByLogin(login);
		if (pass.equals(LibUser.getPass()) && (LibUser.getAccess() == 0)) {
			List<Book> books = BookDaoLibrary.getBookAll();
			req.setAttribute("books", books);
			req.setAttribute("login", " " + login + "!");
			goToPage("/librarian.jsp", req, resp);
		} else if (pass.equals(LibUser.getPass()) && (LibUser.getAccess() == 1)) {
			req.setAttribute("login", " " + login + "!");
			goToPage("/user.jsp", req, resp);
		} else {
			req.setAttribute("note", "There is no such Login or Password!");
			goToPage("/index.jsp", req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		doPost(req, resp);
	}

	private void goToPage(String address, HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(address);
		dispatcher.forward(req, resp);

	}

}
